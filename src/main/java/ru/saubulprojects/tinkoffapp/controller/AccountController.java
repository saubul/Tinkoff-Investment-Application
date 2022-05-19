package ru.saubulprojects.tinkoffapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class AccountController {
	
	@GetMapping
	public String getProfilePage() {
		
		return "account/profile";
	}
	
}
