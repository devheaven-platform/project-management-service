package repositories;

import models.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class ProjectDAO {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public void createProject(Project project){
        em.persist(project);
    }

    public void removeMember(Project project, UUID member){
        List<UUID> UUIDList = project.getMembers();
        UUIDList.remove(member);
        project.setMembers(UUIDList);
        em.merge(project);
    }

    public void addMember(Project project, UUID member){
        List<UUID> UUIDList = project.getMembers();
        UUIDList.add(member);
        project.setMembers(UUIDList);
        em.merge(project);
    }

    public void archiveProject(Project project){
        project.setArchived(true);
        em.merge(project);
    }

    public Project getProjectById(int id){
        return (Project) em.createNamedQuery("Project.getProjectById").setParameter("id", id).getSingleResult();
    }

    public void editProject(Project project){
        em.merge(project);
    }


}
