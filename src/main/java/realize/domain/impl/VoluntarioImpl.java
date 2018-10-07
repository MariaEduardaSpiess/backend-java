package realize.domain.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import realize.domain.Voluntario;
import realize.domain.serialize.VoluntarioSerializer;

@Entity
@DiscriminatorValue("V")
@JsonSerialize(using=VoluntarioSerializer.class)
public class VoluntarioImpl extends UsuarioImpl implements Voluntario {

	private static final long serialVersionUID = 1L;
	
	private boolean enabled=true;
	
	@OneToMany(mappedBy="voluntario", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<AreaInteresseImpl> areasInteresse;

	public void setAreasInteresse(List<AreaInteresseImpl> areasInteresse) {
		this.areasInteresse = areasInteresse;
	}
	
	@Override
	public List<AreaInteresseImpl> getAreasInteresse() {
		return areasInteresse;
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public List<AreaInteresseImpl> getAreasInteresseJson() {
		return areasInteresse;
	}
	
	
}
