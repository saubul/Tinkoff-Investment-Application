package ru.saubulprojects.tinkoffapp.service.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.saubulprojects.tinkoffapp.exception.ShareNotFoundException;
import ru.saubulprojects.tinkoffapp.model.Share;
import ru.saubulprojects.tinkoffapp.model.TinkoffUserDetails;
import ru.saubulprojects.tinkoffapp.service.ShareService;
import ru.saubulprojects.tinkoffapp.service.UserService;
import ru.tinkoff.piapi.core.InstrumentsService;
import ru.tinkoff.piapi.core.InvestApi;

@Service
public class ShareServiceImpl implements ShareService{

	private TinkoffUserDetails user;
	private final UserService userService;
	
	public ShareServiceImpl(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Share findByTicker(String ticker) {
		// get custom user details that contains InvestApi field
		user = (TinkoffUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		InvestApi investApi = user.getInvestApi();
		InstrumentsService instrumentsService = investApi.getInstrumentsService();
		CompletableFuture<Optional<ru.tinkoff.piapi.contract.v1.Share>> shareCF = instrumentsService.getShareByTicker(ticker, "SPBXM");
		Optional<ru.tinkoff.piapi.contract.v1.Share> oShare = Optional.empty();
		try {
			oShare = shareCF.join();
		} catch (Exception ex) {
			
		}
		
		if(oShare.isEmpty()) {
			throw new ShareNotFoundException(String.format("Share %S not found", ticker));
		}
		
		ru.tinkoff.piapi.contract.v1.Share share = oShare.get();
		
		return new Share(share.getName(), share.getTicker(), share.getFigi(), share.getClassCode());
	}

}
