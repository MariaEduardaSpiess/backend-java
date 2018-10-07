package realize.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.SegmentoImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.repo.SegmentoRepository;

@RestController
@RequestMapping( "/api/private/v1/segmento" )
public class SegmentoEndpoint extends AbstractCRUDEndpoint<SegmentoImpl, Long> {

	@Autowired
	private SegmentoRepository repo;
	
	@Override
	public FilteredRepository<SegmentoImpl, Long> getRepository() {
		return repo;
	}

	
	
}
