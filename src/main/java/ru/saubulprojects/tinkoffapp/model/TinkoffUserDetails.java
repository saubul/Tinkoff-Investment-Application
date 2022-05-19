package ru.saubulprojects.tinkoffapp.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import ru.tinkoff.piapi.core.InvestApi;

@Data
public class TinkoffUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7570263426116776200L;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private InvestApi investApi;
	
	public TinkoffUserDetails(String username,
							  String password,
							  Collection<? extends GrantedAuthority> authorities,
							  String ssoToken) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.investApi = InvestApi.create(ssoToken);
	}
	
	public InvestApi getInvestApi() {
		return investApi;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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
	public boolean isEnabled() {
		return true;
	}
}
