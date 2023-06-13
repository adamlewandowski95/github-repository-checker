package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.RepositoryInformationDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.GithubRepositoriesClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class GithubRepositoriesService {

    private final GithubBranchesService githubBranchesService;
    private final GithubRepositoriesClient githubRepositoriesClient;

    public Mono<List<RepositoryInformationDto>> getAllOwnerRepositories(String owner) {
        return githubRepositoriesClient.getRepositoriesDto(owner)
                .flatMap(repositoryDtoList ->
                        Flux.fromIterable(repositoryDtoList)
                                .flatMap(repo ->
                                        githubBranchesService.getAllOwnerBranchesForRepository(repo)
                                                .map(branches ->
                                                        new RepositoryInformationDto(repo.getName(), repo.getOwnerDto().getLogin(), branches))
                                )
                                .collectList()
                );
    }
}
