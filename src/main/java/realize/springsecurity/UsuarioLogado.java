package realize.springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import realize.domain.impl.UsuarioImpl;

public class UsuarioLogado implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

	private boolean enabled;
	private boolean entidade;
	
	public UsuarioLogado(UsuarioImpl users) {
		this.username = users.getEmail();
		this.password = users.getUserPassword();
		this.enabled = true;
		this.entidade = users.getTipo().equals("E");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (PapelUsuario privilege : roles) {
//            authorities.add(new SimpleGrantedAuthority(privilege.getPapel().getPapel()));
//        }

        authorities.add(new SimpleGrantedAuthority("USER_PRIVILEGE"));
        
        if (entidade) {
            authorities.add(new SimpleGrantedAuthority("ENTIDADE_PRIVILEGE"));
        }
        return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioLogado other = (UsuarioLogado) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
