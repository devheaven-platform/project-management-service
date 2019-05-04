package nl.devheaven.service.requests;

import java.util.List;

/**
 * This request is used to delete all boards when a project
 * is deleted.
 */
public class DeleteProjectRequest {

    private String project;
    private List<String> boards;

    /**
     * Gets the id of the project.
     *
     * @return the id of the project.
     */
    public String getProject() {
        return project;
    }

    /**
     * Sets the id of the project.
     *
     * @param project the project id to set.
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Gets the board id's.
     *
     * @return the id's of the boards.
     */
    public List<String> getBoards() {
        return boards;
    }

    /**
     * Sets the list of board id's.
     *
     * @param boards the list of board id's to set.
     */
    public void setBoards(List<String> boards) {
        this.boards = boards;
    }

}
