package realize.domain;

import java.util.List;

import realize.domain.impl.AreaInteresseImpl;

public interface Voluntario extends Usuario {
	
	List<AreaInteresseImpl> getAreasInteresse();
	void setAreasInteresse(List<AreaInteresseImpl> areasInteresse);

}
