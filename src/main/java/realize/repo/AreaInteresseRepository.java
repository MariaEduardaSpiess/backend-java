package realize.repo;

import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.impl.AreaInteresseImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface AreaInteresseRepository extends FilteredRepository<AreaInteresseImpl, Long> {

	@Override
	default Stream<AreaInteresseImpl> findAllFiltered(String filtro) {
		return findAll().stream();
	}


}
