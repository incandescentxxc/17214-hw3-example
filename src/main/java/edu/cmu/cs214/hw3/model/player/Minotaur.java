package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.worker.Worker;

/**
 * Player with God Power Minotaur.
 * 
 * Capacity: Your Worker may move into an opponent Worker’s space, if their
 * Worker can be forced one space straight backwards to an unoccupied space at
 * any level.
 */
public class Minotaur extends Player {

    public Minotaur(int id, String name) {
        super(id, name);
        this.setPower("Minotaur");
    }

    /**
     * checks whether two grids are reachable based on Minotaur's rule
     */
    @Override
    public boolean isMovable(Board board, Grid origin, Grid dest) {
        if (!Board.isAdjacentGrid(origin, dest)) {
            return false;
        }
        if (dest.isOccupied() && origin.getWorker().getPlayerOwned() != dest.getWorker().getPlayerOwned()) {

            Grid newGrid = dest.getBackGrid(board, origin);
            if (newGrid != null && !newGrid.isOccupied()) {
                return true;
            }
        } else if (!dest.isOccupied()) {
            return dest.canMove(origin);
        }
        return false;
    }

    /**
     * actual move the worker
     */
    @Override
    public void move(Board board, int posX, int posY) {
        Worker worker = super.getActiveWorker();
        Grid origin = worker.getGridOccupied();
        Grid dest = board.getGrid(posX, posY);
        if (dest.isOccupied()) {
            Grid newGrid = dest.getBackGrid(board, origin);
            board.move(dest, newGrid.getPositionX(), newGrid.getPositionY());
        }
        board.move(origin, posX, posY);
    }

}
