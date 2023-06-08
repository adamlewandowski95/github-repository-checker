package com.adamlewandowski.githubrepositorychecker.webclient.github;

import com.adamlewandowski.githubrepositorychecker.pojo.BranchPojo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@AllArgsConstructor
public class GithubBranchesClient {

    private final WebClient.Builder webClientBuilder;

    public List<BranchPojo> getBranchesForRepository(String owner, String repositoryName) {
        return webClientBuilder.build()
                .get()
                .uri(String.format("/repos/%s/%s/branches", owner, repositoryName))
                .retrieve()
                .bodyToFlux(BranchPojo.class)
                .collectList()
                .block();
    }
}
