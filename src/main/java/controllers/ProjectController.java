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

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response editProject(ProjectDTO projectDTO){
        try{
            Project project = projectService.getProjectById(projectDTO.getId());

            List<UUID> UUIDMembers = new ArrayList<>();
            for(String member : projectDTO.getMembers()){
                UUIDMembers.add(UUID.fromString(member));
            }
            project.setMembers(UUIDMembers);
            project.setBudget(projectDTO.getBudget());
            project.setName(projectDTO.getName());
            project.setArchived(projectDTO.isArchived());
            project.setClient(projectDTO.getClient());
            project.setDeadlines(projectDTO.getDeadlines());
            project.setEndDate(projectDTO.getEndDate());
            project.setStartDate(projectDTO.getStartDate());
            project.setDescription(projectDTO.getDescription());
            project.setOwner(projectDTO.getOwner());

            projectService.editProject(project);
            return Response.ok().build();
        }catch(Exception ex){

            return Response.status(500).build();
        }
    }
}
