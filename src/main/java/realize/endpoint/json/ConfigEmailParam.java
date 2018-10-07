package realize.endpoint.json;

import realize.mail.ConfigEmail;

public class ConfigEmailParam implements ConfigEmail {
	
	private String hostName;
	private Integer smtpPort;
	private boolean ssl;
	private boolean tls;
	private String charSet;
	private String userName;
	private String password;
	private String mailFrom;
	private String mailAdm;

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	@Override
	public String getHostName() {
		return hostName;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}
	@Override
	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}
	@Override
	public boolean isSSL() {
		return ssl;
	}

	public void setTls(boolean tls) {
		this.tls = tls;
	}
	@Override
	public boolean isTLS() {
		return tls;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	@Override
	public String getCharSet() {
		return charSet;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getPassword() {
		return password;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	@Override
	public String getMailFrom() {
		return mailFrom;
	}
	
	public void setMailAdm(String mailAdm) {
		this.mailAdm = mailAdm;
	}
	@Override
	public String getMailAdm() {
		return mailAdm;
	}

}
