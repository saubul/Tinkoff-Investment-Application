package ru.saubulprojects.tinkoffapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
	
	@GetMapping
	public String getHomePage() {
		
		return "content/home";
	}
	
	@GetMapping("/search")
	public String getSearchPage() {
		
		return "content/search";
	}

}
