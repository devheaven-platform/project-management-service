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

    /**
     * This method removes the specified member from the specified project
     *
     * @param project this param represents the id of the project
     * @param member this param represents the id of the member that needs to be removed from the project
     * @return returns true if the member is successfully removed from the project
     */
    public boolean removeMemberFromProject(Project project, UUID member){
        List<UUID> UUIDList = project.getMembers();
        if(!UUIDList.remove(member)){
            return false;
        }
        project.setMembers(UUIDList);
        em.merge(project);
        return true;
    }

    /**
     * This method adds a member to the specified project
     *
     * @param project this param represents the id of the project
     * @param member this param represents member that should be added to the specified project
     * @return returns true if the member is successfully added to the project
     */
    public boolean addMemberToProject(Project project, UUID member){
        List<UUID> UUIDList = project.getMembers();
        if(UUIDList.contains(member)) {
            return false;
        }
        UUIDList.add(member);
        project.setMembers(UUIDList);
        em.merge(project);
        return true;
    }

    public void archiveProject(Project project){
        project.setArchived(true);
        em.merge(project);
    }

    public Project getProjectById(int id){
        return (Project) em.createNamedQuery("Project.getProjectById").setParameter("id", id).getSingleResult();
    }

    /**
     * This method should update the given Project.
     *
     * @param project this param should be a existing project with changed values
     */
    public void editProject(Project project){
        em.merge(project);
    }


}
