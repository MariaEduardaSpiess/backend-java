package realize.endpoint;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.endpoint.json.ConfigEmailParam;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;
import realize.mail.ConfigEmailImpl;

@RestController
@RequestMapping( "/api/private/v1/config/email" )
public class ConfigEmailEndpoint  {

	@Autowired
	private ConfigEmailImpl cfgmail;
	
	@PostMapping
	@Transactional(rollbackFor=Exception.class)
	public ConfigEmailParam postEmail(@RequestHeader Map<String,String> header,
			@RequestBody ConfigEmailParam obj) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) throw new ValidationException(violations);
		
		cfgmail.save(obj);
		
		return obj;
	}
	
	@GetMapping 
	public ConfigEmailParam getEmail(@RequestHeader Map<String,String> header) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		ConfigEmailParam obj = new ConfigEmailParam();
		obj.setCharSet(cfgmail.getCharSet());
		obj.setHostName(cfgmail.getHostName());
		obj.setMailFrom(cfgmail.getMailFrom());
		obj.setPassword(cfgmail.getPassword());
		obj.setSmtpPort(cfgmail.getSmtpPort());
		obj.setSsl(cfgmail.isSSL());
		obj.setTls(cfgmail.isTLS());
		obj.setUserName(cfgmail.getUserName());
		return obj;
	}
	
}
