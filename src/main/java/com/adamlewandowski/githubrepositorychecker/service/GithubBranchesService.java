package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.BranchInformationDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.BranchDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.RepositoryDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.GithubBranchesClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GithubBranchesService {

    private final GithubBranchesClient githubBranchesClient;

    List<BranchInformationDto> getBranchesForRepository(RepositoryDto repositoryDto) {
        List<BranchDto> branchesForRepositoryFromGithub = githubBranchesClient.getBranchesForRepository(repositoryDto.getOwnerDto().getLogin(), repositoryDto.getName());
        return prepareBranchInformationDto(branchesForRepositoryFromGithub);
    }

    private List<BranchInformationDto> prepareBranchInformationDto(List<BranchDto> branchesForRepositoryFromGithub) {
        return branchesForRepositoryFromGithub.stream()
                .map(branch -> new BranchInformationDto(branch.getName(), branch.getCommitDto().getSha()))
                .toList();
    }
}
