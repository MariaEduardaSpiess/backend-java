package realize.repo;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import realize.domain.impl.ConfigImpl;
import realize.endpoint.core.FilteredRepository;

@Repository
public interface ConfigRepository extends FilteredRepository<ConfigImpl, Long> {

	@Override
	default Stream<ConfigImpl> findAllFiltered(String filtro) {
		return findAll().stream();
	}

	@Query("Select c from ConfigImpl c where chave like :pchave and variavel like :pvariavel")
	ConfigImpl findByChaveVariavel(@Param("pchave") String pChave, @Param("pvariavel") String pVariavel);

}
