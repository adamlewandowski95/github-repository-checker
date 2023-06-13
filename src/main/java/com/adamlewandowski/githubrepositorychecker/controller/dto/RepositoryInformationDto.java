package com.adamlewandowski.githubrepositorychecker.controller.dto;

import java.util.List;

public record RepositoryInformationDto(String repositoryName, String ownerLogin, List<BranchInformationDto> branches) {

}
