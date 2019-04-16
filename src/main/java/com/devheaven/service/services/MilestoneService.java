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

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Milestone> findAll(){
        return milestoneRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    public Milestone getMilestoneById(UUID id){
        return milestoneRepository.findById(id).orElse(null);
    }

    public void createMilestone(Project project, Milestone milestone){
        SortedSet<Milestone> milestones = project.getMilestones();
        milestones.add(milestone);
        project.setMilestones(milestones);
        projectRepository.save(project);
    }

    public void updateMilestone(Milestone milestone){
        milestoneRepository.save(milestone);
    }

    public void deleteMilestone(Milestone milestone){
        milestoneRepository.delete(milestone);
    }
}
