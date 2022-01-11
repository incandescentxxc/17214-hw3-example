package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.worker.Worker;

/**
 * Player with God Power Artemis.
 * 
 * Capacity: Your Worker may move one additional time, but not back to its
 * initial space.
 */
public class Artemis extends Player {
    private Grid initialGrid = null;
    private boolean hasMoved = false;

    public Artemis(int id, String name) {
        super(id, name);
        this.setPower("Artemis");
    }

    private boolean isMovePosOverlapped(Grid dest) {
        if (initialGrid != null && dest.isSameGrid(this.initialGrid)) {
            return true;
        }
        return false;
    }

    /**
     * checks whether a move is valid
     */
    @Override
    public boolean isMovable(Board board, Grid origin, Grid dest) {

        if (!super.isMovable(board, origin, dest) || isMovePosOverlapped(dest)) {
            return false;
        }
        return true;
    }

    @Override
    public void move(Board board, int posX, int posY) {
        Worker worker = this.getActiveWorker();
        if (this.initialGrid == null) {
            this.initialGrid = worker.getGridOccupied();
        } else {
            this.initialGrid = null;
        }
        board.move(worker.getGridOccupied(), posX, posY);
    }

    /**
     * updates all move-related status
     */
    @Override
    public boolean afterMove(Board board, int posX, int posY) {
        if (!this.hasMoved) {
            hasMoved = true;
            return false;
        }
        hasMoved = false;
        this.initialGrid = null;
        return super.afterMove(board, posX, posY);
    }
}
