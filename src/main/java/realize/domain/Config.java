package realize.domain;

import java.io.Serializable;

public interface Config extends Serializable {

	Long getId();
	String getChave();
	String getVariavel();
	String getConteudo();
	
}
