package realize.endpoint;

import java.util.Map;
import java.util.Set;

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

import realize.domain.impl.ConfigImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.endpoint.json.ConfigEmailParam;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;
import realize.mail.ConfigEmailImpl;
import realize.repo.ConfigRepository;

@RestController
@RequestMapping( "/api/private/v1/config" )
public class ConfigEndpoint extends AbstractCRUDEndpoint<ConfigImpl, Long> {

	@Autowired
	private ConfigRepository repo;
	
	@Autowired
	private ConfigEmailImpl cfgmail;
	
	@Override
	public FilteredRepository<ConfigImpl, Long> getRepository() {
		return repo;
	}

	@Override
	public ConfigImpl insert(Map<String, String> header, ConfigImpl obj)
			throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		obj.setChave(obj.getChave().toUpperCase());
		obj.setVariavel(obj.getVariavel().toUpperCase());
		if (repo.findByChaveVariavel(obj.getChave(), obj.getVariavel())!=null) {
			return super.update(header, obj);
		}
		
		return super.insert(header, obj);
	}
	
}
