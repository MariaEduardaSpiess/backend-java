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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.EventoImpl;
import realize.domain.impl.TimeVoluntarioImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.endpoint.core.ValidaToken;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;
import realize.mail.SendMail;
import realize.repo.EntidadeRepository;
import realize.repo.EventoRepository;
import realize.repo.TimeVoluntarioRepository;

@RestController
@RequestMapping( "/api/private/v1/entidade" )
public class EntidadeEndpoint extends AbstractCRUDEndpoint<EntidadeImpl, Long> {

	@Autowired
	private ValidaToken validaToken;

	@Autowired
	private EntidadeRepository repo;

	@Autowired
	private EventoRepository repoevento;
	

	@Autowired
	private TimeVoluntarioRepository repoTime;
	
	@Autowired
	private SendMail sendmail;

	@Override
	public FilteredRepository<EntidadeImpl, Long> getRepository() {
		return repo;
	}
	
	@PutMapping("addtimevoluntario") 
	@Transactional(rollbackFor=Exception.class)
	public TimeVoluntarioImpl addSegmento(HttpServletRequest request,
			@RequestBody VoluntarioImpl voluntario) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(voluntario);
		if (!violations.isEmpty()) throw new ValidationException(violations);
		
		TimeVoluntarioImpl time = new TimeVoluntarioImpl();
		validaToken.valida(request);
		EntidadeImpl entidade = validaToken.getEntidade();

		time.setEntidade(entidade);
		time.setVoluntario(voluntario);
		return repoTime.save(time);
	}

	
	@Override
	public void validateBeforePersist(EntidadeImpl obj) throws ErroNoProcessoException {
		super.validateBeforePersist(obj);
		System.out.println("enviando email");
		String subject = "Entidade aguardando aprovação";
		String message = String.format("A entidade %s aguarda aprovação!",obj.getNomeCompleto());
		
		try {
			sendmail.sendAdm(subject,message);
		} catch (EmailException e) {
			e.printStackTrace();
			throw new ErroNoProcessoException(e.getMessage());
		}
	}
	
	@PutMapping("register") 
	@Transactional(rollbackFor=Exception.class)
	public EntidadeImpl insert(@RequestHeader Map<String,String> header,
			@RequestBody EntidadeImpl obj)
			throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		validateBeforePersist(obj);
		return repo.save(obj);
	}

	@GetMapping("meuseventos")
	public List<EventoImpl> meuseventos(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		validaToken.valida(request);
		EntidadeImpl entidade = validaToken.getEntidade();

		return repoevento.findAllByEntidade(entidade);
	}
	
	@GetMapping("desabilitadas")
	public List<EntidadeImpl> getEntidadesDesabilitadas() {
		return repo.findByEnabled(false);
	}

	@PostMapping("habilitaentidade")
	public EntidadeImpl habilita (HttpServletRequest request, 
			@RequestBody EntidadeImpl entidade) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(entidade);
		if (!violations.isEmpty()) throw new ValidationException(violations);
		
		
		validaToken.valida(request);

		entidade.setEnabled(true);
		
		return repo.save(entidade);
	
	}
}
