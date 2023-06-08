package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.controller.dto.BranchInformationDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.GithubBranchesClient;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.RepositoryDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static com.adamlewandowski.githubrepositorychecker.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.Mockito.when;

@SpringBootTest
class GithubBranchesServiceTest {

    @Mock
    private GithubBranchesClient githubBranchesClient;

    @InjectMocks
    private GithubBranchesService githubBranchesService;

    @ParameterizedTest
    @MethodSource("repositoriesDto")
    void getBranchesForRepository_ownerExistInGithub_returnsListOfRepositories(RepositoryDto repositoryDto) {
        //given
        when(githubBranchesClient.getBranchesForRepository(OWNER, repositoryDto.getName())).thenReturn(BRANCH_DTO_LIST);
        //when
        List<BranchInformationDto> allOwnerRepositories = githubBranchesService.getAllOwnerBranchesForRepository(repositoryDto);
        //then
        assertThat(allOwnerRepositories).containsAll(BRANCH_INFORMATION_DTO_LIST).hasSize(2);
    }

    private static Stream<Arguments> repositoriesDto() {
        return Stream.of(
                of(FIRST_REPOSITORY_DTO),
                of(SECOND_REPOSITORY_DTO));
    }

}