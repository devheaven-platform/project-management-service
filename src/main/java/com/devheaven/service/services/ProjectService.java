package com.devheaven.service.services;

import com.devheaven.service.models.Project;
import com.devheaven.service.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Project> findAllForMember(String id) {
        return projectRepository.findByMembersContains(id, Sort.by(Sort.Direction.ASC, "name"));
    }

    public Project getProjectById(UUID id){
        return projectRepository.getOne(id);
    }
}
