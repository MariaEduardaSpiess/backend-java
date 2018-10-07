package realize.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import realize.domain.impl.UsuarioImpl;
import realize.repo.UsuarioRepository;


@Service
public class UserLoginService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UsuarioImpl users = userRepo.findByEmail(login);
		if (users==null) {
			throw new UsernameNotFoundException("O usuario " + login + " não existe");
		}
		
		if (!users.isEnabled()) throw new UsernameNotFoundException("Usuário não habilitado!");
		
		return new UsuarioLogado(users);
	}

}
