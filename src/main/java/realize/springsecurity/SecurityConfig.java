package realize.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsService users;
	private TokenAuthenticationService tokenAuthenticationService;
	
	public SecurityConfig(UserDetailsService users) {
		super();
		this.users = users;
		this.tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", users);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/public/**").permitAll()
		.antMatchers("/api/private/v1/usuario/findByEmail").permitAll()
		.antMatchers("/api/private/v1/entidade/register").permitAll()
		.antMatchers("/api/private/v1/voluntario/register").permitAll()
		.antMatchers("/api/private/v1/sendmail/**").permitAll()
		.antMatchers("/api/private/**").authenticated()
		.antMatchers(HttpMethod.POST, "/api/login").permitAll()		
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService, users, 
				authenticationManager()), UsernamePasswordAuthenticationFilter.class)		
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
                UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users);
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }
	
}
