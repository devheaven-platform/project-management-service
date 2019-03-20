package repositories;

import models.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
public class ProjectDAO {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public void createProject(Project project){
        em.persist(project);
    }

    public void removeMember(Project project, UUID member){
        project.getMembers().remove(member);
    }

    public void addMember(Project project, UUID member){

        Project project1 = getProjectById(project.getId());
        project1.getMembers().add(member);
        em.merge(project1);
    }

    public void archiveProject(Project project){
        project.setArchived(true);
    }

    public Project getProjectById(int id){
        return (Project) em.createNamedQuery("Project.getProjectById").setParameter("id", id).getSingleResult();
    }

    public void editProject(Project project){
        em.merge(project);
    }


}
