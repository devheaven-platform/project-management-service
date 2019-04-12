package models;

import dto.DeadlineDTO;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Deadline.getDeadlineById", query = "SELECT d FROM Deadline d WHERE d.id = :id")
})
public class Deadline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Calendar deadline;

    private String description;

    private boolean reached;

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

    public boolean isReached() { return reached; }

    public void setReached(boolean reached) { this.reached = reached; }

    /**
     * This method should change the values of the current Deadline to the values of the given DeadlineDTO
     *
     * @param dto this param is a DeadlineDTO containing updated values of the Deadline
     */
    public void updateFromDeadlineDTO(DeadlineDTO dto){
        this.id = dto.getId();
        this.deadline = dto.getDeadline();
        this.description = dto.getDescription();
        this.reached = dto.isReached();
    }
}
