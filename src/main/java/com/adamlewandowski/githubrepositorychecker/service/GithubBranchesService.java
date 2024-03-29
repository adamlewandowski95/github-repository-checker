package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.BranchInformationDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.GithubBranchesClient;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.BranchDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.RepositoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class GithubBranchesService {

    private final GithubBranchesClient githubBranchesClient;

    Mono<List<BranchInformationDto>> getAllOwnerBranchesForRepository(RepositoryDto repositoryDto) {
        return githubBranchesClient.getBranchesForRepository(repositoryDto.getOwnerDto().getLogin(), repositoryDto.getName())
                .map(this::prepareBranchInformationDto);
    }

    private List<BranchInformationDto> prepareBranchInformationDto(List<BranchDto> branchesForRepositoryFromGithub) {
        return branchesForRepositoryFromGithub.stream()
                .map(branch -> new BranchInformationDto(branch.getName(), branch.getCommitDto().getSha()))
                .toList();
    }
}
