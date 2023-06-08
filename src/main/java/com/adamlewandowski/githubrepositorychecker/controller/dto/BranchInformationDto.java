package com.adamlewandowski.githubrepositorychecker.controller.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BranchInformationDto  implements Serializable {

    @Serial
    private static final long serialVersionUID = 376436963768417321L;

    private String name;
    private String commitSha;
}
