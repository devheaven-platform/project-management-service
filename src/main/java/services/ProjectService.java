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

    public boolean removeMember(int projectId, UUID memberId){
        try{
            Project project = projectDAO.getProjectById(projectId);
            projectDAO.removeMember(project, memberId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void addMember(int projectId, UUID memberId){
        Project project = projectDAO.getProjectById(projectId);
        projectDAO.addMember(project, memberId);
        System.out.println("Added member");
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
