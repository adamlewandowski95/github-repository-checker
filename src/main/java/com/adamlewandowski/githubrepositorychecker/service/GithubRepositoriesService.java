package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.RepositoryInformationDto;
import com.adamlewandowski.githubrepositorychecker.exception.NoSuchUserException;
import com.adamlewandowski.githubrepositorychecker.exception.UnexpectedStatusCodeException;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.RepositoryDto;
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
        List<RepositoryDto> repositoryDtoList = githubRepositoriesClient.getRepositoriesInformation(owner);
        return prepareRepositoryInformationDto(repositoryDtoList);
    }

    private List<RepositoryInformationDto> prepareRepositoryInformationDto(List<RepositoryDto> repositoryDtoList) {
        return repositoryDtoList.stream()
                .map(repo -> new RepositoryInformationDto(repo.getName(), repo.getOwnerDto().getLogin(), githubBranchesService.getBranchesForRepository(repo)))
                .toList();
    }
}
