package realize.exeptions;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public ValidationException(Set<ConstraintViolation<Object>> violations) {
		this.message = "ValidationException";
	}
	
	@Override
	public String getMessage() {
		if (message!=null) return message;
		return super.getMessage();
	}

}
