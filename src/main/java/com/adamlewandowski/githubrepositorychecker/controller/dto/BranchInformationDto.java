package com.adamlewandowski.githubrepositorychecker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchInformationDto  implements Serializable {

    @Serial
    private static final long serialVersionUID = 376436963768417321L;

    private String name;
    private String commitSha;
}
