package controllers;

import models.Project;
import services.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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


}
