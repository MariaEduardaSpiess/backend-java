package realize.repo;

import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.impl.UsuarioImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface UsuarioRepository extends FilteredRepository<UsuarioImpl, Long> {

	UsuarioImpl findByEmail(String login);
	Stream<UsuarioImpl> findAllByEmailContaining(String filtro);
	
	@Override
	default Stream<UsuarioImpl> findAllFiltered(String filtro) {
		return findAllByEmailContaining(filtro);
	}


}
