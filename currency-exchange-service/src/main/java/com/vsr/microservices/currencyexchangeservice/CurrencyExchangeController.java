package com.vsr.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vsr.microservices.currencyexchangeservice.domain.ExchangeValue;
import com.vsr.microservices.currencyexchangeservice.repo.ExchangeValueRepo;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepo exchangeValueRepo;

	@GetMapping(path = "/exchange/from/{from}/to/{to}")
	public ExchangeValue getValue(@PathVariable String from, @PathVariable String to) {
//		 ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		 ExchangeValue exchangeValue = exchangeValueRepo.findByFromAndTo(from, to);
		 
		 exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		 return exchangeValue;
	}
}
