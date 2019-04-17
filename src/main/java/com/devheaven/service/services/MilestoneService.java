package com.devheaven.service.services;

import com.devheaven.service.models.Milestone;
import com.devheaven.service.models.Project;
import com.devheaven.service.repositories.MilestoneRepository;
import com.devheaven.service.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedSet;
import java.util.UUID;

/**
 * Interacts with the data access layer.
 */
@Service
public class MilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Gets all milestones in the system.
     *
     * @return a list of milestones.
     */
    public List<Milestone> findAll(){
        return milestoneRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    /**
     * Gets one milestone by its id.
     *
     * @param id the id of the milestone to retrieve.
     * @return the found milestone or null.
     */
    public Milestone findById(UUID id){
        return milestoneRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new milestone to the system.
     *
     * @param project the project to add the milestone to.
     * @param newMilestone the milestone to add.
     * @return the new milestone.
     */
    public Milestone createMilestone(Project project, Milestone newMilestone){
        Milestone milestone = milestoneRepository.save(newMilestone);

        project.addMilestone(milestone);
        projectRepository.save(project);

        return milestone;
    }

    /**
     * Updates a existing milestone.
     *
     * @param milestone the milestone to update.
     * @return the updated milestone.
     */
    public Milestone updateMilestone(Milestone milestone){
        return milestoneRepository.save(milestone);
    }

    /**
     * Deletes a milestone.
     *
     * @param milestone the milestone to delete.
     */
    public void deleteMilestone(Milestone milestone){
        // TODO: probably delete milestone from project or use some type of cascade

        milestoneRepository.delete(milestone);
    }
}
