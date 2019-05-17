package nl.devheaven.service.requests;

/**
 * This request is used to remove a board from a project.
 */
public class DeleteBoardRequest {

    private String board;

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

}
