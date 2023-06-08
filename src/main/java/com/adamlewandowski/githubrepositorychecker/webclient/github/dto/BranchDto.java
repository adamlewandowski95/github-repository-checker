package com.adamlewandowski.githubrepositorychecker.webclient.github.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BranchDto {

    private String name;
    @JsonProperty("commit")
    private CommitDto commitDto;

}