package realize.domain.impl;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import realize.domain.Entidade;
import realize.domain.serialize.EntidadeSerializer;

@Entity
@DiscriminatorValue("E")
@JsonSerialize(using=EntidadeSerializer.class)
public class EntidadeImpl extends UsuarioImpl implements Entidade {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="entidade",fetch=FetchType.EAGER)
	@JsonIgnore
	private List<TimeVoluntarioImpl> timeVoluntarios;

	public void setTimeVoluntarios(List<TimeVoluntarioImpl> timeVoluntarios) {
		this.timeVoluntarios = timeVoluntarios;
	}
	@Override
	public List<TimeVoluntarioImpl> getTimeVoluntarios() {
		return timeVoluntarios;
	}

	public List<TimeVoluntarioImpl> getTimeVoluntariosJson() {
		return timeVoluntarios;
	}

}
