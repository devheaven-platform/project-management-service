package services;

import models.Deadline;
import models.Project;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import repositories.ProjectDAO;

import javax.ejb.EJB;
import javax.persistence.PersistenceContext;

import java.awt.*;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectServiceTest {

    @EJB
    private ProjectService projectService;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Project.class, ProjectService.class, ProjectDAO.class, Deadline.class, UUID.class, List.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void createProject(){
        Project project = new Project();
        project.setName("projectSimon");
        project.setBudget(10000);
        project.getMembers().add(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));

        assertNotNull(projectService.createProject(project));
    }

    @Test
    public void removeMemberTest(){
        Project project = new Project();
        project.setName("projectTwee");
        project.getMembers().add(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));
        Project testProject = projectService.createProject(project);

        assertTrue(projectService.removeMember(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73")));
    }

    @Test
    public void addMemberTest(){
        Project project = new Project();
        project.setName("projectDrie");
        project.getMembers().add(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));
        Project testProject = projectService.createProject(project);

        projectService.addMember(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));

        Project project1 = projectService.getProjectById(testProject.getId());

        assertEquals(1, project1.getMembers().size());
        assertEquals(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"), project1.getMembers().get(0));
    }

    @Test
    public void archiveProjectTest(){
        Project project = new Project();
        project.setName("projectVier");
        project.getMembers().add(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));
        Project testProject = projectService.createProject(project);

        projectService.archiveProject(testProject.getId());

        Project project1 = projectService.getProjectById(testProject.getId());

        assertEquals(true, project1.isArchived());
    }

    @Test
    public void editProjectTest(){
        Project project = new Project();
        project.setName("projectVier");
        project.getMembers().add(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));
        Project testProject = projectService.createProject(project);

        assertEquals("projectVier", testProject.getName());

        project.setName("projectVijf");

        projectService.editProject(project);

        Project project1 = projectService.getProjectById(project.getId());

        assertEquals("projectVijf", project1.getName());
    }

}
