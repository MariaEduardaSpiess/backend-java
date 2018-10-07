package realize.domain.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import realize.domain.Config;

@Entity
@Table(name="config")
public class ConfigImpl implements Config {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length=200)
	private String chave;

	@Column(length=200)
	private String variavel;

	@Column(length=200)
	private String conteudo;
	
	public ConfigImpl() {
	}

	public ConfigImpl(String chave, String variavel, String conteudo) {
		this.chave = chave;
		this.variavel = variavel;
		this.conteudo = conteudo;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	@Override
	public String getChave() {
		return chave;
	}
	
	public void setVariavel(String variavel) {
		this.variavel = variavel;
	}
	@Override
	public String getVariavel() {
		return variavel;
	}
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	@Override
	public String getConteudo() {
		return conteudo;
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
		ConfigImpl other = (ConfigImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
