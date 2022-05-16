package ru.saubulprojects.tinkoffapp.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.saubulprojects.tinkoffapp.model.User;
import ru.saubulprojects.tinkoffapp.service.UserService;

@Controller
public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "auth/login";
	}
	
	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		return "auth/registration";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
		if(errors.hasErrors()) {
			return "auth/registration";
		}
		
		if(!Objects.equals(user.getPassword(), user.getMatchingPassword())) {
			model.addAttribute("passMismatchs", true);
			return "auth/registration";
		}
		
		if(userService.findByUsername(user.getUsername()) != null) {
			model.addAttribute("userExists", true);
			return "auth/registration";
		}
		
		userService.save(user);
		
		return "redirect:/";
	}
	
}
