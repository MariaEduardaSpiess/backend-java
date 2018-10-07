package realize.domain.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import realize.domain.TimeVoluntario;

@Entity
@Table(name="timevoluntario")
public class TimeVoluntarioImpl implements TimeVoluntario {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="entidade", referencedColumnName = "id")})
	private EntidadeImpl entidade;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="voluntario", referencedColumnName = "id")})
	private VoluntarioImpl voluntario;

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}

	public void setVoluntario(VoluntarioImpl voluntario) {
		this.voluntario = voluntario;
	}
	@Override
	public VoluntarioImpl getVoluntario() {
		return voluntario;
	}
	
	public void setEntidade(EntidadeImpl entidade) {
		this.entidade = entidade;
	}
	@Override
	public EntidadeImpl getEntidade() {
		return entidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeVoluntarioImpl other = (TimeVoluntarioImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
