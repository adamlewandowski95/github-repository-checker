package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.RepositoryInformationDto;
import com.adamlewandowski.githubrepositorychecker.exception.NoSuchUserException;
import com.adamlewandowski.githubrepositorychecker.exception.UnexpectedStatusCodeException;
import com.adamlewandowski.githubrepositorychecker.pojo.RepositoryPojo;
import com.adamlewandowski.githubrepositorychecker.webclient.github.GithubRepositoriesClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GithubRepositoriesService {

    private final GithubBranchesService githubBranchesService;
    private final GithubRepositoriesClient githubRepositoriesClient;

    public List<RepositoryInformationDto> getAllOwnerRepositories(String owner) throws NoSuchUserException, UnexpectedStatusCodeException {
        List<RepositoryPojo> repositoryPojo = githubRepositoriesClient.getRepositoriesInformation(owner);
        return prepareRepositoryInformationDto(repositoryPojo);
    }

    private List<RepositoryInformationDto> prepareRepositoryInformationDto(List<RepositoryPojo> repositoryPojo) {
        return repositoryPojo.stream()
                .map(repo -> new RepositoryInformationDto(repo.getName(), repo.getOwner().getLogin(), githubBranchesService.getBranchesForRepository(repo)))
                .toList();
    }
}
