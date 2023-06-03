package com.adamlewandowski.githubrepositorychecker.service;

import com.adamlewandowski.githubrepositorychecker.domain.Repositories;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@NoArgsConstructor
public class RepositoriesService {
    public List<Repositories> getRepositories() {

        return Collections.emptyList();
    }
}
