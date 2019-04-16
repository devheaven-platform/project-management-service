package com.devheaven.service.services.impl;

import com.devheaven.service.models.Milestone;
import com.devheaven.service.models.Project;
import com.devheaven.service.repositories.MilestoneRepository;
import com.devheaven.service.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class MilestoneServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private MilestoneRepository milestoneRepository;

    @Before
    public void setUp() {
        Project project = new Project();
        project.setName("TestProject");
        project.setArchived(false);
        project.setBudget(1000);
        project.setClient(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefa237"));
        project.setCreatedAt(new Date());
        project.setId(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaeee"));
        project.setOwner(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaddd"));

        Mockito.when(projectRepository.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaeee"))).thenReturn(Optional.of(project));

        Milestone milestone = new Milestone();
        milestone.setName("TestMilestone");
        milestone.setDescription("TestDescription");
        milestone.setDate(new Date());
        milestone.setId(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaFFF"));

        Mockito.when(milestoneRepository.findById(UUID.fromString("cf023055-af8e-42bf-9d2e-cfe37cefaFFF"))).thenReturn(Optional.of(milestone));
        Mockito.when(milestoneRepository.findAll(Sort.by(Sort.Direction.DESC, "date"))).thenReturn(new ArrayList<Milestone>(){{
            add(milestone);
        }});
    }

    @Test
    public void findAll() {
        Project project = new Project();
        project.setName("TestProject");
        projectService.createProject(project);

        assertNotNull(project.getId());
    }

    @Test
    public void getMilestoneById() {
    }

    @Test
    public void createMilestone() {
    }

    @Test
    public void updateMilestone() {
    }

    @Test
    public void deleteMilestone() {
    }
}