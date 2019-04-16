package com.devheaven.service.controllers;

import com.devheaven.service.exceptions.InternalServerException;
import com.devheaven.service.exceptions.NotFoundException;
import com.devheaven.service.requests.CreateMilestoneRequest;
import com.devheaven.service.requests.UpdateMilestoneRequest;
import com.devheaven.service.responses.MilestoneResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.SortedSet;

/**
 * This controller handles the requests for the milestone model.
 * It listens on the /milestones route.
 */
@RestController
@RequestMapping(path = "/milestones")
@Api(tags = "Milestone")
public class MilestoneController {

    /**
     * Handles the / route. This route returns all the milestones in the system.
     *
     * @return List<MilestoneResponse> containing data about the milestones in the system.
     */
    @GetMapping("/")
    @ApiOperation(value = "List all milestones in the system", response = MilestoneResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public SortedSet<MilestoneResponse> getMilestones() {
        throw new UnsupportedOperationException();
    }

    /**
     * Handles the /{id} route. This route returns information about one specific milestone.
     *
     * @param id the id of the milestone to retrieve.
     * @return MilestoneResponse containing data about the milestone.
     * @throws NotFoundException if the milestone is not found.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Information about one milestone project", response = MilestoneResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public MilestoneResponse getMilestoneById(@ApiParam(required = true, value = "Id of the milestone to retrieve") @PathVariable String id) throws NotFoundException {
        throw new UnsupportedOperationException();
    }

    /**
     * Handles the / route. This route adds a new milestone to the system.
     *
     * @param  createMilestoneRequest create milestone request containing the values from the form.
     * @return MilestoneResponse containing data about the created milestone.
     * @throws InternalServerException if an error occurred while creating the milestone.
     */
    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Adds a new milestone to the system", response = MilestoneResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public MilestoneResponse createMilestone(@ApiParam("New User") @RequestBody CreateMilestoneRequest createMilestoneRequest) throws InternalServerException {
        throw new UnsupportedOperationException();
    }

    /**
     * Handles the /{id} route. This route updates one specific milestone.
     *
     * @param id the id of the milestone to update.
     * @param updateMilestoneRequest the update request containing the data from the form.
     * @return MilestoneResponse the updated milestone.
     * @throws NotFoundException if the milestone is not found.
     */
    @PatchMapping("/{id}")
    @ApiOperation(value = "Update one specific milestone", response = MilestoneResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public MilestoneResponse updateMilestone(@ApiParam(required = true, value = "Id of the milestone to update") @PathVariable String id, @ApiParam("Update Data") @RequestBody UpdateMilestoneRequest updateMilestoneRequest) throws NotFoundException {
        throw new UnsupportedOperationException();
    }

    /**
     * Handles the /{id} route. This routes deletes one specific milestone.
     *
     * @param id the id of the milestone to delete.
     * @throws NotFoundException if the milestone is not found.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete one specific milestone", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void deleteMilestone(@ApiParam(required = true, value = "Id of the milestone to delete") @PathVariable String id) throws NotFoundException {
        throw new UnsupportedOperationException();
    }

}
