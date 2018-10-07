package realize.endpoint;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.endpoint.json.SendMailParam;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;
import realize.mail.SendMail;

@RestController
@RequestMapping( "/api/private/v1/sendmail" )
public class SendMailEndpoint {

	@Autowired
	private SendMail sendmail;
	
	@PutMapping("send") 
	public String sendMail(@RequestHeader Map<String,String> header,
			@RequestBody SendMailParam obj) throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		if (!violations.isEmpty()) throw new ValidationException(violations);

		try {
			sendmail.send(obj.getMailTo(), obj.getMailSubject(), obj.getMailMessage());
		} catch (EmailException e) {
			e.printStackTrace();
			throw new ErroNoProcessoException(e.getMessage());
		}
		
		return "Sucesso";
	}
}
