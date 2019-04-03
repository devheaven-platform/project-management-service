package controllers;

import dto.EditMemberDTO;
import dto.ProjectDTO;
import models.Project;
import services.ProjectService;

import javax.inject.Inject;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.updateFromProjectDTO(projectDTO);
        projectService.createProject(project);

        List<String> stringMembers = new ArrayList<>();
        if(project.getMembers() != null){
            for(UUID member : project.getMembers()){
                stringMembers.add(String.valueOf(member));
            }

        }


        ProjectDTO returnDTO = new ProjectDTO(project.getId(),
                project.getOwner(),
                stringMembers,
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
}
