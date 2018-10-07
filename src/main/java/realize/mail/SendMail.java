package realize.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import realize.endpoint.json.SendMailParam;

@Component
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class SendMail {
	
	@Autowired
	private ConfigEmailImpl cfgmail;

	private void send(SendMailParam smp) throws EmailException {
		if (cfgmail.getHostName()==null) throw new EmailException("Necessário configurar e-mail!");
		
		SimpleEmail email = new SimpleEmail();
		email.setHostName(cfgmail.getHostName());
		email.setSmtpPort(cfgmail.getSmtpPort());
		email.setAuthenticator(new DefaultAuthenticator(cfgmail.getUserName(), cfgmail.getPassword()));
		email.setSSL(cfgmail.isSSL());
		email.setTLS(cfgmail.isTLS());
		email.setCharset(cfgmail.getCharSet());
		email.setFrom(cfgmail.getMailFrom());

		email.addTo(smp.getMailTo());
		email.setSubject(smp.getMailSubject());
		email.setMsg(smp.getMailMessage());
		email.send();
	}

	public void send(String mailTo, String subject, String message) throws EmailException {
		SendMailParam smp = new SendMailParam();
		smp.setMailTo(mailTo);
		smp.setMailSubject(subject);
		smp.setMailMessage(message);
		send(smp);
	}

	public void sendAdm(String subject, String message) throws EmailException {
		String mailTo = cfgmail.getMailAdm();
		send(mailTo, subject, message);
	}

}
