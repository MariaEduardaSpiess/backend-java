package realize.endpoint.json;

import realize.domain.impl.SegmentoImpl;

public class SegmentoJson {

	private Long id;
	private String nome;

	public SegmentoJson(SegmentoImpl segmento) {
		if (segmento!=null) {
			this.id = segmento.getId();
			this.nome = segmento.getNome();
		}	
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
}
