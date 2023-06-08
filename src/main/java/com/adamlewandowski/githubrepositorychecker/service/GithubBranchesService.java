package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.BranchInformationDto;
import com.adamlewandowski.githubrepositorychecker.pojo.BranchPojo;
import com.adamlewandowski.githubrepositorychecker.pojo.RepositoryPojo;
import com.adamlewandowski.githubrepositorychecker.webclient.github.GithubBranchesClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GithubBranchesService {

    private final GithubBranchesClient githubBranchesClient;

    List<BranchInformationDto> getBranchesForRepository(RepositoryPojo repositoryPojo) {
        List<BranchPojo> branchesForRepositoryFromGithub = githubBranchesClient.getBranchesForRepository(repositoryPojo.getOwner().getLogin(), repositoryPojo.getName());
        return prepareBranchInformationDto(branchesForRepositoryFromGithub);
    }

    private List<BranchInformationDto> prepareBranchInformationDto(List<BranchPojo> branchesForRepositoryFromGithub) {
        return branchesForRepositoryFromGithub.stream()
                .map(branch -> new BranchInformationDto(branch.getName(), branch.getCommit().getSha()))
                .toList();
    }
}
