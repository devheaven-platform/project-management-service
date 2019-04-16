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

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository, ProjectRepository projectRepository){
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Retrieve all milestones
     *
     * @return returns a list containing all milestones in the datasource.
     */
    public List<Milestone> findAll(){
        return milestoneRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    /**
     * Retrieve a specific milestone by it's id.
     *
     * @param id the id of the milestone that will be retrieved.
     * @return returns the Milestone with the given id.
     */
    public Milestone getMilestoneById(UUID id){
        return milestoneRepository.getOne(id);
    }

    /**
     * Create a new milestone.
     *
     * @param project the project to which the milestone will be added.
     * @param milestone the milestone that will be added to the datasource.
     */
    public void createMilestone(Project project, Milestone milestone){
        SortedSet<Milestone> milestones = project.getMilestones();
        milestones.add(milestone);
        project.setMilestones(milestones);
        projectRepository.save(project);
    }

    /**
     * Update a existing milestone.
     *
     * @param milestone the milestone that will be updated with the changed values.
     */
    public void updateMilestone(Milestone milestone){
        milestoneRepository.save(milestone);
    }

    /**
     * Delete a existing milestone.
     *
     * @param milestone the milestone that will be removed from the datasource.
     */
    public void deleteMilestone(Milestone milestone){
        milestoneRepository.delete(milestone);
    }
}
