package com.devheaven.service.requests;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * This response is used for updating a milestone.
 */
public class UpdateMilestoneRequest {

    @ApiModelProperty(required = true, value = "The name of the milestone", example = "Version 1.0 finished")
    private String name;

    @ApiModelProperty(value = "The description of the milestone", example = "This milestone includes ...")
    private String description;

    @ApiModelProperty(required = true, value = "The date of the milestone", example = "2019-01-01T00:00:00.000Z")
    private Date date;

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
}