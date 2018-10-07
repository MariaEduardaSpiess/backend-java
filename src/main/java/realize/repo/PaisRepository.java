package realize.repo;

import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.impl.PaisImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface PaisRepository extends FilteredRepository<PaisImpl, Long> {

	Stream<PaisImpl> findAllByNomeContaining(String filtro);
	
	@Override
	default Stream<PaisImpl> findAllFiltered(String filtro) {
		return findAllByNomeContaining(filtro);
	}


}
