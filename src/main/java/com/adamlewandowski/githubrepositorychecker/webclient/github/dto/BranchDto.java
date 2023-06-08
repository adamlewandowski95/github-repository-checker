package com.adamlewandowski.githubrepositorychecker.webclient.github.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
public class BranchDto {

    private String name;
    @JsonProperty("commit")
    private CommitDto commitDto;

}