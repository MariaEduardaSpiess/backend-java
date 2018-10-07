package realize;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TesteEmail {

	public static void main(String[] args) {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("andrerspiess@gmail.com", "mfc413304."));
		email.setSSL(true);
		email.setCharset("utf-8");
		try {
			email.setFrom("andrerspiess@gmail.com");
			email.setSubject("Teste de e-mail");
			email.setMsg("Este é um teste de e-mail :-)");
			email.addTo("dudaspiess@gmail.com");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

}
