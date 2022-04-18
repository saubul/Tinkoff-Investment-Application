package ru.saubulprojects.tinkoffapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.tinkoff.piapi.core.InvestApi;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public InvestApi api() {
		return InvestApi.create(System.getenv("ssoToken"));
	}
	
}
