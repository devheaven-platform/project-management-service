package controllers;

import dto.ProjectDTO;
import models.Project;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import services.ProjectService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
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
}
