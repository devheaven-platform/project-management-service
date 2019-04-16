package com.devheaven.service.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

/**
 * This model represents a project in the system.
 */
@Entity
public class Project implements Comparable<Project> {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    private float budget;

    private Date start;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false, updatable = false)
    private String client;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> members;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> boards;

    @SortNatural
    @OneToMany(fetch = FetchType.LAZY)
    private SortedSet<Milestone> milestones;

    private boolean archived;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Override
    public int compareTo(Project project) {
        return name.compareTo(project.name);
    }

    /**
     * Constructor for the project model.
     */
    public Project() {
        members = new ArrayList<>();
        milestones = new TreeSet<>();
    }

    /**
     * Gets the id of the project.
     *
     * @return the id of the project.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the id of the project.
     *
     * @param id the id to set.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the name of the project.
     *
     * @return the name of the project.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the project.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the project.
     *
     * @return the description of the project.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the project.
     *
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the budget of the project.
     *
     * @return the budget of the project.
     */
    public float getBudget() {
        return budget;
    }

    /**
     * Sets the budget of the project.
     *
     * @param budget the budget to set.
     */
    public void setBudget(float budget) {
        this.budget = budget;
    }

    /**
     * Gets the start date of the project.
     *
     * @return the start date of the project.
     */
    public Date getStart() {
        return start;
    }

    /**
     * Sets the start date of the project.
     *
     * @param start the date to set.
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Gets the owner of the project.
     *
     * @return the owner of the project.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the project.
     *
     * @param owner the owner to set.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the client of the project.
     *
     * @return the client of the project.
     */
    public String getClient() {
        return client;
    }

    /**
     * Sets the client of the project.
     *
     * @param client the client to set.
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * Gets the members of the project.
     *
     * @return the members of the project.
     */
    public List<String> getMembers() {
        return members;
    }

    /**
     * Sets the members of the project.
     *
     * @param members the members to set.
     */
    public void setMembers(List<String> members) {
        this.members = members;
    }

    public boolean addMember(String member) {
        return members.add(member);
    }

    public boolean removeMember(String member) {
        return members.remove(member);
    }

    /**
     * Gets the boards of the project.
     *
     * @return the members of the project.
     */
    public List<String> getBoards() {
        return boards;
    }

    /**
     * Sets the boards of the project.
     *
     * @param boards the boards to set.
     */
    public void setBoards(List<String> boards) {
        this.boards = boards;
    }

    /**
     * Gets the milestones of the project.
     *
     * @return the milestones of the project.
     */
    public SortedSet<Milestone> getMilestones() {
        return milestones;
    }

    /**
     * Sets the milestones of the project.
     *
     * @param milestones the milestones to set.
     */
    public void setMilestones(SortedSet<Milestone> milestones) {
        this.milestones = milestones;
    }

    /**
     * Gets whether the project is archived.
     *
     * @return whether the project is archived.
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * Sets whether the project is archived.
     *
     * @param archived the archived value to set.
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
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