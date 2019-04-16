package com.devheaven.service.services;

import com.devheaven.service.models.Project;

import java.util.List;
import java.util.UUID;

public interface IProjectService {

    List<Project> findAll();

    List<Project> findAllForMember(UUID id);

    Project findById(UUID id);

    Project createProject(Project project);

    Project updateProject(Project project);

    Project addMember(Project project, UUID member);

    Project removeMember(Project project, UUID member);

    void deleteProject(Project project);

}
