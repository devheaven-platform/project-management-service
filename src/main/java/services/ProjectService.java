package services;

import models.Project;
import repositories.ProjectDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class ProjectService {

    @EJB
    private ProjectDAO projectDAO;

    public Project createProject(Project project){
        try{
            projectDAO.createProject(project);
            return project;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method removes the specified member from the specified project
     *
     * @param projectId this param represents the id of the project
     * @param memberId this param represents the id of the member that needs to be removed from the project
     * @return returns true if the member is successfully removed from the project
     */
    public boolean removeMemberFromProject(int projectId, UUID memberId){
        try{
            Project project = projectDAO.getProjectById(projectId);
            return projectDAO.removeMemberFromProject(project, memberId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method adds a member to the specified project
     *
     * @param projectId this param represents the id of the project
     * @param memberId this param represents member that should be added to the specified project
     * @return returns true if the member is successfully added to the project
     */
    public boolean addMemberToProject(int projectId, UUID memberId){
        try {
            Project project = projectDAO.getProjectById(projectId);
            return projectDAO.addMemberToProject(project, memberId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Project archiveProject(int projectId){
        try{
            Project project = projectDAO.getProjectById(projectId);
            projectDAO.archiveProject(project);
            return project;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method should update the given Project.
     *
     * @param project this param should be a existing project with changed values
     */
    public void editProject(Project project){
        try{
            projectDAO.editProject(project);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Project getProjectById(int id){
        return projectDAO.getProjectById(id);
    }
}
