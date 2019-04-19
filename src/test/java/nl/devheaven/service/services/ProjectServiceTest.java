package nl.devheaven.service.services;

import nl.devheaven.service.models.Project;
import nl.devheaven.service.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.notNull;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;
    @MockBean
    private ProjectRepository projectRepository;
    private Project project;
    private List<UUID> members;

    @Before
    public void setUp() {
        project = new Project();
        members = new ArrayList<>();
        project.setId(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"));
        project.setName("Project 1");
        project.setDescription("Description 1");
        project.setBudget(10);
        project.setStart(new Date());
        project.setArchived(false);
        project.setMembers(members);
        project.addMember(UUID.fromString("4562366c-2d59-44a5-b385-8848144706fe"));

        Mockito.when(projectRepository.findAll(Sort.by(Sort.Direction.DESC, "name"))).thenReturn(new ArrayList<Project>() {{
            add(project);
        }});

        Mockito.when(projectRepository.findByMembersContains(UUID.fromString("4562366c-2d59-44a5-b385-8848144706fe"), Sort.by(Sort.Direction.DESC, "name"))).thenReturn(new ArrayList<Project>() {{
            add(project);
        }});
        Mockito.when(projectRepository.findByMembersContains(UUID.fromString("74e52b1d-c0db-4a89-a93f-2439a2c208f8"), Sort.by(Sort.Direction.DESC, "name"))).thenReturn(new ArrayList<>());

        Mockito.when(projectRepository.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"))).thenReturn(Optional.of(project));
        Mockito.when(projectRepository.findById(UUID.fromString("b9c0dce0-b047-43f8-960d-53aca1a9fa26"))).thenReturn(Optional.empty());

        Mockito.when(projectRepository.save(notNull())).thenReturn(project);
    }

    @Test
    public void findAll() {
        List<Project> projects = projectService.findAll();

        assertEquals("Service returned an invalid project count", 1, projects.size());
        assertEquals("Project has an invalid id", UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"), projects.get(0).getId());
        assertEquals("Project has an invalid name", "Project 1", projects.get(0).getName());
        assertEquals("Project has an invalid description", "Description 1", projects.get(0).getDescription());
        assertEquals("Project has an invalid budget", 10f, projects.get(0).getBudget(), 0.0f);
        assertFalse("Project has an invalid archived value", projects.get(0).isArchived());
    }

    @Test
    public void findAllForMember() {
        List<Project> projects = projectService.findAllForMember(UUID.fromString("4562366c-2d59-44a5-b385-8848144706fe"));

        assertEquals("Service returned an invalid project count", 1, projects.size());
        assertEquals("Project has an invalid id", UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"), projects.get(0).getId());
        assertEquals("Project has an invalid name", "Project 1", projects.get(0).getName());
        assertEquals("Project has an invalid description", "Description 1", projects.get(0).getDescription());
        assertEquals("Project has an invalid budget", 10f, projects.get(0).getBudget(), 0.0f);
        assertFalse("Project has an invalid archived value", projects.get(0).isArchived());
    }

    @Test
    public void findAllForMemberWrongId() {
        List<Project> projects = projectService.findAllForMember(UUID.fromString("74e52b1d-c0db-4a89-a93f-2439a2c208f8"));

        assertEquals("Service returned an invalid project count", 0, projects.size());
    }

    @Test
    public void findById() {
        Project project = projectService.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"));

        assertEquals("Project has an invalid id", UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"), project.getId());
        assertEquals("Project has an invalid name", "Project 1", project.getName());
        assertEquals("Project has an invalid description", "Description 1", project.getDescription());
        assertEquals("Project has an invalid budget", 10f, project.getBudget(), 0.0f);
        assertFalse("Project has an invalid archived value", project.isArchived());
    }

    @Test
    public void findByIdWrongId() {
        Project project = projectService.findById(UUID.fromString("b9c0dce0-b047-43f8-960d-53aca1a9fa26"));

        assertNull("Project should be null", project);
    }

    @Test
    public void createProject() {
        Project newProject = new Project();
        newProject.setName("Project 1");
        newProject.setDescription("Description 1");
        newProject.setBudget(10f);
        Project project = projectService.createProject(newProject);

        assertEquals("Project has an invalid id", UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"), project.getId());
        assertEquals("Project has an invalid name", newProject.getName(), project.getName());
        assertEquals("Project has an invalid description", newProject.getDescription(), project.getDescription());
        assertEquals("Project has an invalid budget", newProject.getBudget(), project.getBudget(), 0.0f);
        assertFalse("Project has an invalid archived value", project.isArchived());
    }

    @Test
    public void updateProject() {
        Project updateProject = new Project();
        updateProject.setName("Project 1");
        Project project = projectService.updateProject(updateProject);

        assertEquals("Project has an invalid id", UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"), project.getId());
        assertEquals("Project has an invalid name", updateProject.getName(), project.getName());
        assertEquals("Project has an invalid description", "Description 1", project.getDescription());
        assertEquals("Project has an invalid budget", 10f, project.getBudget(), 0.0f);
        assertFalse("Project has an invalid archived value", project.isArchived());
    }

    @Test
    public void addMember() {
        Project updatedProject = projectService.addMember(project, UUID.fromString("66585a29-05bb-4033-b2c7-23b30e6e2fd1"));

        assertEquals("Project has an invalid member count", 2, updatedProject.getMembers().size());
        assertEquals("Project has an invalid member", UUID.fromString("4562366c-2d59-44a5-b385-8848144706fe"), updatedProject.getMembers().get(0));
        assertEquals("Project has an invalid member", UUID.fromString("66585a29-05bb-4033-b2c7-23b30e6e2fd1"), updatedProject.getMembers().get(1));
    }

    @Test
    public void removeMember() {
        Project updatedProject = projectService.removeMember(project, UUID.fromString("4562366c-2d59-44a5-b385-8848144706fe"));

        assertEquals("Project has an invalid member count", 0, updatedProject.getMembers().size());
    }

    @Test
    public void deleteProject() {
        projectService.deleteProject(project);
    }

    @TestConfiguration
    static class ProjectServiceTestContextConfiguration {
        @Bean
        public ProjectService projectService() {
            return new ProjectService();
        }
    }
}