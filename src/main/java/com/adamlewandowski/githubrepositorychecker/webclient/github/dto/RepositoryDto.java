package com.adamlewandowski.githubrepositorychecker.webclient.github.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryDto {

    private String name;

    @JsonProperty("owner")
    private OwnerDto ownerDto;

}




