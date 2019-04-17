package com.devheaven.service.repositories;

import com.devheaven.service.models.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * This repository is the data access layer for Milestone class.
 */
@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, UUID> {
}
