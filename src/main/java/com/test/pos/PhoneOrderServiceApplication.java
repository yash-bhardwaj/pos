package com.test.pos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;

@SpringBootApplication
public class PhoneOrderServiceApplication {

	@Value("${phoneAPI.base.URL}")
	private String PHONE_API_BASE_URL;

	public static void main(String[] args) {
		SpringApplication.run(PhoneOrderServiceApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder
				.setConnectTimeout(Duration.ofMillis(3000))
				.setReadTimeout(Duration.ofMillis(3000))
				.build();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PHONE_API_BASE_URL));

		return restTemplate;
	}
}
