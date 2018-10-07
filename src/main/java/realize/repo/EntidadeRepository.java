package realize.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.impl.EntidadeImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface EntidadeRepository extends FilteredRepository<EntidadeImpl, Long> {

	EntidadeImpl findByEmail(String login);
	Stream<EntidadeImpl> findAllByEmailContaining(String filtro);
	
	@Override
	default Stream<EntidadeImpl> findAllFiltered(String filtro) {
		return findAllByEmailContaining(filtro);
	}
	List<EntidadeImpl> findByEnabled(boolean enabled);

}
