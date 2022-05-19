package ru.saubulprojects.tinkoffapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.saubulprojects.tinkoffapp.model.Share;
import ru.saubulprojects.tinkoffapp.service.ShareService;

@Controller
@RequestMapping("/share")
public class ShareController {
	
	private final ShareService shareService;
	
	public ShareController(ShareService shareService) {
		this.shareService = shareService;
	}
	
	@GetMapping
	public String getSharePage(@RequestParam(value = "ticker") String ticker,
							   Model model) {
		Share share = shareService.findByTicker(ticker);
		model.addAttribute("share", share);
		return "content/share";
	}
	
}
