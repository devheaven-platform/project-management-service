package nl.devheaven.service.responses;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.UUID;

/**
 * This response is used for sending a milestone.
 */
public class MilestoneResponse implements Comparable<MilestoneResponse> {

    @ApiModelProperty(required = true, value = "The id of the milestone", example = "8371432d-d705-4f41-8bf7-c499d075f871")
    private UUID id;

    @ApiModelProperty(required = true, value = "The name of the milestone", example = "Version 1.0 finished")
    private String name;

    @ApiModelProperty(value = "The description of the milestone", example = "This milestone includes ...")
    private String description;

    @ApiModelProperty(required = true, value = "The date of the milestone", example = "2019-01-01T00:00:00.000Z")
    private Date date;

    @ApiModelProperty(required = true, value = "The date the milestone was created on", example = "2019-01-01T00:00:00.000Z")
    private Date createdAt;

    @ApiModelProperty(required = true, value = "The date the milestone was last updated on", example = "2019-01-01T00:00:00.000Z")
    private Date updatedAt;

    @Override
    public int compareTo(MilestoneResponse milestoneResponse) {
        int result = date.compareTo(milestoneResponse.date);
        return result != 0 ? result : name.compareTo(milestoneResponse.name);
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
