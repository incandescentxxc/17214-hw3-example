package edu.cmu.cs214.hw3.model.board;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.cmu.cs214.hw3.model.tower.Tower;
import edu.cmu.cs214.hw3.model.worker.Worker;
import lombok.Data;

/**
 * Grid represents a grid model in the board. It has the information of its
 * location and contents, e.g. Worker or Tower
 */
@Data
public class Grid {
    private String id;
    @JsonIgnore
    private int positionX;
    @JsonIgnore
    private int positionY;
    private Worker worker;
    private Tower tower;
    private static final int MAX_HEIGHT = 3;

    public Grid(int x, int y) {
        this.id = String.valueOf(x) + String.valueOf(y);
        this.positionX = x;
        this.positionY = y;
        this.worker = null;
        this.tower = null;
    }

    /**
     * If this grid is not occupied, added this worker to the grid.
     * 
     * @param worker
     * @return true if successfully added this worker
     */
    public boolean addWorker(Worker worker) {
        // ensure Grid has no other workers
        if (this.worker != null) {
            System.out.print("Invalid position to place the worker: ");
            System.out.println("This position is occupied!");
            return false;
        }
        this.worker = worker;
        this.worker.setGrid(this);
        return true;
    }

    /**
     * If this grid is not occupied, and the other grid has a valid worker, then we
     * add the worker to this grid, and clear the worker in the other grid.
     * 
     * @param otherGrid
     * @return true if successfully added this worker
     */
    public boolean addWorker(Grid otherGrid) {
        // ensure Grid has no other workers
        if (this.worker != null) {
            System.out.print("Invalid position to place the worker: ");
            System.out.println("This position is occupied!");
            return false;
        }
        Worker worker = otherGrid.getWorker();
        if (worker == null) {
            return false;
        }
        this.worker = otherGrid.getWorker();
        otherGrid.removeWorker();
        this.worker.setGrid(this);
        return true;
    }

    /**
     * remove the worker on this grid, regardless of whether there is one
     */
    public void removeWorker() {
        if (this.worker != null) {
            this.worker = null;
        }
    }

    /**
     * Base logic of checking whether the move from another grid to this grid is
     * valid. The base logic only considers the dome and level restrictions.
     * 
     * @param fromGrid
     * @return true if the move from another grid to this grid is valid.
     */
    public boolean canMove(Grid fromGrid) {
        int curLevel = (this.getTower() == null) ? 0 : this.getTower().getLevel();
        int fromLevel = (fromGrid.getTower() == null) ? 0 : fromGrid.getTower().getLevel();
        if (this.getTower() == null) {
            return true;
        }
        if (curLevel > fromLevel + 1 || this.tower.hasDome()) {
            return false;
        }
        return true;
    }

    /**
     * Base logic of checking whether the build on this grid is valid. Delegate to
     * tower to further check the logic if necessary.
     * 
     * @return true if it is valid to either build a block or build a dome on this
     *         grid
     */
    public boolean canBuildTower() {
        if (this.tower == null) {
            return true;
        }
        return this.tower.canBuildUp();
    }

    /**
     * Check whether it is possible to build a tower by adding a block only
     * 
     * @return true if it is possible to build a tower by adding a block only
     */
    public boolean canBuildBlock() {
        if (this.tower == null) {
            return true;
        }
        return this.tower.canBuildBlock();
    }

    /**
     * Check whether it is possible to build a dome based on basic logic
     * 
     * @return true it is possible to build a dome based on basic logic
     */
    public boolean canBuildDome() {
        return this.tower != null && this.tower.getLevel() == MAX_HEIGHT;
    }

    /**
     * Delegate to tower to actual build the tower if it is valid to build on this
     * grid. If reaches third level, by default add one more dome.
     * 
     */
    public void buildTower() {
        if (this.tower == null) {
            this.tower = new Tower(1);
        } else {
            this.tower.buildUp();
        }
    }

    /**
     * Delegates to tower to build a dome regardless of the current height
     */
    public void buildDome() {
        if (this.tower == null) {
            this.tower = new Tower(0);
            this.tower.buildDome();
        } else {
            this.tower.buildDome();
        }
    }

    /**
     * Getter function of the position of the grid
     * 
     * @return a int[] composed of positionX and positionY
     */
    @JsonIgnore
    public int[] getPosition() {
        int[] position = { this.positionX, this.positionY };
        return position;
    }

    /**
     * Check whether there exists a worker on the thrid level of the tower on this
     * grid
     * 
     * @return the winning worker if it exists, otherwise return null
     */
    @JsonIgnore
    public Worker getWiningWorker() {
        if (this.worker == null) {
            return null;
        }
        if (this.tower != null && this.tower.getLevel() == MAX_HEIGHT && !this.tower.hasDome()) {
            return this.worker;
        }
        return null;
    }

    /**
     * A util function that checks whether this grid is lower than the given grid
     * 
     * @param grid
     * @return true if this grid is lower than the given grid
     */
    public boolean isLowerThan(Grid grid) {
        int curHeight = (this.getTower() == null) ? 0 : this.getTower().getLevel();
        int newHeight = (grid.getTower() == null) ? 0 : grid.getTower().getLevel();
        return curHeight < newHeight;
    }

    /**
     * A util function that checks whether this grid is occupied or not.
     * 
     * @return true if this gird is occupied by a worker.
     */
    @JsonIgnore
    public boolean isOccupied() {
        return this.getWorker() != null;
    }

    /**
     * A util function that checks whether this grid is 2 level higher than the
     * given grid
     * 
     * @param dest
     * @return true if this grid is 2 level higher than the given grid
     */
    public boolean isHigherTwoLevelThan(Grid dest) {
        int curHeight = (this.getTower() == null) ? 0 : this.getTower().getLevel();
        int newHeight = (dest.getTower() == null) ? 0 : dest.getTower().getLevel();
        return curHeight - newHeight >= 2;
    }

    /**
     * Gets the grid which is behind this current grid, given a grid as the original
     * position
     * 
     * @param board
     * @param origin
     * @return grid that is behind this current grid;
     */
    public Grid getBackGrid(Board board, Grid origin) {
        int[] originPos = origin.getPosition();
        int newPosX = this.positionX - originPos[0] + this.positionX;
        int newPosY = this.positionY - originPos[1] + this.positionY;
        return board.getGrid(newPosX, newPosY);
    }

    /**
     * Swaps the workers on different grids, assuming workers exist in these two
     * grids.
     * 
     * @param otherGrid
     */
    public void swapWorker(Grid otherGrid) {
        assert this.worker != null;
        assert otherGrid.getWorker() != null;
        Worker curWorker = this.worker;
        Worker otherWorker = otherGrid.getWorker();
        this.worker = otherWorker;
        otherWorker.setGrid(this);
        otherGrid.setWorker(curWorker);
        curWorker.setGrid(otherGrid);
    }

    /**
     * Checks whether two grids have the same location
     * 
     * @param otherGrid
     * @return true if the two grids have the same location
     */
    public boolean isSameGrid(Grid otherGrid) {
        assert otherGrid != null;
        return this.positionX == otherGrid.getPositionX() && this.positionY == otherGrid.getPositionY();
    }

}
