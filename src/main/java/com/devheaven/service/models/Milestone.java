package com.devheaven.service.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * This model represents a milestone in the system.
 */
@Entity
public class Milestone implements Comparable<Milestone> {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    private Date date;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Override
    public int compareTo(Milestone milestone) {
        int result = date.compareTo(milestone.date);
        return result != 0 ? result : name.compareTo(milestone.name);
    }

    /**
     * Gets the id of the milestone.
     *
     * @return the id of the milestone.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the id of the milestone.
     *
     * @param id the id to set.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the name of the milestone.
     *
     * @return the name of the milestone.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the milestone.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the milestone.
     *
     * @return the description of the milestone.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the milestone.
     *
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of the milestone.
     *
     * @return the date of the milestone.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the milestone.
     *
     * @param date the date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the createdAt date of the milestone.
     *
     * @return the createdAt date of the milestone.
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the createdAt date of the milestone.
     *
     * @param createdAt the date to set.
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the updatedAt date of the milestone.
     *
     * @return the updatedAt date of the milestone.
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the updatedAt date of the milestone.
     *
     * @param updatedAt the date to set.
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
