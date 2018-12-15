package realize.endpoint.core;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import realize.domain.impl.EntidadeImpl;
import realize.domain.impl.UsuarioImpl;
import realize.domain.impl.VoluntarioImpl;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.repo.EntidadeRepository;
import realize.repo.UsuarioRepository;
import realize.repo.VoluntarioRepository;
import realize.springsecurity.TokenAuthenticationService;

@Component
public class ValidaToken {

	@Autowired
	private VoluntarioRepository repoVol;
	
	@Autowired
	private EntidadeRepository repoEnt;

	@Autowired
	private UsuarioRepository repoUser;

	@Resource
	private TokenAuthenticationService tokenAS;

	private Authentication user;
	
	
	public void valida(HttpServletRequest request) throws InvalidAuthException {
		user = tokenAS.getAuthentication(request);
		if (user==null) throw new InvalidAuthException();
		
	}

	public VoluntarioImpl getVoluntario() throws ErroNoProcessoException {
		VoluntarioImpl vol = repoVol.findByEmail(user.getName());
		if (vol==null) throw new ErroNoProcessoException("Voluntário não encontrado!");
		return vol;
	}

	public EntidadeImpl getEntidade() throws ErroNoProcessoException {
		EntidadeImpl ent = repoEnt.findByEmail(user.getName());
		if (ent==null) throw new ErroNoProcessoException("Entidade não encontrada!");
		return ent;
	}

	public UsuarioImpl getUsuario() throws ErroNoProcessoException {
		UsuarioImpl usuario = repoUser.findByEmail(user.getName());
		if (usuario==null) throw new ErroNoProcessoException("Usuário não encontrado!");
		return usuario;
	}

}
