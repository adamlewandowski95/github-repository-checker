package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.RepositoryInformationDto;
import com.adamlewandowski.githubrepositorychecker.exception.NoSuchUserException;
import com.adamlewandowski.githubrepositorychecker.exception.UnexpectedStatusCodeException;
import com.adamlewandowski.githubrepositorychecker.webclient.github.GithubRepositoriesClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.adamlewandowski.githubrepositorychecker.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class GithubRepositoriesServiceTest {

    @Mock
    private GithubBranchesService githubBranchesService;

    @Mock
    private GithubRepositoriesClient githubRepositoriesClient;

    @InjectMocks
    private GithubRepositoriesService githubRepositoriesService;

    @Test
    void getAllOwnerRepositories_ownerExistInGithub_returnsListOfRepositories() {
        //given
        when(githubRepositoriesClient.getRepositoriesDto(OWNER)).thenReturn(REPOSITORY_DTO_LIST);
        when(githubBranchesService.getAllOwnerBranchesForRepository(FIRST_REPOSITORY_DTO)).thenReturn(BRANCH_INFORMATION_DTO_LIST);
        when(githubBranchesService.getAllOwnerBranchesForRepository(SECOND_REPOSITORY_DTO)).thenReturn(BRANCH_INFORMATION_DTO_LIST);
        //when
        List<RepositoryInformationDto> allOwnerRepositories = githubRepositoriesService.getAllOwnerRepositories(OWNER);
        //then
        assertThat(allOwnerRepositories).containsAll(REPOSITORY_INFORMATION_DTO_LIST).hasSize(2);
    }

    @Test
    void getAllOwnerRepositories_ownerNotExistInGithub_throwsNoSuchUserException() {
        //given
        when(githubRepositoriesClient.getRepositoriesDto(OWNER)).thenThrow(new NoSuchUserException("There is no such user in Github"));
        //when
        //then
        assertThatThrownBy(() -> githubRepositoriesClient.getRepositoriesDto(OWNER))
                .isInstanceOf(NoSuchUserException.class)
                        .hasMessage("There is no such user in Github");
    }

    @Test
    void getAllOwnerRepositories_ownerNotExistInGithub_throwsUnexpectedStatusCodeException() {
        //given
        when(githubRepositoriesClient.getRepositoriesDto(OWNER)).thenThrow(new UnexpectedStatusCodeException("Unexpected error from API"));
        //when
        //then
        assertThatThrownBy(() -> githubRepositoriesClient.getRepositoriesDto(OWNER))
                .isInstanceOf(UnexpectedStatusCodeException.class)
                .hasMessage("Unexpected error from API");
    }
}