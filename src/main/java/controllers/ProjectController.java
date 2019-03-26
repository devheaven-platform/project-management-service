package controllers;

import dto.EditMemberDTO;
import dto.ProjectDTO;
import models.Project;
import services.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({"application/json"})
@Path("project")
public class ProjectController {

    @Inject
    ProjectService projectService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createproject")
    public Response createProject(Project project) {
        projectService.createProject(project);
        return Response.status(201).entity(project).build();
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

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response archiveProject(ProjectDTO project){
        try{
            projectService.archiveProject(project.getId());
            return Response.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response editMembers(EditMemberDTO memberDTO){
        try{
            if(memberDTO.isAddMember()){
                projectService.addMember(memberDTO.getProjectId(), memberDTO.getMemberId());
                return Response.ok().build();
            }
            else if (!memberDTO.isAddMember()){
                projectService.removeMember(memberDTO.getProjectId(), memberDTO.getMemberId());
                return Response.ok().build();
            }
            return Response.ok().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}
