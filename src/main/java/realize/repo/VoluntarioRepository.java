package realize.repo;

import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface VoluntarioRepository extends FilteredRepository<VoluntarioImpl, Long> {

	VoluntarioImpl findByEmail(String login);
	Stream<VoluntarioImpl> findAllByEmailContaining(String filtro);
	
	@Override
	default Stream<VoluntarioImpl> findAllFiltered(String filtro) {
		return findAllByEmailContaining(filtro);
	}
}
