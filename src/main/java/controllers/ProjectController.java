package controllers;

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
        }catch(Exception ex){
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }
}
