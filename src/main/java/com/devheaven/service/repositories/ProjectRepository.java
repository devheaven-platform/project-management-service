package com.devheaven.service.repositories;

import com.devheaven.service.models.Milestone;
import com.devheaven.service.models.Project;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This repository is the data access layer for Project class.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    /**
     * Gets all projects that have a specific member.
     *
     * @param member the member to get the project for.
     * @param sort   the value to sort on.
     * @return list of projects.
     */
    List<Project> findByMembersContains(UUID member, Sort sort);

    /**
     * Returns a project containing a
     * specific milestone.
     *
     * @param milestone the milestone to search for.
     * @return project or null
     */
    Optional<Project> findFirstByMilestonesContains(Milestone milestone);

}
