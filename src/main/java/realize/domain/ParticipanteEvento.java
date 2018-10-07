package realize.domain;

import java.io.Serializable;

import realize.domain.impl.EventoImpl;
import realize.domain.impl.VoluntarioImpl;

public interface ParticipanteEvento extends Serializable {

	Long getId();
	EventoImpl getEvento();
	VoluntarioImpl getVoluntario();
	boolean isAceito();
	
}
