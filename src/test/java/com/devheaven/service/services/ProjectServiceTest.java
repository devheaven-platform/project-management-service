package com.devheaven.service.services;

import com.devheaven.service.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @TestConfiguration
    static class ProjectServiceTestContextConfiguration {
        @Bean
        public ProjectService projectService() {
            return new ProjectService();
        }
    }

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void findAll() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void findAllForMember() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void findById() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void createProject() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void updateProject() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void addMember() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void removeMember() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void deleteProject() {
        throw new UnsupportedOperationException();
    }
}