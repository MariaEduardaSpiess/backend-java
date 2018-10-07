package realize.endpoint;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.ParticipanteEventoImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.exeptions.ErroNoProcessoException;
import realize.mail.SendMail;
import realize.repo.ParticipanteEventoRepository;

@RestController
@RequestMapping( "/api/private/v1/participanteevento" )
public class ParticipanteEventoEndpoint extends AbstractCRUDEndpoint<ParticipanteEventoImpl, Long> {

	@Autowired
	private ParticipanteEventoRepository repo;
	
	@Autowired
	private SendMail sendmail;

	@Override
	public FilteredRepository<ParticipanteEventoImpl, Long> getRepository() {
		return repo;
	}

	@Override
	public void validateBeforePersist(ParticipanteEventoImpl obj) throws ErroNoProcessoException {
		super.validateBeforePersist(obj);
		
		ParticipanteEventoImpl ant = repo.findOne(obj.getId());
		if (!ant.isAceito() && obj.isAceito()) {
			Long count = repo.countByEventoAndAceito(ant.getEvento(), true);
			if (ant.getEvento().getVagas().compareTo(count.intValue())<=0) {
				List<ParticipanteEventoImpl> list = repo.findAllByEventoAndAceito(ant.getEvento(), false);
				String subject = String.format("Evento: %s", ant.getEvento().getTitulo());
				String message = String.format("Todas as %s vagas disponíveis para o evento foram preenchidas!", ant.getEvento().getVagas());
				for (ParticipanteEventoImpl part : list) {
					try {
						sendmail.send(part.getVoluntario().getEmail(), subject, message);
					} catch (EmailException e) {
						e.printStackTrace();
						throw new ErroNoProcessoException(e.getMessage());
					}
				}
				throw new ErroNoProcessoException("Todas as vagas disponíveis para o evento foram preenchidas!"); 
			}

		}
		
	}
}
