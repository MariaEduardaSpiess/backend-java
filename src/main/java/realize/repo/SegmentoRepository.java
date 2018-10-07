package realize.repo;

import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.impl.SegmentoImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface SegmentoRepository extends FilteredRepository<SegmentoImpl, Long> {

	Stream<SegmentoImpl> findAllByNomeContaining(String filtro);
	
	@Override
	default Stream<SegmentoImpl> findAllFiltered(String filtro) {
		return findAllByNomeContaining(filtro);
	}


}
