package realize.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.EventoImpl;
import realize.domain.impl.SegmentoImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface EventoRepository extends FilteredRepository<EventoImpl, Long> {

	Stream<EventoImpl> findAllByTituloContaining(String filtro);
	
	@Override
	default Stream<EventoImpl> findAllFiltered(String filtro) {
		return findAllByTituloContaining(filtro);
	}

	@Query("select e from EventoImpl e where e.data >= date(now()) and e.segmento in :segs order by e.data, e.hora")
	List<EventoImpl> findAllBySegmentoContaining(@Param("segs") List<SegmentoImpl> segs);

	List<EventoImpl> findAllByEntidade(EntidadeImpl entidade);


}
