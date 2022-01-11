package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class Athena extends Player {

    /**
     * records whether this player has a worker that moved up
     */
    private boolean hasMovedUp = false;

    public Athena(int id, String name) {
        super(id, name);
        this.setPower("Athena");
    }

    /**
     * checks the status and whether allows others to move
     */
    @Override
    public boolean agreeOtherMove(Board board, Worker worker, int posX, int posY) {
        if (board.isMoveUp(worker.getGridOccupied(), posX, posY) && this.hasMovedUp) {
            return false;
        }
        return true;
    }

    /**
     * actually moves the worker and records whether this is a move-up
     */
    @Override
    public void move(Board board, int posX, int posY) {
        Worker worker = super.getActiveWorker();
        this.hasMovedUp = board.isMoveUp(worker.getGridOccupied(), posX, posY);
        board.move(worker.getGridOccupied(), posX, posY);
    }
}
