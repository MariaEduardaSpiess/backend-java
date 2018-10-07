package realize.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.Pais;
import realize.domain.impl.EstadoImpl;
import realize.domain.impl.PaisImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface EstadoRepository extends FilteredRepository<EstadoImpl, Long> {

	Stream<EstadoImpl> findAllByNomeContaining(String filtro);
	
	@Override
	default Stream<EstadoImpl> findAllFiltered(String filtro) {
		return findAllByNomeContaining(filtro);
	}

	List<EstadoImpl> findByPaisEquals(Pais pais);


}
