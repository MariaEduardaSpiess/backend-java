package realize.mail;

public interface ConfigEmail {

	static final String MAIL_CHAVE="EMAIL";
	static final String MAIL_HOSTNAME="MAIL_HOSTNAME";
	static final String MAIL_SMTPPORT="MAIL_SMTPPORT";
	static final String MAIL_SSL="MAIL_SSL";
	static final String MAIL_TLS="MAIL_TLS";
	static final String MAIL_CHARSET="MAIL_CHARSET";
	
	static final String MAIL_USERNAME="MAIL_USERNAME";
	static final String MAIL_PASSWORD="MAIL_PASSWORD";
	
	static final String MAIL_FROM="MAIL_FROM";
	
	static final String MAIL_ADM="MAIL_ADM";

	String getHostName();
	Integer getSmtpPort();
	boolean isSSL();
	boolean isTLS();
	String getCharSet();
	
	String getUserName();
	String getPassword();
	
	String getMailFrom();
	String getMailAdm();

}
