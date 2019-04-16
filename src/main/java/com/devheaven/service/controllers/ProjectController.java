package com.devheaven.service.controllers;

import com.devheaven.service.exceptions.BadRequestException;
import com.devheaven.service.exceptions.InternalServerException;
import com.devheaven.service.exceptions.NotFoundException;
import com.devheaven.service.models.Project;
import com.devheaven.service.requests.CreateProjectRequest;
import com.devheaven.service.requests.UpdateProjectRequest;
import com.devheaven.service.responses.ProjectResponse;
import com.devheaven.service.services.ProjectService;
import com.devheaven.service.utils.MergeUtility;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * This controller handles the requests for the project model.
 * It listens on the /projects route.
 */
@RestController
@RequestMapping(path = "/projects")
@Api(tags = "Project")
public class ProjectController {

    private final ProjectService projectService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Handles the / route. This route returns all the projects in the system.
     *
     * @return List<ProjectResponse> containing data about the projects in the system.
     */
    @GetMapping("/")
    @Transactional
    @ApiOperation(value = "List all projects in the system", response = ProjectResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<ProjectResponse> getProjects() {
        List<Project> projects = projectService.findAll();
        return modelMapper.map(projects, new TypeToken<List<ProjectResponse>>() {}.getType());
    }

    /**
     * Handles the /{id} route. This route returns information about one specific project.
     *
     * @param id the id of the project to retrieve.
     * @return ProjectResponse containing data about the project.
     * @throws NotFoundException if the project is not found.
     */
    @GetMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Information about one specific project", response = ProjectResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ProjectResponse getProjectById(@ApiParam(required = true, value = "Id of the project to retrieve") @PathVariable String id) throws NotFoundException {
        Project project = projectService.findById(UUID.fromString(id));

        if (project == null) {
            throw new NotFoundException("Project not found");
        }

        return modelMapper.map(project, ProjectResponse.class);
    }


    /**
     * Handles the /for/{id} route. This route retrieves all the projects from a specific
     * user.
     *
     * @param id the id of the user to retrieve the projects for.
     * @return List<ProjectResponse> containing the projects from the user.
     */
    @GetMapping("/for/{id}")
    @Transactional
    @ApiOperation(value = "List all projects for a user", response = ProjectResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<ProjectResponse> getProjectsForUser(@ApiParam(required = true, value = "Id of the user") @PathVariable String id) {
        List<Project> projects = projectService.findAllForMember(UUID.fromString(id));
        return modelMapper.map(projects, new TypeToken<List<ProjectResponse>>() {}.getType());
    }

    /**
     * Handles the / route. This route adds a new project to the system.
     *
     * @param createProjectRequest the create project request containing the values from the form.
     * @return ProjectResponse containing data about the created project.
     * @throws InternalServerException if an error occurred while creating the project.
     */
    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Adds a new project to the system", response = ProjectResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ProjectResponse createProject(@ApiParam("New User") @RequestBody CreateProjectRequest createProjectRequest) throws InternalServerException {
        Project newProject = modelMapper.map(createProjectRequest, Project.class);
        Project project = projectService.createProject(newProject);
        return modelMapper.map(project, ProjectResponse.class);
    }

    /**
     * Handles the /{id} route. This route updates one specific project.
     *
     * @param id the id of the project to update.
     * @param updateProjectRequest the update request containing the data from the form.
     * @return ProjectResponse the updated project.
     * @throws NotFoundException if the project is not found.
     */
    @PatchMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Update one specific project", response = ProjectResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ProjectResponse updateProject(@ApiParam(required = true, value = "Id of the project to update") @PathVariable String id, @ApiParam("Update Data") @RequestBody UpdateProjectRequest updateProjectRequest) throws NotFoundException {
        Project existingProject = projectService.findById(UUID.fromString(id));

        if (existingProject == null) {
            throw new NotFoundException("Project not found");
        }

        Project updateProject = MergeUtility.merge(modelMapper.map(updateProjectRequest, Project.class), existingProject);

        Project project = projectService.updateProject(updateProject);

        return modelMapper.map(project, ProjectResponse.class);
    }

    /**
     * Handles the /project/{id}/members/{memberId} route. This route adds a user
     * to a project.
     *
     * @param id the id of the project.
     * @param memberId the id of the member.
     * @throws NotFoundException   if the project is not found.
     * @throws BadRequestException if the project already has the member.
     */
    @PatchMapping("/{id}/members/{memberId}")
    @Transactional
    @ApiOperation(value = "Adds a member", response = ProjectResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ProjectResponse addMember(@ApiParam(required = true, value = "Id of the project") @PathVariable String id, @ApiParam(required = true, value = "Id of the member") @PathVariable String memberId) throws NotFoundException, BadRequestException {
        Project project = projectService.findById(UUID.fromString(id));

        if (project == null) {
            throw new NotFoundException("Project not found");
        }

        if (project.getMembers().contains(UUID.fromString(memberId))) {
            throw new BadRequestException("User is already a member of the project");
        }

        Project updatedProject = projectService.addMember(project, UUID.fromString(memberId));

        return modelMapper.map(updatedProject, ProjectResponse.class);
    }

    /**
     * Handles the /project/{id}/members/{memberId} route. This route removes a user
     * from a project.
     *
     * @param id the id of the project.
     * @param memberId the id of the member.
     * @throws NotFoundException   if the project is not found.
     * @throws BadRequestException if the project doesn't has the member.
     */
    @DeleteMapping("/{id}/members/{memberId}")
    @Transactional
    @ApiOperation(value = "Removes a member", response = ProjectResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ProjectResponse removeMember(@ApiParam(required = true, value = "Id of the project") @PathVariable String id, @ApiParam(required = true, value = "Id of the member") @PathVariable String memberId) throws NotFoundException, BadRequestException {
        Project project = projectService.findById(UUID.fromString(id));

        if (project == null) {
            throw new NotFoundException("Project not found");
        }

        if (!project.getMembers().contains(UUID.fromString(memberId))) {
            throw new BadRequestException("User is not a member of the project");
        }

        Project updatedProject = projectService.removeMember(project, UUID.fromString(memberId));

        return modelMapper.map(updatedProject, ProjectResponse.class);
    }

    /**
     * Handles the /{id} route. This routes deletes one specific project.
     *
     * @param id the id of the project to delete.
     * @throws NotFoundException if the project is not found.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete one specific project", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void deleteProject(@ApiParam(required = true, value = "Id of the project to delete") @PathVariable String id) throws NotFoundException {
        Project project = projectService.findById(UUID.fromString(id));

        if (project == null) {
            throw new NotFoundException("Project not found");
        }

        projectService.deleteProject(project);
    }
}
