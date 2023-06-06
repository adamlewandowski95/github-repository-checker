package com.adamlewandowski.githubrepositorychecker;

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

	public static void main(String[] args) {
		SpringApplication.run(GithubRepositoryChecker.class, args);
	}

}
