package realize.endpoint.json;

public class SendMailParam {

	private String mailTo;
	private String mailSubject;
	private String mailMessage;
	
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailTo() {
		return mailTo;
	}
	
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	
	public void setMailMessage(String mailMessage) {
		this.mailMessage = mailMessage;
	}
	
	public String getMailMessage() {
		return mailMessage;
	}
	
	
}
