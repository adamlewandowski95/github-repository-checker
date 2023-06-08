package com.adamlewandowski.githubrepositorychecker;

import com.adamlewandowski.githubrepositorychecker.controller.dto.BranchInformationDto;
import com.adamlewandowski.githubrepositorychecker.controller.dto.RepositoryInformationDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.OwnerDto;
import com.adamlewandowski.githubrepositorychecker.webclient.github.dto.RepositoryDto;

import java.util.List;

public class TestConstants {
    public static final String OWNER = "TestOwner";
    public static final BranchInformationDto MAIN_BRANCH = new BranchInformationDto("main", "9984e9a9640398d0d36c631237c9fbd6bde0dad8");
    public static final BranchInformationDto FEATURE_BRANCH = new BranchInformationDto("feature", "1234e9a9640398d0d36c632b77c9fbd6bde0d123");
    public static final List<BranchInformationDto> BRANCH_INFORMATION_DTO_LIST = List.of(MAIN_BRANCH, FEATURE_BRANCH);
    public static final RepositoryInformationDto FIRST_REPOSITORY_INFORMATION_DTO = new RepositoryInformationDto("first-repository", OWNER, BRANCH_INFORMATION_DTO_LIST);
    public static final RepositoryInformationDto SECOND_REPOSITORY_INFORMATION_DTO = new RepositoryInformationDto("second-repository", OWNER, BRANCH_INFORMATION_DTO_LIST);
    public static final List<RepositoryInformationDto> REPOSITORY_INFORMATION_DTO_LIST = List.of(FIRST_REPOSITORY_INFORMATION_DTO, SECOND_REPOSITORY_INFORMATION_DTO);
    public static final RepositoryDto FIRST_REPOSITORY_DTO = new RepositoryDto("first-repository", new OwnerDto(OWNER));
    public static final RepositoryDto SECOND_REPOSITORY_DTO = new RepositoryDto("second-repository", new OwnerDto(OWNER));
    public static final List<RepositoryDto> REPOSITORY_DTO_LIST = List.of(FIRST_REPOSITORY_DTO, SECOND_REPOSITORY_DTO);
}
