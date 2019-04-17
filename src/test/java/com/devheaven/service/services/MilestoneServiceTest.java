package com.devheaven.service.services;

import com.devheaven.service.models.Milestone;
import com.devheaven.service.models.Project;
import com.devheaven.service.repositories.MilestoneRepository;
import com.devheaven.service.repositories.ProjectRepository;
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
public class MilestoneServiceTest {

    @TestConfiguration
    static class ProjectServiceTestContextConfiguration {
        @Bean
        public ProjectService projectService() {
            return new ProjectService();
        }

        @Bean
        public MilestoneService milestoneService() {
            return new MilestoneService();
        }
    }

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MilestoneService milestoneService;

    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private MilestoneRepository milestoneRepository;

    private Project project;
    private Milestone milestone;

    @Before
    public void setUp() {
        project = new Project();
        project.setId(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"));
        project.setName("Project 1");
        project.setDescription("Description 1");
        project.setBudget(10);
        project.setStart(new Date());
        project.setArchived(false);
        project.addMember(UUID.fromString("4562366c-2d59-44a5-b385-8848144706fe"));

        Mockito.when(projectRepository.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"))).thenReturn(Optional.of(project));

        milestone = new Milestone();
        milestone.setId(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaFFF"));
        milestone.setName("Milestone 1");
        milestone.setDescription("Description 1");
        milestone.setDate(new Date());

        Mockito.when(milestoneRepository.findAll(Sort.by(Sort.Direction.DESC, "date"))).thenReturn(new ArrayList<Milestone>(){{
            add(milestone);
        }});

        Mockito.when(milestoneRepository.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaFFF"))).thenReturn(Optional.of(milestone));
        Mockito.when(milestoneRepository.findById(UUID.fromString("b9c0dce0-b047-43f8-960d-53aca1a9fa26"))).thenReturn(Optional.empty());

        Mockito.when(milestoneRepository.save(notNull())).thenReturn(milestone);
    }

    @Test
    public void findAll() {
        List<Milestone> milestones = milestoneService.findAll();

        assertEquals("Service returned an invalid milestone count", 1, milestones.size());
        assertEquals("Milestone has an invalid name", "Milestone 1", milestones.get(0).getName());
        assertEquals("Milestone has an invalid description", "Description 1", milestones.get(0).getDescription());
    }

    @Test
    public void getMilestoneById() {
        Milestone milestone = milestoneService.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaFFF"));

        assertEquals("Milestone has an invalid name", "Milestone 1", milestone.getName());
        assertEquals("Milestone has an invalid description", "Description 1", milestone.getDescription());
    }

    @Test
    public void getMilestoneByIdWrongId() {
        Milestone milestone = milestoneService.findById(UUID.fromString("b9c0dce0-b047-43f8-960d-53aca1a9fa26"));

        assertNull("Milestone should be null", milestone);
    }

    @Test
    public void createMilestone() {
        Project project = projectService.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"));
        Milestone newMilestone = new Milestone();
        newMilestone.setName("Milestone 1");
        newMilestone.setDescription("Description 1");
        newMilestone.setDate(new Date());
        Milestone milestone = milestoneService.createMilestone(project, newMilestone);


        assertEquals("Milestone has an invalid name", "Milestone 1", milestone.getName());
        assertEquals("Milestone has an invalid description", "Description 1", milestone.getDescription());
    }

    @Test
    public void updateMilestone() {
        Milestone updateMilestone = new Milestone();
        updateMilestone.setName("Milestone 1");
        Milestone milestone = milestoneService.updateMilestone(updateMilestone);


        assertEquals("Milestone has an invalid name", "Milestone 1", milestone.getName());
        assertEquals("Milestone has an invalid description", "Description 1", milestone.getDescription());
    }

    @Test
    public void deleteMilestone() {
        milestoneService.deleteMilestone(milestone);
    }
}