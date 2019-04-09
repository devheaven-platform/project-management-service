package dto;

import models.Deadline;

import java.util.Calendar;
import java.util.UUID;

public class DeadlineDTO {
    private UUID id;

    private Calendar deadline;

    private String description;

    private boolean reached;

    public DeadlineDTO(){}

    public DeadlineDTO(Deadline deadline){
        this.id = deadline.getId();
        this.deadline = deadline.getDeadline();
        this.description = deadline.getDescription();
        this.reached = deadline.isReached();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }
}
