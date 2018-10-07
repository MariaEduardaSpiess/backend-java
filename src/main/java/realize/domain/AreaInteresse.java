package realize.domain;

import java.io.Serializable;

import realize.domain.impl.SegmentoImpl;
import realize.domain.impl.VoluntarioImpl;

public interface AreaInteresse extends Serializable {

	Long getId();
	VoluntarioImpl getVoluntario();
	SegmentoImpl getSegmento();
	
	void setId(Long id);
	void setVoluntario(VoluntarioImpl voluntario);
	void setSegmento(SegmentoImpl segmento);
	
}
