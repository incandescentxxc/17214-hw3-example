package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.worker.Worker;

/**
 * Represents a player with the god power of Pan
 */
public class Pan extends Player {
    /**
     * whether this player has a worker that went down 2 levels in this round
     */
    private boolean hasDownTwoLevels = false;

    public Pan(int id, String name) {
        super(id, name);
        this.setPower("Pan");
    }

    /**
     * if this move is a two-level-down operation then record it
     */
    @Override
    public void move(Board board, int posX, int posY) {
        Worker worker = super.getActiveWorker();
        hasDownTwoLevels = board.isJump(worker.getGridOccupied(), posX, posY);
        board.move(worker.getGridOccupied(), posX, posY);
    }

    /**
     * checks whether this player won, espcially given the power of Pan
     */
    @Override
    public boolean afterMove(Board board, int posX, int posY) {
        if (hasDownTwoLevels) {
            this.setHasWon(true);
        }
        return super.afterMove(board, posX, posY);
    }

}
