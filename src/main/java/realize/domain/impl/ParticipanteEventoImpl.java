package realize.domain.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import realize.domain.ParticipanteEvento;
import realize.endpoint.json.VoluntarioJson;

@Entity
@Table(name="participanteEvento")
public class ParticipanteEventoImpl implements ParticipanteEvento {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="evento", referencedColumnName = "id")})
	private EventoImpl evento;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="voluntario", referencedColumnName = "id")})
	private VoluntarioImpl voluntario;
	
	private boolean aceito=false;
	
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}

	public void setEvento(EventoImpl evento) {
		this.evento = evento;
	}
	@Override
	public EventoImpl getEvento() {
		return evento;
	}

	public void setVoluntario(VoluntarioImpl voluntario) {
		this.voluntario = voluntario;
	}
	@Override
	public VoluntarioImpl getVoluntario() {
		return voluntario;
	}

	public void setAceito(boolean aceito) {
		this.aceito = aceito;
	}
	@Override
	public boolean isAceito() {
		return aceito;
	}
	
	public VoluntarioJson getVoluntarioJson() {
		return new VoluntarioJson(voluntario);
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
		ParticipanteEventoImpl other = (ParticipanteEventoImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
