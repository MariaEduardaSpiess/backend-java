package realize.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import realize.domain.impl.EventoImpl;
import realize.domain.impl.ParticipanteEventoImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface ParticipanteEventoRepository extends FilteredRepository<ParticipanteEventoImpl, Long> {

	@Override
	default Stream<ParticipanteEventoImpl> findAllFiltered(String filtro) {
		return findAll().stream();
	}

	ParticipanteEventoImpl findByEventoAndVoluntario(EventoImpl evento, VoluntarioImpl voluntario);

	@Query("select p.evento from ParticipanteEventoImpl p where p.evento.data >= date(now()) and p.voluntario like :voluntario order by p.evento.data")
	List<EventoImpl> findAllByVoluntarioComEventoFuturo(@Param("voluntario") VoluntarioImpl voluntario);
	
	@Query("select p.evento from ParticipanteEventoImpl p where p.evento.data < date(now()) and p.voluntario like :voluntario order by p.evento.data")
	List<EventoImpl> findAllByVoluntarioComEventoPassado(@Param("voluntario") VoluntarioImpl voluntario);

	Long countByEventoAndAceito(EventoImpl evento, boolean aceito);

	List<ParticipanteEventoImpl> findAllByEventoAndAceito(EventoImpl evento, boolean aceito);


}
