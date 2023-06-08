package com.adamlewandowski.githubrepositorychecker.controller.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RepositoryInformationDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    private String repositoryName;
    private String ownerLogin;
    private List<BranchInformationDto> branches;
}
