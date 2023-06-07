package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.BranchInformationDto;
import com.adamlewandowski.githubrepositorychecker.pojo.BranchPojo;
import com.adamlewandowski.githubrepositorychecker.pojo.RepositoryPojo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class GithubBranchesService {

    private WebClient.Builder webClientBuilder;

    List<BranchInformationDto> getBranchesForRepository(RepositoryPojo repositoryPojo) {
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
