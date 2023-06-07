package com.adamlewandowski.githubrepositorychecker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GithubRepositoryChecker {

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder().baseUrl("https://api.github.com");
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(GithubRepositoryChecker.class, args);
	}

}
