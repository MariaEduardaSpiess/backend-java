package realize.endpoint.json;

import realize.domain.impl.ParticipanteEventoImpl;

public class ParticipanteEventoJson {

	
	private Long id;
	private VoluntarioJson voluntario;
	private boolean aceito;

	public ParticipanteEventoJson(ParticipanteEventoImpl part) {
		this.id = part.getId();
		this.voluntario = part.getVoluntarioJson();
		this.aceito = part.isAceito();
	}
	
	public Long getId() {
		return id;
	}
	public VoluntarioJson getVoluntario() {
		return voluntario;
	}
	
	public boolean isAceito() {
		return aceito;
	}

}
