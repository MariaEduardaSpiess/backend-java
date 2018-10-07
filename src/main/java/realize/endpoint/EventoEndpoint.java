package realize.endpoint;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonEncoding;

import realize.domain.impl.EventoImpl;
import realize.domain.impl.ParticipanteEventoImpl;
import realize.domain.impl.TimeVoluntarioImpl;
import realize.domain.impl.UsuarioImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.endpoint.json.ImagemJson;
import realize.endpoint.json.ParticipanteEventoParam;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;
import realize.mail.SendMail;
import realize.repo.EventoRepository;
import realize.repo.ParticipanteEventoRepository;
import realize.repo.TimeVoluntarioRepository;
import realize.repo.VoluntarioRepository;

@RestController
@RequestMapping( "/api/private/v1/evento" )
public class EventoEndpoint extends AbstractCRUDEndpoint<EventoImpl, Long> {

	@Autowired
	private EventoRepository repo;
	
	@Autowired
	private VoluntarioRepository repovoluntario;
	
	@Autowired
	private ParticipanteEventoRepository repoparticipante;

	@Autowired
	private TimeVoluntarioRepository repotime;
	
	@Autowired
	private SendMail sendmail;

	@Override
	public FilteredRepository<EventoImpl, Long> getRepository() {
		return repo;
	}

	@PutMapping("addparticipante") 
	@Transactional(rollbackFor=Exception.class)
	public ParticipanteEventoImpl addParticipante(@RequestHeader Map<String,String> header,
			@RequestBody ParticipanteEventoParam obj) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) throw new ValidationException(violations);
		
		
		ParticipanteEventoImpl participante = null;
		
		EventoImpl evento = repo.findOne(obj.getEventoId());
		VoluntarioImpl voluntario = repovoluntario.findOne(obj.getVoluntarioId());

		Long count = repoparticipante.countByEventoAndAceito(evento, true);
		if (evento.getVagas().compareTo(count.intValue())<=0) {
			List<ParticipanteEventoImpl> list = repoparticipante.findAllByEventoAndAceito(evento, false);
			String subject = String.format("Evento: %s", evento.getTitulo());
			String message = String.format("Todas as %s vagas disponíveis para o evento foram preenchidas!", evento.getVagas());
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
		
		ParticipanteEventoImpl part = repoparticipante.findByEventoAndVoluntario(evento, voluntario);
		if (part!=null) {
			if (!part.isAceito()) throw new ErroNoProcessoException("Participante aguardando autorização para participar do evento!");
			throw new ErroNoProcessoException("Participante já registrado no evento!");
		}

		participante = new ParticipanteEventoImpl();
		// Caso seleção estiver true seta aceito false, caso seleção estiver false seta aceito true pois não precisa seleção
		boolean aceito = !evento.isSelecao();
		
		TimeVoluntarioImpl timevol = repotime.findByEntidadeAndVoluntario(evento.getEntidade(), voluntario);
		if (timevol!=null) aceito=true;
		
		participante.setEvento(evento);
		participante.setVoluntario(voluntario);
		participante.setAceito(aceito);
		
		ParticipanteEventoImpl ret = repoparticipante.save(participante);
		
		if (!ret.isAceito()) {
			System.out.println("enviando email");
			String subject = "Participante aguardando aprovação";
			String message = String.format("O voluntário %s aguarda aprovação para participar do evento %s, que vai ocorrer no dia %s ás %s!",
					voluntario.getNomeCompleto().trim(),
					evento.getTitulo().trim(),
					evento.getData(),
					evento.getHora());
			
			try {
				sendmail.send(evento.getEntidade().getEmail(), subject,message);
			} catch (EmailException e) {
				e.printStackTrace();
				throw new ErroNoProcessoException(e.getMessage());
			}
		}
		return ret;
	}

	@DeleteMapping("removeparticipante") 
	@Transactional(rollbackFor=Exception.class)
	public void removeParticipante(@RequestHeader Map<String,String> header,
			@RequestBody ParticipanteEventoParam obj) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) throw new ValidationException(violations);
		
		EventoImpl evento = repo.findOne(obj.getEventoId());
		if (evento==null) throw new ErroNoProcessoException("Evento não encontrado!");
		
		VoluntarioImpl voluntario = repovoluntario.findOne(obj.getVoluntarioId());
		if (voluntario==null) throw new ErroNoProcessoException("Voluntário não encontrado!");
		
		ParticipanteEventoImpl participante = repoparticipante.findByEventoAndVoluntario(evento, voluntario);
		if (participante==null) throw new ErroNoProcessoException("Participante não encontrado no evento!");
		
		repoparticipante.delete(participante.getId());
		
	}	
	
	@PostMapping("addimagem") 
	@Transactional(rollbackFor=Exception.class)
	public String addimagem(HttpServletRequest request, @RequestParam("id") Long id,
			@RequestBody String imagem)
			throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		EventoImpl evento = repo.findOne(id);
		if (evento==null) throw new ErroNoProcessoException(String.format("Não foi possível adicionar imagem!\nEvento %s não encontrado!",id));
		evento.setImagem(imagem);
		repo.save(evento);
		return "sucesso";
	}
	
	@GetMapping("getimagem")
	public String getImagemPerfil(HttpServletRequest request, @RequestParam("id") Long id) throws InvalidAuthException, ErroNoProcessoException {
		EventoImpl evento = repo.findOne(id);
		if (evento==null) throw new ErroNoProcessoException(String.format("Não foi possível localizar imagem!\nEvento %s não encontrado!",id));
		return evento.getImagem();
	}

	
}
