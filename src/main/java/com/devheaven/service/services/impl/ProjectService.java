package com.devheaven.service.services.impl;

import com.devheaven.service.models.Project;
import com.devheaven.service.repositories.ProjectRepository;
import com.devheaven.service.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<Project> findAllForMember(UUID id) {
        return projectRepository.findByMembersContains(id, Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Project findById(UUID id){
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project addMember(Project project, UUID member) {
        project.addMember(member);
        return projectRepository.save(project);
    }

    @Override
    public Project removeMember(Project project, UUID member) {
        project.removeMember(member);
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
