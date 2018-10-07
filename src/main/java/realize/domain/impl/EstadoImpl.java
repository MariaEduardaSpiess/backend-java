package realize.domain.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import realize.domain.Estado;
import realize.domain.Pais;
import realize.domain.serialize.EstadoSerializable;

@Entity
@Table(name="estado")
@JsonSerialize(using=EstadoSerializable.class)
public class EstadoImpl implements Estado {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=PaisImpl.class)
	@JoinColumns({@JoinColumn(name="pais", referencedColumnName = "id")})
	private Pais pais;

	@Column(length=2,unique=true)
	private String uf;

	@Column(length=60)
	private String nome;


	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	@Override
	public Pais getPais() {
		return pais;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public String getUf() {
		return uf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getNome() {
		return nome;
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
		EstadoImpl other = (EstadoImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
