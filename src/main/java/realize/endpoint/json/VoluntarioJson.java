package realize.endpoint.json;

import realize.domain.impl.VoluntarioImpl;

public class VoluntarioJson {

	private Long id;
	private String nome;

	public VoluntarioJson(VoluntarioImpl voluntario) {
		this.id = voluntario.getId();
		this.nome = voluntario.getNomeCompleto();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

}
