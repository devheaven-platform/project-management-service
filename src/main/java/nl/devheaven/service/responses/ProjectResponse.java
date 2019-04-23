package nl.devheaven.service.responses;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.UUID;

/**
 * This response is used for sending a project.
 */
public class ProjectResponse implements Comparable<ProjectResponse> {

    @ApiModelProperty(required = true, value = "The id of the project", example = "c97853a9-3286-4de8-af7d-565cdb22b8ca")
    private UUID id;

    @ApiModelProperty(required = true, value = "The name of the project", example = "Project 1")
    private String name;

    @ApiModelProperty(value = "The description of the project", example = "This project is ...")
    private String description;

    @ApiModelProperty(value = "The budget of the project", example = "100")
    private float budget;

    @ApiModelProperty(value = "The duration of the project", example = "100")
    private float duration;

    @ApiModelProperty(required = true, value = "The start date of the project", example = "2019-01-01T00:00:00.000Z")
    private Date start;

    @ApiModelProperty(required = true, value = "The owner of the project", example = "95fdb700-d772-4067-8296-89c4ceb73c5f")
    private UUID owner;

    @ApiModelProperty(required = true, value = "The client of the project", example = "c34b267f-b81c-4060-b2bd-e09e3537472c")
    private UUID client;

    @ApiModelProperty(value = "The members of the project", example = "[]")
    private List<UUID> members;

    @ApiModelProperty(value = "The boards of the project", example = "[]")
    private List<UUID> boards;

    @ApiModelProperty(value = "The milestones of the project", example = "[]")
    private SortedSet<MilestoneResponse> milestones;

    @ApiModelProperty(required = true, value = "Whether the project is archived", example = "false")
    private boolean archived;

    @ApiModelProperty(required = true, value = "The date the project was created on", example = "2019-01-01T00:00:00.000Z")
    private Date createdAt;

    @ApiModelProperty(required = true, value = "The date the project was last updated on", example = "2019-01-01T00:00:00.000Z")
    private Date updatedAt;

    @Override
    public int compareTo(ProjectResponse projectResponse) {
        return name.compareTo(projectResponse.name);
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
    public UUID getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the project.
     *
     * @param owner the owner to set.
     */
    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    /**
     * Gets the client of the project.
     *
     * @return the client of the project.
     */
    public UUID getClient() {
        return client;
    }

    /**
     * Sets the client of the project.
     *
     * @param client the client to set.
     */
    public void setClient(UUID client) {
        this.client = client;
    }

    /**
     * Gets the members of the project.
     *
     * @return the members of the project.
     */
    public List<UUID> getMembers() {
        return members;
    }

    /**
     * Sets the members of the project.
     *
     * @param members the members to set.
     */
    public void setMembers(List<UUID> members) {
        this.members = members;
    }

    /**
     * Gets the boards of the project.
     *
     * @return the members of the project.
     */
    public List<UUID> getBoards() {
        return boards;
    }

    /**
     * Sets the boards of the project.
     *
     * @param boards the boards to set.
     */
    public void setBoards(List<UUID> boards) {
        this.boards = boards;
    }

    /**
     * Gets the milestones of the project.
     *
     * @return the milestones of the project.
     */
    public SortedSet<MilestoneResponse> getMilestones() {
        return milestones;
    }

    /**
     * Sets the milestones of the project.
     *
     * @param milestones the milestones to set.
     */
    public void setMilestones(SortedSet<MilestoneResponse> milestones) {
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
