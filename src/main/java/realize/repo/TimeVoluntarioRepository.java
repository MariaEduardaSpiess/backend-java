package realize.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.TimeVoluntarioImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface TimeVoluntarioRepository extends FilteredRepository<TimeVoluntarioImpl, Long> {

	@Override
	default Stream<TimeVoluntarioImpl> findAllFiltered(String filtro) {
		return findAll().stream();
	}

	List<TimeVoluntarioImpl> findByEntidade(EntidadeImpl entidade);

	TimeVoluntarioImpl findByEntidadeAndVoluntario(EntidadeImpl entidade, VoluntarioImpl voluntario);


}
