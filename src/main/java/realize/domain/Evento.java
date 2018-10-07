package realize.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.ParticipanteEventoImpl;
import realize.domain.impl.SegmentoImpl;

public interface Evento extends Endereco, Serializable {

	Long getId();
	
	EntidadeImpl getEntidade();
	
	String getTitulo();
	String getDescricao();
	String getImagem();
	Date getData();
	String getHora();
	Integer getVagas();
	boolean isSelecao();
	
	SegmentoImpl getSegmento();
	
	List<ParticipanteEventoImpl> getParticipantes();
	
}
