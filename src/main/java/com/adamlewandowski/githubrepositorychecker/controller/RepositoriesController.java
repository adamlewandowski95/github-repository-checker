package com.adamlewandowski.githubrepositorychecker.controller;

import com.adamlewandowski.githubrepositorychecker.domain.Repositories;
import com.adamlewandowski.githubrepositorychecker.service.RepositoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repositories")
@RequiredArgsConstructor
public class RepositoriesController {

    private final RepositoriesService repositoriesService;

    @GetMapping
    public List<Repositories> getAll(){
        return repositoriesService.getRepositories();
    }

}
