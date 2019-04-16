package com.devheaven.service.services.impl;

import com.devheaven.service.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllForMember() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void createProject() {
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void addMember() {
    }

    @Test
    public void removeMember() {
    }

    @Test
    public void deleteProject() {
    }
}