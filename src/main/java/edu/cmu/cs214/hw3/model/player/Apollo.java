package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.worker.Worker;

/**
 * Player with God Power Apollo.
 * 
 * Capacity: Your Worker may move into an opponent Worker’s space by forcing
 * their Worker to the space yours just vacated.
 */
public class Apollo extends Player {

    public Apollo(int id, String name) {
        super(id, name);
        this.setPower("Apollo");
    }

    /**
     * checks whether two grids are reachable based on Apollo's rule
     */
    @Override
    public boolean isMovable(Board board, Grid origin, Grid dest) {
        if (!Board.isAdjacentGrid(origin, dest)) {
            return false;
        }
        if (dest.isOccupied() && origin.getWorker().getPlayerOwned() != dest.getWorker().getPlayerOwned()) {
            return true;
        } else if (!dest.isOccupied()) {
            return dest.canMove(origin);
        }
        return false;
    }

    /**
     * actual swap the two workers if necessary
     */
    @Override
    public void move(Board board, int posX, int posY) {
        Worker worker = super.getActiveWorker();
        Grid origin = worker.getGridOccupied();
        Grid dest = board.getGrid(posX, posY);
        if (dest.isOccupied()) {
            dest.swapWorker(origin);
        } else {
            board.move(origin, posX, posY);
        }
    }
}
