package realize.endpoint.json;

import realize.domain.impl.EntidadeImpl;

public class EntidadeJson {

	private Long id;
	private String nome;

	public EntidadeJson(EntidadeImpl entidade) {
		if (entidade!=null) {
			this.id = entidade.getId();
			this.nome = entidade.getNomeCompleto();
		}	
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
}
