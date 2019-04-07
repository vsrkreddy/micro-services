package com.vsr.microservices.currencyexchangeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsr.microservices.currencyexchangeservice.domain.ExchangeValue;

public interface ExchangeValueRepo extends JpaRepository<ExchangeValue, Long> {
	
	ExchangeValue findByFromAndTo(String from, String to);

}
