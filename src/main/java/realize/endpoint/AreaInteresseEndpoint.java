package realize.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.AreaInteresseImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.repo.AreaInteresseRepository;

@RestController
@RequestMapping( "/api/private/v1/areainteresse" )
public class AreaInteresseEndpoint extends AbstractCRUDEndpoint<AreaInteresseImpl, Long> {

	@Autowired
	private AreaInteresseRepository repo;
	
	@Override
	public FilteredRepository<AreaInteresseImpl, Long> getRepository() {
		return repo;
	}

	
	
}
