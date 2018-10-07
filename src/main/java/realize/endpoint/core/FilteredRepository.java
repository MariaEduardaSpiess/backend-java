package realize.endpoint.core;

import java.io.Serializable;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FilteredRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {
	
	public Stream<T> findAllFiltered(String filtro);

}
