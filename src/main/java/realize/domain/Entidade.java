package realize.domain;

import java.util.List;

import realize.domain.impl.TimeVoluntarioImpl;

public interface Entidade extends Usuario {

	List<TimeVoluntarioImpl> getTimeVoluntarios();
	
}
