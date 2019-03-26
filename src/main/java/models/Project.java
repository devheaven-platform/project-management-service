package models;

import dto.ProjectDTO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@NamedQueries(
        @NamedQuery(name = "Project.getProjectById", query = "select p from Project p where p.id = :id")
)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    private UUID owner;

    //@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> members;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private float budget;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Deadline> deadlines;

    private UUID client;

    private boolean archived;

    public Project(){
        members = new ArrayList<>();
        deadlines = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public List<UUID> getMembers() {
        List<UUID> UUIDMembers = new ArrayList<>();
        for(String member : members){
            UUIDMembers.add(UUID.fromString(member));
        }

        return UUIDMembers;
    }

    public void setMembers(List<UUID> members) {
        List<String> stringMembers = new ArrayList<>();
        for(UUID member : members){
            stringMembers.add(String.valueOf(member));
        }

        this.members = stringMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public List<Deadline> getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(List<Deadline> deadlines) {
        this.deadlines = deadlines;
    }

    public UUID getClient() {
        return client;
    }

    public void setClient(UUID client) {
        this.client = client;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    /**
     * This method should change the values of the current Project to the values of the given ProjectDTO
     *
     * @param dto this param is a ProjectDTO containing updated values of the Project
     */
    public void updateFromProjectDTO(ProjectDTO dto){
        this.archived = dto.isArchived();
        this.budget = dto.getBudget();
        this.client = dto.getClient();
        this.deadlines = dto.getDeadlines();
        this.description = dto.getDescription();
        this.endDate = dto.getEndDate();
        this.startDate = dto.getStartDate();
        this.name = dto.getName();
        this.owner = dto.getOwner();
        this.members = dto.getMembers();
    }
}
