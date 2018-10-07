package realize.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.Estado;
import realize.domain.impl.CidadeImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface CidadeRepository extends FilteredRepository<CidadeImpl, Long> {

	Stream<CidadeImpl> findAllByNomeContaining(String filtro);
	
	@Override
	default Stream<CidadeImpl> findAllFiltered(String filtro) {
		return findAllByNomeContaining(filtro);
	}

	List<CidadeImpl> findByEstadoEquals(Estado estado);

}
