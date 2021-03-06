package nl.devheaven.service.controllers;

import io.swagger.annotations.*;
import nl.devheaven.service.exceptions.InternalServerException;
import nl.devheaven.service.exceptions.NotFoundException;
import nl.devheaven.service.models.Milestone;
import nl.devheaven.service.models.Project;
import nl.devheaven.service.requests.CreateMilestoneRequest;
import nl.devheaven.service.requests.UpdateMilestoneRequest;
import nl.devheaven.service.responses.MilestoneResponse;
import nl.devheaven.service.services.MilestoneService;
import nl.devheaven.service.services.ProjectService;
import nl.devheaven.service.utils.MergeUtility;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * This controller handles the requests for the milestone model.
 * It listens on the /milestones route.
 */
@RestController
@RequestMapping(path = "/milestones")
@Api(tags = "Milestone")
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final ProjectService projectService;
    private final ModelMapper modelMapper;

    /**
     * Constructor for the milestone controller.
     *
     * @param milestoneService the milestone service to interact with the data access layer.
     * @param projectService   the project service to interact with the data access layer.
     */
    @Autowired
    public MilestoneController(MilestoneService milestoneService, ProjectService projectService) {
        this.milestoneService = milestoneService;
        this.projectService = projectService;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Handles the / route. This route returns all the milestones in the system.
     *
     * @return List<MilestoneResponse> containing data about the milestones in the system.
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "List all milestones in the system", response = MilestoneResponse.class, responseContainer = "List", authorizations = {@Authorization(value = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<MilestoneResponse> getMilestones() {
        List<Milestone> milestones = milestoneService.findAll();
        return modelMapper.map(milestones, new TypeToken<List<MilestoneResponse>>() {
        }.getType());
    }

    /**
     * Handles the /{id} route. This route returns information about one specific milestone.
     *
     * @param id the id of the milestone to retrieve.
     * @return MilestoneResponse containing data about the milestone.
     * @throws NotFoundException if the milestone is not found.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Information about one milestone project", response = MilestoneResponse.class, authorizations = {@Authorization(value = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public MilestoneResponse getMilestoneById(@ApiParam(required = true, value = "Id of the milestone to retrieve") @PathVariable String id) throws NotFoundException {
        Milestone milestone = milestoneService.findById(UUID.fromString(id));

        if (milestone == null) {
            throw new NotFoundException("Milestone not found");
        }

        return modelMapper.map(milestone, MilestoneResponse.class);
    }

    /**
     * Handles the / route. This route adds a new milestone to the system.
     *
     * @param createMilestoneRequest create milestone request containing the values from the form.
     * @return MilestoneResponse containing data about the created milestone.
     * @throws InternalServerException if an error occurred while creating the milestone.
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    @Transactional
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Adds a new milestone to the system", response = MilestoneResponse.class, authorizations = {@Authorization(value = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public MilestoneResponse createMilestone(@ApiParam("New User") @RequestBody CreateMilestoneRequest createMilestoneRequest) throws InternalServerException, NotFoundException {
        Milestone newMilestone = modelMapper.map(createMilestoneRequest, Milestone.class);

        Project project = projectService.findById(UUID.fromString(createMilestoneRequest.getProject()));
        if (project == null) {
            throw new NotFoundException("Project not found");
        }

        Milestone milestone = milestoneService.createMilestone(project, newMilestone);
        if (milestone == null) {
            throw new InternalServerException("An error occurred while storing the milestone");
        }

        return modelMapper.map(milestone, MilestoneResponse.class);
    }

    /**
     * Handles the /{id} route. This route updates one specific milestone.
     *
     * @param id                     the id of the milestone to update.
     * @param updateMilestoneRequest the update request containing the data from the form.
     * @return MilestoneResponse the updated milestone.
     * @throws NotFoundException if the milestone is not found.
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Update one specific milestone", response = MilestoneResponse.class, authorizations = {@Authorization(value = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public MilestoneResponse updateMilestone(@ApiParam(required = true, value = "Id of the milestone to update") @PathVariable String id, @ApiParam("Update Data") @RequestBody UpdateMilestoneRequest updateMilestoneRequest) throws NotFoundException {
        Milestone existingMilestone = milestoneService.findById(UUID.fromString(id));

        if (existingMilestone == null) {
            throw new NotFoundException("Milestone not found");
        }

        Milestone updateMilestone = MergeUtility.merge(modelMapper.map(updateMilestoneRequest, Milestone.class), existingMilestone);

        Milestone milestone = milestoneService.updateMilestone(updateMilestone);

        return modelMapper.map(milestone, MilestoneResponse.class);
    }

    /**
     * Handles the /{id} route. This routes deletes one specific milestone.
     *
     * @param id the id of the milestone to delete.
     * @throws NotFoundException if the milestone is not found.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    @Transactional
    @ApiOperation(value = "Delete one specific milestone", response = void.class, authorizations = {@Authorization(value = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void deleteMilestone(@ApiParam(required = true, value = "Id of the milestone to delete") @PathVariable String id) throws NotFoundException {
        Milestone milestone = milestoneService.findById(UUID.fromString(id));

        if (milestone == null) {
            throw new NotFoundException("Milestone not found");
        }

        milestoneService.deleteMilestone(milestone);
    }

}
