package realize.endpoint.core;

import java.io.Serializable;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import realize.exeptions.InvalidAuthException;

public abstract class AbstractCRUDEndpoint<T,ID extends Serializable> extends AbstractBaseCRUDEndpoint<T, ID,ID> {

//	@Autowired
//	private AuthWithUserPassImpl tksb;

	@GetMapping("{id}")
	public T findOne(@RequestHeader Map<String,String> header, 
			@PathVariable("id") ID id) throws InvalidAuthException {
//		tksb.validateAuth(header);
		FilteredRepository<T, ID> repo = getRepository();
		return repo.findOne(id);
	}
	
}
