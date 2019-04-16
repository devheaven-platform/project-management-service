package com.devheaven.service.requests;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * This response is used for creating a project.
 */
public class CreateProjectRequest {

    @ApiModelProperty(required = true, value = "The name of the project", example = "Project 1")
    private String name;

    @ApiModelProperty(value = "The description of the project", example = "This project is ...")
    private String description;

    @ApiModelProperty(value = "The budget of the project", example = "100")
    private float budget;

    @ApiModelProperty(required = true, value = "The start date of the project", example = "2019-01-01T00:00:00.000Z")
    private Date start;

    @ApiModelProperty(required = true, value = "The owner of the project", example = "95fdb700-d772-4067-8296-89c4ceb73c5f")
    private String owner;

    @ApiModelProperty(required = true, value = "The client of the project", example = "c34b267f-b81c-4060-b2bd-e09e3537472c")
    private String client;

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

}
