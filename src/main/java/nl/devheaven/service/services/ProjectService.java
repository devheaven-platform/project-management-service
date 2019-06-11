package nl.devheaven.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.devheaven.service.models.Project;
import nl.devheaven.service.repositories.ProjectRepository;
import nl.devheaven.service.requests.AddBoardRequest;
import nl.devheaven.service.requests.DeleteBoardRequest;
import nl.devheaven.service.requests.DeleteProjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Interacts with the data access layer.
 */
@Service
public class ProjectService {

    private final static Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public ProjectService() {
        objectMapper = new ObjectMapper();
    }

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
        return projectRepository.findByMembersContainsOrOwnerEquals(id, Sort.by(Sort.Direction.DESC, "name"));
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

    @Transactional
    @KafkaListener(topics = "db.task-management.create-board", autoStartup = "${spring.kafka.enabled:true}")
    public void createBoard(String message) {
        try {
            AddBoardRequest addBoardRequest = objectMapper.readValue(message, AddBoardRequest.class);

            Project project = projectRepository.findById(UUID.fromString(addBoardRequest.getProject())).orElse(null);

            if (project != null) {
                project.addBoard(UUID.fromString(addBoardRequest.getBoard()));
                projectRepository.save(project);
            }
        } catch (IOException e) {
            logger.error("An error occurred while adding the board", e);
        }
    }

    @Transactional
    @KafkaListener(topics = "db.task-management.delete-board", autoStartup = "${spring.kafka.enabled:true}")
    public void deleteBoard(String message) {
        try {
            DeleteBoardRequest deleteBoardRequest = objectMapper.readValue(message, DeleteBoardRequest.class);

            Project project = projectRepository.findFirstByBoardsContains(UUID.fromString(deleteBoardRequest.getBoard())).orElse(null);

            if (project != null) {
                project.removeBoard(UUID.fromString(deleteBoardRequest.getBoard()));
                projectRepository.save(project);
            }
        } catch (IOException e) {
            logger.error("An error occurred while removing the board", e);
        }
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
     * @return true if the project was deleted.
     */
    public boolean deleteProject(Project project) {
        try {
            projectRepository.delete(project);

            // Delete all boards associated with the project
            DeleteProjectRequest message = new DeleteProjectRequest();
            message.setProject(project.getId().toString());
            message.setBoards(project.getBoards().stream().map(UUID::toString).collect(Collectors.toList()));
            kafkaTemplate.send("db.project-management.delete-project", objectMapper.writeValueAsString(message));

            return true;
        } catch (JsonProcessingException e) {
            logger.error("An error occurred while deleting the project", e);
            return false;
        }
    }
}
