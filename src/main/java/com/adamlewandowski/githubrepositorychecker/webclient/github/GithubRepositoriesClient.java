package com.adamlewandowski.githubrepositorychecker.webclient.github;

import com.adamlewandowski.githubrepositorychecker.exception.NoSuchUserException;
import com.adamlewandowski.githubrepositorychecker.exception.UnexpectedStatusCodeException;
import com.adamlewandowski.githubrepositorychecker.pojo.RepositoryPojo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class GithubRepositoriesClient {

    private final WebClient.Builder webClientBuilder;

    public List<RepositoryPojo> getRepositoriesInformation(String owner) throws NoSuchUserException, UnexpectedStatusCodeException {
        return webClientBuilder.build()
                .get()
                .uri(String.format("/users/%s/repos?type=owner", owner))
                .retrieve()
                .bodyToFlux(RepositoryPojo.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getStatusCode().value() == 404 ?
                                Mono.error(new NoSuchUserException("There is no such user in Github")) :
                                Mono.error(new UnexpectedStatusCodeException("Unexpected error from API")))
                .collectList()
                .block();
    }
}
