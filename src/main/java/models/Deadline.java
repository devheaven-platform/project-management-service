package models;

import javax.persistence.*;
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
    private int id;

    private Date deadline;

    private String description;

    private boolean reached;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
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
}
