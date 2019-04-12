package controllers;

import dto.DeadlineDTO;
import dto.ProjectDTO;
import models.Deadline;
import models.Project;
import services.ProjectService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Produces({"application/json"})
@Path("project")
public class ProjectController {

    @Inject
    ProjectService projectService;

    /**
     * This method creates a new project based on the object given in the parameter
     *
     * @param projectDTO this param is a projectDTO containing values of the project that will be created
     * @return this method with the response code 201 together with the newly created project if the project is created
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response createProject(ProjectDTO projectDTO) {
        try {
            Project project = new Project();
            project.updateFromProjectDTO(projectDTO);
            projectService.createProject(project);

            ProjectDTO returnDTO = new ProjectDTO(project.getId(),
                    project.getOwner(),
                    convertUUIDToString(project.getMembers()),
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getBudget(),
                    project.getDeadlines(),
                    project.getClient(),
                    project.isArchived());
            return Response.status(201).entity(returnDTO).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    /**
     * This method should update the given Project.
     *
     * @param projectDTO this param should be a projectDTO containing data of a project that should be changed
     * @return this method returns a response with code 200 or 500
     */
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response editProject(ProjectDTO projectDTO){
        try{
            Project project = projectService.getProjectById(projectDTO.getId());
            project.updateFromProjectDTO(projectDTO);

            projectService.editProject(project);

            return Response.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    /**
     * Archive the specified project
     *
     * @param id this param represents the id of the project that will be archived
     * @return returns a response with status 200 if successful
     */
    @PATCH
    @Path("/{id}")
    public Response archiveProject(@PathParam("id") UUID id){
        try{
            projectService.archiveProject(id);
            return Response.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    /**
     * Adds a member to the specified project
     *
     * @param projectId this param represents the id of the project
     * @param memberId this param represents the id of the member that needs to be added to the project
     * @return returns a response with status code 204
     */
    @PATCH
    @Path("/{projectId}/members/{memberId}")
    public Response addMemberToProject(@PathParam("projectId") UUID projectId, @PathParam("memberId") UUID memberId){
        try{
            if(!projectService.addMemberToProject(projectId, memberId)){
                return Response.status(400).build();
            }
            return Response.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }


    /**
     * This method removes the specified member from the specified project
     *
     * @param projectId this param represents the id of the project
     * @param memberId this param represents the id of the member that needs to be removed from the project
     * @return returns a response with status code 204
     */
    @DELETE
    @Path("/{projectId}/members/{memberId}")
    public Response removeMemberFromProject(@PathParam("projectId") UUID projectId, @PathParam("memberId") UUID memberId){
        try{
            if(!projectService.removeMemberFromProject(projectId, memberId)) {
                return Response.status(404).build();
            }
            return Response.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    /**
     * Retrieve all projects of the specified member
     *
     * @param memberId this param is the id of the member that wants to retrieve their projects
     * @return a response with status code 200 and a list of projects
     */
    @GET
    @Path("/{memberId}")
    public Response getAllProjectsOfMember(@PathParam("memberId") UUID memberId){
        try{
            List<Project> projects = projectService.getAllProjectsOfMember(memberId);
            List<ProjectDTO> projectDTOS = new ArrayList<>();
            projects.forEach(x -> {
                projectDTOS.add(new ProjectDTO(x.getId(), x.getName()));
            });

            return Response.status(200).entity(projectDTOS).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    /**
     * This method adds a deadline to a existing project
     *
     * @param projectId this param represents the id of the project to which the deadline will be added
     * @param deadlineDTO this param represents the deadline that will be added to the project
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{projectId}/deadline")
    @Transactional
    public Response addDeadline(@PathParam("projectId") UUID projectId, DeadlineDTO deadlineDTO){
        try{
            Deadline deadline = new Deadline();
            deadline.updateFromDeadlineDTO(deadlineDTO);
            Project project = projectService.getProjectById(projectId);

            projectService.addDeadline(project, deadline);

            DeadlineDTO returnDTO = new DeadlineDTO(deadline);
            return Response.status(201).entity(returnDTO).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    /**
     * This method converts a List of UUID's to a list of Strings
     *
     * @param uuidList this param represents a list of UUID's that will be transformed to string
     * @return returns a list of uuid strings
     */
    private List<String> convertUUIDToString(List<UUID> uuidList){
        if(uuidList != null) {
            List<String> stringMembers = new ArrayList<>();
            for (UUID member : uuidList) {
                stringMembers.add(String.valueOf(member));
            }
            return stringMembers;
        }
        else{
            return null;
        }
    }
}
