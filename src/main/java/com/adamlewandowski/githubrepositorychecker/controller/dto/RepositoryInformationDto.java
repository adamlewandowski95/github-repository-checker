package com.adamlewandowski.githubrepositorychecker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryInformationDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    private String repositoryName;
    private String ownerLogin;
    private List<BranchInformationDto> branches;
}
