package com.devheaven.service.services.impl;

import com.devheaven.service.models.Milestone;
import com.devheaven.service.models.Project;
import com.devheaven.service.repositories.MilestoneRepository;
import com.devheaven.service.repositories.ProjectRepository;
import com.devheaven.service.services.IMilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedSet;
import java.util.UUID;

@Service
public class MilestoneService implements IMilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Milestone> findAll(){
        return milestoneRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    @Override
    public Milestone getMilestoneById(UUID id){
        return milestoneRepository.findById(id).orElse(null);
    }

    @Override
    public void createMilestone(Project project, Milestone milestone){
        SortedSet<Milestone> milestones = project.getMilestones();
        milestones.add(milestone);
        project.setMilestones(milestones);
        projectRepository.save(project);
    }

    @Override
    public void updateMilestone(Milestone milestone){
        milestoneRepository.save(milestone);
    }

    @Override
    public void deleteMilestone(Milestone milestone){
        milestoneRepository.delete(milestone);
    }
}
