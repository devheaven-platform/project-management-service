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
import org.junit.Test;
import org.junit.runner.RunWith;
import repositories.DeadlineDAO;
import repositories.ProjectDAO;

import javax.ejb.EJB;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class DeadlineServiceTest {

    @EJB
    private DeadlineService deadlineService;

    @EJB
    private ProjectService projectService;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Project.class, DeadlineDTO.class, ProjectDAO.class, ProjectDTO.class, DeadlineDAO.class, Deadline.class, ProjectService.class, DeadlineService.class, UUID.class, List.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void createDeadlineTest(){
        Project project = new Project();
        project.setName("projectSimon");
        projectService.createProject(project);

        Deadline deadline = new Deadline();
        deadline.setDeadline(Calendar.getInstance());
        deadline.getDeadline().set(2018,10,10);
        deadline.setDescription("Deadline");

        deadlineService.addDeadline(project, deadline);
        assertEquals(1, project.getDeadlines().size());
    }
}
