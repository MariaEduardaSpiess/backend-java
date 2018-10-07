package realize.domain;

import java.io.Serializable;

public interface Cidade extends Serializable {

	Long getId();
	Estado getEstado();
	String getNome();
	
}
