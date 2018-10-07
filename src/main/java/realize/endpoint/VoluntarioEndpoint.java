package realize.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.AreaInteresseImpl;
import realize.domain.impl.EventoImpl;
import realize.domain.impl.SegmentoImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.endpoint.core.ValidaToken;
import realize.endpoint.json.AreaInteresseParam;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;
import realize.repo.AreaInteresseRepository;
import realize.repo.EventoRepository;
import realize.repo.ParticipanteEventoRepository;
import realize.repo.SegmentoRepository;
import realize.repo.VoluntarioRepository;

@RestController
@RequestMapping( "/api/private/v1/voluntario" )
public class VoluntarioEndpoint extends AbstractCRUDEndpoint<VoluntarioImpl, Long> {

	@Autowired
	private ValidaToken validaToken;
	
	@Autowired
	private VoluntarioRepository repo;
	
	@Autowired
	private EventoRepository repoEvento;

	@Autowired
	private ParticipanteEventoRepository repoPartEvento;
	
	@Autowired
	private AreaInteresseRepository repoAreaInteresse;
	
	@Autowired
	private SegmentoRepository repoSegmento;
	
	@Override
	public FilteredRepository<VoluntarioImpl, Long> getRepository() {
		return repo;
	}

	@GetMapping("feedeventos")
	public List<EventoImpl> feedeventos(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		validaToken.valida(request);
		VoluntarioImpl voluntario = validaToken.getVoluntario();
		if (voluntario.getAreasInteresse().size()==0) throw new ErroNoProcessoException("Não existem áreas de interesse informadas!");
		List<SegmentoImpl> segs = voluntario.getAreasInteresse().stream().map(a -> a.getSegmento()).collect(Collectors.toList());
		List<EventoImpl> list = new ArrayList<>();
		List<EventoImpl> listEventos = repoEvento.findAllBySegmentoContaining(segs);
		List<EventoImpl> listPart = repoPartEvento.findAllByVoluntarioComEventoFuturo(voluntario);
		for (EventoImpl evento : listEventos) {
			boolean achou=false;
			for (EventoImpl eventoPart : listPart) {
				if (eventoPart.getId().equals(evento.getId())) {
					achou=true;
					break;
				}
			}
			if (!achou) list.add(evento);
		}
		return list;
	}

	@PutMapping("addsegmento") 
	@Transactional(rollbackFor=Exception.class)
	public AreaInteresseImpl addSegmento(@RequestHeader Map<String,String> header,
			@RequestBody AreaInteresseParam obj) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) throw new ValidationException(violations);
		
		AreaInteresseImpl area = new AreaInteresseImpl();
		
		VoluntarioImpl voluntario = repo.findOne(obj.getVoluntarioId());
		if (voluntario==null) throw new ErroNoProcessoException(String.format("Voluntário (%s) não encontrado!",obj.getVoluntarioId()));

		SegmentoImpl segmento = repoSegmento.findOne(obj.getSegmentoId());
		if (segmento==null) throw new ErroNoProcessoException(String.format("Segmento (%s) não encontrado!",obj.getSegmentoId()));
		
		area.setVoluntario(voluntario);
		area.setSegmento(segmento);
		return repoAreaInteresse.save(area);
	}

	@GetMapping("meuseventos")
	public List<EventoImpl> meuseventos(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		validaToken.valida(request);
		VoluntarioImpl voluntario = validaToken.getVoluntario();

		return repoPartEvento.findAllByVoluntarioComEventoFuturo(voluntario);
	}
	
	@GetMapping("historico")
	public List<EventoImpl> historico(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		validaToken.valida(request);
		VoluntarioImpl voluntario = validaToken.getVoluntario();

		return repoPartEvento.findAllByVoluntarioComEventoPassado(voluntario);
	}
	
	@PutMapping("register") 
	@Transactional(rollbackFor=Exception.class)
	public VoluntarioImpl register(@RequestHeader Map<String,String> header,
			@RequestBody VoluntarioImpl obj)
			throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		return repo.save(obj);
	}

}
