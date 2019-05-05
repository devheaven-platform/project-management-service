package nl.devheaven.service.requests;

/**
 * This request is used to add a board to a project.
 */
public class AddBoardRequest {

    private String board;
    private String project;

    /**
     * Gets the board id.
     *
     * @return the id of the board.
     */
    public String getBoard() {
        return board;
    }

    /**
     * Sets the board id.
     *
     * @param board the board id to set.
     */
    public void setBoard(String board) {
        this.board = board;
    }

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

}
