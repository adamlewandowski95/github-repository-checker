package com.adamlewandowski.githubrepositorychecker.webclient.github.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
public class RepositoryDto {

    private String name;

    @JsonProperty("owner")
    private OwnerDto ownerDto;

}




