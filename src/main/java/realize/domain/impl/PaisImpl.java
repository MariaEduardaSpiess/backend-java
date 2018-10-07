package realize.domain.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import realize.domain.Pais;
import realize.domain.serialize.PaisSerializable;

@Entity
@Table(name="pais")
@JsonSerialize(using=PaisSerializable.class)
public class PaisImpl implements Pais {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length=3, unique=true)
	private String sigla;

	@Column(length=60, unique=true)
	private String nome;

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	@Override
	public String getSigla() {
		return sigla;
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
		PaisImpl other = (PaisImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
