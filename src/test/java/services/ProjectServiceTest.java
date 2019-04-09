package services;

import dto.DeadlineDTO;
import dto.ProjectDTO;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
                .addClasses(Project.class, DeadlineDTO.class, ProjectService.class, ProjectDAO.class, Deadline.class, UUID.class, List.class, ProjectDTO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void createProject(){
        Project project = new Project();
        project.setName("testProject");
        project.setBudget(10000);

        List<UUID> uuidList = new ArrayList<>();
        uuidList.add(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));
        project.setMembers(uuidList);

        assertNotNull(projectService.createProject(project));
        assertNotNull(project.getId());
        assertEquals(10000 ,project.getBudget(), 0);

        project = projectService.getProjectById(project.getId());
        assertEquals(1, project.getMembers().size());
    }

    @Test
    public void removeMemberTest(){
        Project project = new Project();
        project.setName("projectTwee");
        Project testProject = projectService.createProject(project);
        projectService.addMemberToProject(project.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));
        projectService.addMemberToProject(project.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c10"));

        project = projectService.getProjectById(project.getId());
        assertEquals(2, project.getMembers().size());

        assertTrue(projectService.removeMemberFromProject(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73")));

        project = projectService.getProjectById(project.getId());
        assertEquals(1, project.getMembers().size());

        assertFalse(projectService.removeMemberFromProject(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73")));

        project = projectService.getProjectById(project.getId());
        assertEquals(1,project.getMembers().size());

        assertTrue(projectService.removeMemberFromProject(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c10")));

        project = projectService.getProjectById(project.getId());
        assertEquals(0, project.getMembers().size());
    }

    @Test
    public void addMemberTest(){
        Project project = new Project();
        project.setName("projectDrie");
        Project testProject = projectService.createProject(project);

        assertTrue(projectService.addMemberToProject(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73")));

        project = projectService.getProjectById(project.getId());
        assertEquals(1, project.getMembers().size());

        assertFalse(projectService.addMemberToProject(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73")));

        project = projectService.getProjectById(project.getId());
        assertEquals(1, project.getMembers().size());

        assertTrue(projectService.addMemberToProject(testProject.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c10")));

        project = projectService.getProjectById(project.getId());
        assertEquals(2, project.getMembers().size());

        assertEquals(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"), project.getMembers().get(0));
        assertEquals(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c10"), project.getMembers().get(1));
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

        List<UUID> uuidList = new ArrayList<>();
        uuidList.add(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c73"));
        project.setMembers(uuidList);

        Project testProject = projectService.createProject(project);

        assertEquals("projectVier", testProject.getName());

        project.setName("projectVijf");

        projectService.editProject(project);

        Project project1 = projectService.getProjectById(project.getId());

        assertEquals("projectVijf", project1.getName());
    }

    @Test
    public void getAllProjectsOfMemberTest(){
        Project project = new Project();
        project.setName("getAllProjectsOfMemberTest1");
        project = projectService.createProject(project);

        Project project1 = new Project();
        project1.setName("getAllProjectsOfMemberTest2");
        project1 = projectService.createProject(project1);

        Project project2 = new Project();
        project2.setName("getAllProjectsOfMemberTest3");
        project2 = projectService.createProject(project2);

        assertTrue(projectService.addMemberToProject(project.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c99")));
        assertTrue(projectService.addMemberToProject(project2.getId(), UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c99")));

        List<Project> projectList = projectService.getAllProjectsOfMember(UUID.fromString("919bde8c-df32-45d7-952e-b47119b73c99"));
        assertEquals(2, projectList.size());
        assertTrue(projectList.stream().anyMatch(x -> x.getName().equals("getAllProjectsOfMemberTest1")));
        assertFalse(projectList.stream().anyMatch(x -> x.getName().equals("getAllProjectsOfMemberTest2")));
        assertTrue(projectList.stream().anyMatch(x -> x.getName().equals("getAllProjectsOfMemberTest3")));
    }

    @Test
    public void createDeadlineTest(){
        Project project = new Project();
        project.setName("testProject");
        projectService.createProject(project);

        Deadline deadline = new Deadline();
        deadline.setDeadline(Calendar.getInstance());
        deadline.getDeadline().set(2018,10,10);
        deadline.setDescription("Deadline");

        projectService.addDeadline(project, deadline);
        assertEquals(1, project.getDeadlines().size());
    }
}
