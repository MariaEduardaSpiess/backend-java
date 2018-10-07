package realize.exeptions;

public class ErroNoProcessoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public ErroNoProcessoException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		if (message!=null) return message;
		return super.getMessage();
	}
}
