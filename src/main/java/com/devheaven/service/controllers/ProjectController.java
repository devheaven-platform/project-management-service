package com.devheaven.service.controllers;

import com.devheaven.service.exceptions.BadRequestException;
import com.devheaven.service.exceptions.InternalServerException;
import com.devheaven.service.exceptions.NotFoundException;
import com.devheaven.service.requests.CreateProjectRequest;
import com.devheaven.service.requests.UpdateProjectRequest;
import com.devheaven.service.responses.ProjectResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.SortedSet;

/**
 * This controller handles the requests for the project model.
 * It listens on the /projects route.
 */
@RestController
@RequestMapping(path = "/projects")
@Api(tags = "Project")
public class ProjectController {

    /**
     * Handles the / route. This route returns all the projects in the system.
     *
     * @return List<ProjectResponse> containing data about the projects in the system.
     */
    @GetMapping("/")
    @ApiOperation(value = "List all projects in the system", response = ProjectResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public SortedSet<ProjectResponse> getProjects() {
        throw new UnsupportedOperationException();
    }

    /**
     * Handles the /{id} route. This route returns information about one specific project.
     *
     * @param id the id of the project to retrieve.
     * @return ProjectResponse containing data about the project.
     * @throws NotFoundException if the project is not found.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Information about one specific project", response = ProjectResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ProjectResponse getProjectById(@ApiParam(required = true, value = "Id of the project to retrieve") @PathVariable String id) throws NotFoundException {
        throw new UnsupportedOperationException();
    }


    /**
     * Handles the /for/{id} route. This route retrieves all the projects from a specific
     * user.
     *
     * @param id the id of the user to retrieve the projects for.
     * @return List<ProjectResponse> containing the projects from the user.
     */
    @GetMapping("/for/{id}")
    @ApiOperation(value = "List all projects for a user", response = ProjectResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public SortedSet<ProjectResponse> getProjectsForUser(@ApiParam(required = true, value = "Id of the user") @PathVariable String id) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
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
    @ApiOperation(value = "Update one specific project", response = ProjectResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ProjectResponse updateProject(@ApiParam(required = true, value = "Id of the project to update") @PathVariable String id, @ApiParam("Update Data") @RequestBody UpdateProjectRequest updateProjectRequest) throws NotFoundException {
        throw new UnsupportedOperationException();
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
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Adds a member", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void addMember(@ApiParam(required = true, value = "Id of the project") @PathVariable String id, @ApiParam(required = true, value = "Id of the member") @PathVariable String memberId) throws NotFoundException, BadRequestException {
        throw new UnsupportedOperationException();
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
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Removes a member", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void removeMember(@ApiParam(required = true, value = "Id of the project") @PathVariable String id, @ApiParam(required = true, value = "Id of the member") @PathVariable String memberId) throws NotFoundException, BadRequestException {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
