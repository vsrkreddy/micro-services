package com.vsr.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vsr.microservices.currencyconversionservice.domain.ConvertedAmount;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	@GetMapping(path = "/conversion/{from}/to/{to}/quantity/{quantity}")
	public ConvertedAmount convertAmountFromTO(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		
		
		
		//Method#1
		/*Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<ConvertedAmount> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/exchange/from/{from}/to/{to}", ConvertedAmount.class, uriVariables);
		ConvertedAmount response = responseEntity.getBody();
		
		return new ConvertedAmount(1L, from, to, quantity, response.getConversionMultiple(),
				quantity.multiply(response.getConversionMultiple()), response.getPort());*/
		
		//Method#2
		// Feign solves the problem of making service calls

		ConvertedAmount response = currencyExchangeServiceProxy.getValue(from, to);

		return new ConvertedAmount(1L, from, to, quantity, response.getConversionMultiple(),
				quantity.multiply(response.getConversionMultiple()), response.getPort());
		
	}

}
