package com.devheaven.service.services;

import com.devheaven.service.models.Project;
import com.devheaven.service.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project getProjectById(UUID id){
        return projectRepository.getOne(id);
    }
}
