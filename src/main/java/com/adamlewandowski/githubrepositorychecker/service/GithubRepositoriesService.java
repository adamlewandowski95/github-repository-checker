package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.BranchInformationDto;
import com.adamlewandowski.githubrepositorychecker.controller.dto.RepositoryInformationDto;
import com.adamlewandowski.githubrepositorychecker.exception.NoSuchUserException;
import com.adamlewandowski.githubrepositorychecker.exception.UnexpectedStatusCodeException;
import com.adamlewandowski.githubrepositorychecker.pojo.BranchPojo;
import com.adamlewandowski.githubrepositorychecker.pojo.RepositoryPojo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@NoArgsConstructor
public class GithubRepositoriesService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<RepositoryInformationDto> getAllOwnerRepositories(String owner) throws NoSuchUserException, UnexpectedStatusCodeException {
        List<RepositoryPojo> repositoryPojo = getRepositoryInformationFromGithub(owner);
        return prepareRepositoryInformationDto(repositoryPojo);
    }

    private List<RepositoryPojo> getRepositoryInformationFromGithub(String owner) throws NoSuchUserException, UnexpectedStatusCodeException {
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

    private List<RepositoryInformationDto> prepareRepositoryInformationDto(List<RepositoryPojo> repositoryPojo) {
        return repositoryPojo.stream()
                .map(repo -> new RepositoryInformationDto(repo.getName(), repo.getOwner().getLogin(), getBranchesForRepository(repo)))
                .toList();
    }

    private List<BranchInformationDto> getBranchesForRepository(RepositoryPojo repositoryPojo) {
        List<BranchPojo> branchesForRepositoryFromGithub = getBranchesForRepositoryFromGithub(repositoryPojo.getOwner().getLogin(), repositoryPojo.getName());
        return prepareBranchInformationDto(branchesForRepositoryFromGithub);
    }

    private List<BranchPojo> getBranchesForRepositoryFromGithub(String owner, String repositoryName) {
        return webClientBuilder.build()
                .get()
                .uri(String.format("/repos/%s/%s/branches", owner, repositoryName))
                .retrieve()
                .bodyToFlux(BranchPojo.class)
                .collectList()
                .block();
    }

    private List<BranchInformationDto> prepareBranchInformationDto(List<BranchPojo> branchesForRepositoryFromGithub) {
        return branchesForRepositoryFromGithub.stream()
                .map(branch -> new BranchInformationDto(branch.getName(), branch.getCommit().getSha()))
                .toList();
    }
}
