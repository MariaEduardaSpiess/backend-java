package realize.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import realize.domain.impl.UsuarioImpl;
import realize.endpoint.core.AbstractCRUDEndpoint;
import realize.endpoint.core.FilteredRepository;
import realize.endpoint.core.ValidaToken;
import realize.exeptions.ErroNoProcessoException;
import realize.exeptions.InvalidAuthException;
import realize.exeptions.ValidationException;
import realize.repo.UsuarioRepository;
import realize.springsecurity.LoginDTO;

@RestController
@RequestMapping( "/api/private/v1/usuario" )
public class UsuarioEndpoint extends AbstractCRUDEndpoint<UsuarioImpl, Long> {

	@Autowired
	private ValidaToken validaToken;

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public FilteredRepository<UsuarioImpl, Long> getRepository() {
		return repo;
	}

	@GetMapping("findByToken")
	public UsuarioImpl findByToken(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		validaToken.valida(request);
		return validaToken.getUsuario();
	}
	
	@PostMapping("addimagem") 
	@Transactional(rollbackFor=Exception.class)
	public String addimagem(HttpServletRequest request,
			@RequestBody String imagem)
			throws InvalidAuthException, ErroNoProcessoException, ValidationException {
		validaToken.valida(request);
		UsuarioImpl usuario = validaToken.getUsuario();
		usuario.setImagem(imagem);
		repo.save(usuario);
		return "sucesso";
	}

	@GetMapping("getimagem")
	public String getImagemPerfil(HttpServletRequest request) throws InvalidAuthException, ErroNoProcessoException {
		validaToken.valida(request);
		UsuarioImpl user = validaToken.getUsuario();
		return user.getImagem();
	}
	
	@PutMapping("findByEmail")
	public UsuarioImpl findByEmail(HttpServletRequest request, @RequestBody LoginDTO loginDTO) throws ErroNoProcessoException {
		UsuarioImpl user = repo.findByEmail(loginDTO.getUsername()); 
		if (user==null) throw new ErroNoProcessoException(String.format("Usuário %s não localizado!",loginDTO.getUsername()));
		return user;
	}

}
