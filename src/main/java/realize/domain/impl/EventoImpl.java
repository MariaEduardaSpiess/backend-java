package realize.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import realize.domain.Evento;
import realize.endpoint.json.ParticipanteEventoJson;

@Entity
@Table(name="evento")
public class EventoImpl implements Evento {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="entidade", referencedColumnName = "id")})
	private EntidadeImpl entidade;
	
	@Column(length=60)
	private String titulo;
	
	@Lob
	private String descricao;
	
	@Lob
    @Column(nullable=true, columnDefinition="mediumblob")
	private String imagem;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(length=5)
	private String hora;
	
	@Column(length=3)
	private Integer vagas;
	
	private boolean selecao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="segmento", referencedColumnName = "id")})
	private SegmentoImpl segmento;
	
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

	@Column(length=100)
	private String endBairro;

	@JsonIgnore
	@OneToMany(mappedBy="evento",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ParticipanteEventoImpl> participantes;

	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public void setEntidade(EntidadeImpl entidade) {
		this.entidade = entidade;
	}
	
	@Override
	public EntidadeImpl getEntidade() {
		return entidade;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Override
	public String getTitulo() {
		return titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String getDescricao() {
		return descricao;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	@Override
	public String getImagem() {
		return imagem;
	}

	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public Date getData() {
		return data;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	@Override
	public String getHora() {
		return hora;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}
	@Override
	public Integer getVagas() {
		return vagas;
	}

	public void setSelecao(boolean selecao) {
		this.selecao = selecao;
	}
	@Override
	public boolean isSelecao() {
		return selecao;
	}

	public void setSegmento(SegmentoImpl segmento) {
		this.segmento = segmento;
	}
	@Override
	public SegmentoImpl getSegmento() {
		return segmento;
	}

	public void setEndPais(String endPais) {
		this.endPais = endPais;
	}
	@Override
	public String getEndPais() {
		return endPais;
	}
	
	public void setEndEstado(String endEstado) {
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

	public void setParticipantes(List<ParticipanteEventoImpl> participantes) {
		this.participantes = participantes;
	}
	@Override
	public List<ParticipanteEventoImpl> getParticipantes() {
		return participantes;
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
		EventoImpl other = (EventoImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<ParticipanteEventoJson> getParticipantesJson() {
		List<ParticipanteEventoJson> list = new ArrayList<>();
		if (participantes!=null) {
			for (ParticipanteEventoImpl part : participantes) {
				ParticipanteEventoJson partJson = new ParticipanteEventoJson(part);
				list.add(partJson);
			}
		}	
		return list;
	}

}
