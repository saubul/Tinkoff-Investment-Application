package ru.saubulprojects.tinkoffapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ru.saubulprojects.tinkoffapp.handler.SuccessLoginHandler;
import ru.saubulprojects.tinkoffapp.service.UserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	private final SuccessLoginHandler successLoginHandler;
	
	public SecurityConfiguration(PasswordEncoder passwordEncoder,
								 UserService userService,
								 SuccessLoginHandler successLoginHandler) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.successLoginHandler = successLoginHandler;
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder);
		return auth;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		String[] staticResources = {
				"/images/**"
		};
		
		http
			.authorizeRequests()
				.antMatchers(staticResources).permitAll()
				.antMatchers("/registration").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/search")
				.permitAll()
				.successHandler(successLoginHandler)
				.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.permitAll();
			
	}
	
}
