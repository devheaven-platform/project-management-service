package dto;

import models.Deadline;
import models.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProjectDTO {

    private UUID id;

    private UUID owner;

    private List<String> members;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private float budget;

    private List<Deadline> deadlines;

    private UUID client;

    private boolean archived;

    public ProjectDTO(UUID id, UUID owner, List<String> members, String name, String description, Date startDate, Date endDate, float budget, List<Deadline> deadlines, UUID client, boolean archived) {
        this.id = id;
        this.owner = owner;
        this.members = members;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.deadlines = deadlines;
        this.client = client;
        this.archived = archived;
    }

    public ProjectDTO(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
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

    public Project convertToProject(){
        Project project = new Project();

        List<UUID> UUIDMembers = new ArrayList<>();
        for(String member : members){
            UUIDMembers.add(UUID.fromString(member));
        }
        project.setMembers(UUIDMembers);
        project.setBudget(this.budget);
        project.setName(this.name);
        project.setArchived(this.archived);
        project.setClient(this.client);
        project.setDeadlines(this.deadlines);
        project.setEndDate(this.endDate);
        project.setStartDate(this.startDate);
        project.setDescription(this.description);
        project.setOwner(this.owner);
        project.setId(this.id);

        return project;
    }
}
