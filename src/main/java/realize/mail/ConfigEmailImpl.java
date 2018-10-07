package realize.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import realize.domain.impl.ConfigImpl;
import realize.endpoint.json.ConfigEmailParam;
import realize.repo.ConfigRepository;

@Component
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class ConfigEmailImpl implements ConfigEmail {

	@Autowired
	private ConfigRepository repo;
	
	@Override
	public String getHostName() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_HOSTNAME);
		return cfg.getConteudo();
	}

	@Override
	public Integer getSmtpPort() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_SMTPPORT);
		if (cfg.getConteudo()==null) return 587;
		int ret = Integer.parseInt(cfg.getConteudo());
		return ret;
	}

	@Override
	public boolean isSSL() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_SSL);
		return Boolean.parseBoolean(cfg.getConteudo());
	}

	@Override
	public boolean isTLS() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_TLS);
		return Boolean.parseBoolean(cfg.getConteudo());
	}

	@Override
	public String getCharSet() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_CHARSET);
		String ret = cfg.getConteudo();
		if (ret==null) ret = "utf-8";
		return ret;
	}

	@Override
	public String getMailFrom() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_FROM);
		return cfg.getConteudo();
	}

	@Override
	public String getUserName() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_USERNAME);
		return cfg.getConteudo();
	}

	@Override
	public String getPassword() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_PASSWORD);
		return cfg.getConteudo();
	}

	@Override
	public String getMailAdm() {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_ADM);
		return cfg.getConteudo();
	}

	public void save(ConfigEmailParam obj) {
		ConfigImpl cfg = find(MAIL_CHAVE, MAIL_HOSTNAME);
		cfg.setConteudo(obj.getHostName());
		repo.save(cfg);
		
		cfg = find(MAIL_CHAVE, MAIL_SMTPPORT);
		cfg.setConteudo(""+obj.getSmtpPort());
		repo.save(cfg);
		
		cfg = find(MAIL_CHAVE, MAIL_SSL);
		cfg.setConteudo(""+obj.isSSL());
		repo.save(cfg);
		
		cfg = find(MAIL_CHAVE, MAIL_TLS);
		cfg.setConteudo(""+obj.isTLS());
		repo.save(cfg);

		cfg = find(MAIL_CHAVE, MAIL_CHARSET);
		cfg.setConteudo(obj.getCharSet());
		repo.save(cfg);

		cfg = find(MAIL_CHAVE, MAIL_USERNAME);
		cfg.setConteudo(obj.getUserName());
		repo.save(cfg);

		cfg = find(MAIL_CHAVE, MAIL_PASSWORD);
		cfg.setConteudo(obj.getPassword());
		repo.save(cfg);

		cfg = find(MAIL_CHAVE, MAIL_FROM);
		cfg.setConteudo(obj.getMailFrom());
		repo.save(cfg);

		cfg = find(MAIL_CHAVE, MAIL_ADM);
		cfg.setConteudo(obj.getMailAdm());
		repo.save(cfg);

	}
	
	private ConfigImpl find(String chave, String variavel) {
		ConfigImpl cfg = repo.findByChaveVariavel(chave, variavel);
		if (cfg==null) {
			cfg = new ConfigImpl();
			cfg.setChave(chave);
			cfg.setVariavel(variavel);
		}
		return cfg;
	}

}
