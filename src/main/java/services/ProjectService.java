package services;

import models.Project;
import repositories.ProjectDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

@Stateless
public class ProjectService {

    @EJB
    private ProjectDAO projectDAO;

    /**
     * This method creates a new project based on the object given in the parameter
     *
     * @param project this param represents the project that will be created
     * @return returns the created project
     */
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
    public boolean removeMemberFromProject(UUID projectId, UUID memberId){
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
    public boolean addMemberToProject(UUID projectId, UUID memberId){
        try {
            Project project = projectDAO.getProjectById(projectId);
            return projectDAO.addMemberToProject(project, memberId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Archive the specified project
     *
     * @param projectId this param represents the id of the project that will be archived
     * @return returns a response with status 200 if successful
     */
    public Project archiveProject(UUID projectId){
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

    public Project getProjectById(UUID id){
        return projectDAO.getProjectById(id);
    }

    /**
     * Retrieve all projects of the specified member
     *
     * @param memberId this param is the id of the member that wants to retrieve their projects
     * @return returns a list of projects
     */
    public List<Project> getAllProjectsOfMember(UUID memberId) {
        try{
            return projectDAO.getAllMemberProjects(memberId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
