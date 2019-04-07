package com.vsr.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vsr.microservices.currencyconversionservice.domain.ConvertedAmount;

//@FeignClient(name ="currency-exchange-service", url= "localhost:8001")
//@FeignClient(name ="currency-exchange-service")
//Route thru Zuul API gateway
@FeignClient(name ="netflix-zuul-api-gateway-server")
@RibbonClient(name ="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

//	@GetMapping(path = "/exchange/from/{from}/to/{to}")
	//for zuul
	@GetMapping(path = "currency-exchange-service/exchange/from/{from}/to/{to}")
	public ConvertedAmount getValue(@PathVariable("from") String from, @PathVariable ("to") String to);
}
