package realize.domain;

import java.io.Serializable;

import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.VoluntarioImpl;

public interface TimeVoluntario extends Serializable {

	Long getId();
	EntidadeImpl getEntidade();
	VoluntarioImpl getVoluntario();
	
}
