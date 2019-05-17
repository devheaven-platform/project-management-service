package nl.devheaven.service.requests;

import io.swagger.annotations.ApiModelProperty;
import nl.devheaven.service.models.Identifier;

import java.util.Date;
import java.util.UUID;

/**
 * This request is used for updating a project.
 */
public class UpdateProjectRequest {

    @ApiModelProperty(required = true, value = "The name of the project", example = "Project 1")
    private String name;

    @ApiModelProperty(value = "The description of the project", example = "This project is ...")
    private String description;

    @ApiModelProperty(value = "The budget of the project", example = "100")
    private float budget;

    @ApiModelProperty(value = "The identifier of the project", example = "STORY_POINTS")
    private Identifier identifier;

    @ApiModelProperty(value = "The profit margin of the project", example = "20")
    private double profitMargin;

    @ApiModelProperty(value = "The price per point of the project", example = "50")
    private double pricePerPoint;

    @ApiModelProperty(required = true, value = "The start date of the project", example = "2019-01-01T00:00:00.000Z")
    private Date start;

    @ApiModelProperty(required = true, value = "The owner of the project", example = "95fdb700-d772-4067-8296-89c4ceb73c5f")
    private UUID owner;

    @ApiModelProperty(required = true, value = "Whether the project is archived", example = "false")
    private boolean archived;

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
     * Gets the point indentifier of the project.
     *
     * @return the identifier of the project.
     */
    public Identifier getIdentifier() { return identifier; }

    /**
     * Sets the point identifier of the project.
     *
     * @param identifier the identifier to set.
     */
    public void setIdentifier(Identifier identifier) { this.identifier = identifier; }

    /**
     * Gets the price per point of the project.
     *
     * @return the price per point of the project.
     */
    public double getPricePerPoint() { return pricePerPoint; }

    /**
     * Sets the price per point of the project.
     *
     * @param pricePerPoint the price per point to set.
     */
    public void setPricePerPoint(double pricePerPoint) { this.pricePerPoint = pricePerPoint; }

    /**
     * Gets the profit margin of the project.
     *
     * @return the profit margin of the project.
     */
    public double getProfitMargin() { rprofit; }

    /**
     * Sets the profit margin of the project.
     *
     * @profit the profit margin to set.
     */
    public void setProfitMargin(dprofit) {profit; }

}
