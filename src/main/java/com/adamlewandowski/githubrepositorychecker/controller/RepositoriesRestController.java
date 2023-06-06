package com.adamlewandowski.githubrepositorychecker.controller;

import com.adamlewandowski.githubrepositorychecker.controller.dto.RepositoryInformationDto;
import com.adamlewandowski.githubrepositorychecker.service.GithubRepositoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repositories")
@RequiredArgsConstructor
public class RepositoriesRestController {

    private final GithubRepositoriesService githubRepositoriesService;

    @GetMapping
    public List<RepositoryInformationDto> getAll(){
        return githubRepositoriesService.getAllOwnerRepositories("adamlewandowski95");
    }

}
