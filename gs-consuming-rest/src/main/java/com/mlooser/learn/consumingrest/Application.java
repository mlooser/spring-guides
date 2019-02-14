package com.mlooser.learn.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Application {

	private final static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();

		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		log.info(quote.toString());
	}

}
