package realize.endpoint.json;

public class ParticipanteEventoParam {

	private Long eventoId;
	private Long voluntarioId;
	
	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}
	public Long getEventoId() {
		return eventoId;
	}
	
	public void setVoluntarioId(Long voluntarioId) {
		this.voluntarioId = voluntarioId;
	}
	
	public Long getVoluntarioId() {
		return voluntarioId;
	}
	
}
