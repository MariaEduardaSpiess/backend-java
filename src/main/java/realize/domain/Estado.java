package realize.domain;

import java.io.Serializable;

public interface Estado extends Serializable {

	Long getId();
	Pais getPais();
	String getUf();
	String getNome();
	
}
