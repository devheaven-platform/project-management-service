package nl.devheaven.service.services;

import nl.devheaven.service.models.Project;
import nl.devheaven.service.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Interacts with the data access layer.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Gets all projects in the system.
     *
     * @return a list of projects.
     */
    public List<Project> findAll() {
        return projectRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    /**
     * Gets all projects for a specific member.
     *
     * @param id the id of the member to get the
     *           projects for.
     * @return a list of projects.
     */
    public List<Project> findAllForMember(UUID id) {
        return projectRepository.findByMembersContains(id, Sort.by(Sort.Direction.DESC, "name"));
    }

    /**
     * Gets one project by its id.
     *
     * @param id the id of the project to retrieve.
     * @return the found project or null.
     */
    public Project findById(UUID id) {
        return projectRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new project to the system.
     *
     * @param project the project to add.
     * @return the new project.
     */
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Updates a existing project.
     *
     * @param project the project to update.
     * @return the updated project.
     */
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Adds a member to a project.
     *
     * @param project the project to add the member to.
     * @param member  the id of the member to add.
     * @return the updated project.
     */
    public Project addMember(Project project, UUID member) {
        project.addMember(member);
        return projectRepository.save(project);
    }

    /**
     * Removes a member from a project.
     *
     * @param project the project to remove the member from.
     * @param member  the id of the member to remove.
     * @return the updated project.
     */
    public Project removeMember(Project project, UUID member) {
        project.removeMember(member);
        return projectRepository.save(project);
    }

    /**
     * Deletes a project.
     *
     * @param project the project to delete.
     */
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
