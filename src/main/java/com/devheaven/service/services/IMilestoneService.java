package com.devheaven.service.services;

import com.devheaven.service.models.Milestone;
import com.devheaven.service.models.Project;

import java.util.List;
import java.util.UUID;

public interface IMilestoneService {

    /**
     * Retrieve all milestones
     *
     * @return returns a list containing all milestones in the datasource.
     */
    List<Milestone> findAll();

    /**
     * Retrieve a specific milestone by it's id.
     *
     * @param id the id of the milestone that will be retrieved.
     * @return returns the Milestone with the given id.
     */
    Milestone getMilestoneById(UUID id);

    /**
     * Create a new milestone.
     *
     * @param project the project to which the milestone will be added.
     * @param milestone the milestone that will be added to the datasource.
     */
    void createMilestone(Project project, Milestone milestone);

    /**
     * Update a existing milestone.
     *
     * @param milestone the milestone that will be updated with the changed values.
     */
    void updateMilestone(Milestone milestone);

    /**
     * Delete a existing milestone.
     *
     * @param milestone the milestone that will be removed from the datasource.
     */
    void deleteMilestone(Milestone milestone);
}
