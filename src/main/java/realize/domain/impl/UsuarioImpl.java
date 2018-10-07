package realize.domain.impl;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;

import realize.domain.Usuario;

@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType=DiscriminatorType.STRING, length=1)
@DiscriminatorValue("U")
public class UsuarioImpl implements Usuario {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length=60, unique=true)
	private String email;
	
	@Column(length=15)
	private String userPassword;

	@Column(length=100)
	private String nomeCompleto;

	@Column(length=14)
	private String documento;

	@Column(length=12)
	private String telefone;

	@Column(length=13)
	private String celular;

	@Column(length=45)
	private String endPais;
	
	@Column(length=45)
	private String endEstado;
	
	@Column(length=45)
	private String endCidade;
	
	@Column(length=8)
	private String endCep;
	
	@Column(length=100)
	private String endLogradouro;
	
	@Column(length=8)
	private Integer endNumero;
	
	@Column(length=255)
	private String endComplemento;

	@Column(insertable=false, updatable=false)
	private String tipo;

	private boolean enabled;

	@Column(length=100)
	private String endBairro;
	
	@Lob
    @Column(nullable=true, columnDefinition="mediumblob")
	private String imagem;

	@Column(length=500)
	private String descricao;
	
	@Version
	private Long version;
	
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getEmail() {
		return email;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setEndPais(String endPais) {
		this.endPais = endPais;
	}
	@Override
	public String getEndPais() {
		return endPais;
	}

	public void setEndestado(String endEstado) {
		this.endEstado = endEstado;
	}
	@Override
	public String getEndEstado() {
		return endEstado;
	}
	
	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}
	@Override
	public String getEndCidade() {
		return endCidade;
	}
	
	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}
	@Override
	public String getEndCep() {
		return endCep;
	}
	
	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}
	@Override
	public String getEndLogradouro() {
		return endLogradouro;
	}
	
	public void setEndNumero(Integer endNumero) {
		this.endNumero = endNumero;
	}
	@Override
	public Integer getEndNumero() {
		return endNumero;
	}
	
	public void setEndComplemento(String endComplemento) {
		this.endComplemento = endComplemento;
	}
	@Override
	public String getEndComplemento() {
		return endComplemento;
	}
	
	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}
	@Override
	public String getEndBairro() {
		return endBairro;
	}
	
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	@Override
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	@Override
	public String getDocumento() {
		return documento;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String getTelefone() {
		return telefone;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	@Override
	public String getCelular() {
		return celular;
	}
	
	@Override
	public String getTipo() {
		return tipo;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	@Override
	public String getImagem() {
		return imagem;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	@Override
	public Long getVersion() {
		return version;
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
		UsuarioImpl other = (UsuarioImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
