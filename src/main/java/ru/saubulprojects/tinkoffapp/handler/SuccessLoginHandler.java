package ru.saubulprojects.tinkoffapp.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ru.saubulprojects.tinkoffapp.model.TinkoffUserDetails;
import ru.saubulprojects.tinkoffapp.service.UserService;
import ru.tinkoff.piapi.core.InvestApi;

@Component
public class SuccessLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	private final UserService userService;
	
	public SuccessLoginHandler(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws ServletException, IOException {
		
		
		TinkoffUserDetails user = (TinkoffUserDetails) authentication.getPrincipal();
		user.setInvestApi(InvestApi.createReadonly(userService.findByUsername(user.getUsername()).getSsotoken()));
		super.onAuthenticationSuccess(request, response, authentication);
		
	}

}
