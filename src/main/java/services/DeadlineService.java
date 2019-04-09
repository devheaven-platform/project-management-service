package services;

import models.Deadline;
import models.Project;
import repositories.DeadlineDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class DeadlineService {

    @EJB
    private DeadlineDAO deadlineDAO;

    public void addDeadline(Project project, Deadline deadline){
        project.getDeadlines().add(deadline);
    }

    public void editDeadline(Deadline deadline){
        deadlineDAO.editDeadline(deadline);
    }

    public void removeDeadline(Deadline deadline){
        deadlineDAO.removeDeadline(deadline);
    }

    public Deadline getDeadlineById(UUID id){
        return deadlineDAO.getDeadlineById(id);
    }
}
