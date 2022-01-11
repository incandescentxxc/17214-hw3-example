package edu.cmu.cs214.hw3.model.board;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs214.hw3.model.board.filterStrategies.GridsFilter;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

/**
 * Represents the Board model, which contains all the grids. Provides functions
 * of verifying the space relationship of grids, and manipulates the grids
 * contents by delegation.
 */
public class Board {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;

    private Grid[][] grids;

    public Board() {
        this.grids = new Grid[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                this.grids[i][j] = new Grid(i, j);
            }
        }
    }

    public Grid[][] getGrids() {
        return this.grids;
    }

    /**
     * Get a single grid according to positionX and positionY,
     * 
     * @param positionX
     * @param positionY
     * @return {@link Grid}; null if the position given is invalid
     */
    public Grid getGrid(int positionX, int positionY) {
        if (positionX >= HEIGHT || positionX < 0 || positionY >= WIDTH || positionY < 0) {
            return null;
        }
        return this.grids[positionX][positionY];
    }

    /**
     * Checks whether a position pair is valid for the board.
     * 
     * @param positionX
     * @param positionY
     * @return true if the position is valid
     */
    public boolean hasGrid(int positionX, int positionY) {
        return !(positionX >= HEIGHT || positionX < 0 || positionY >= WIDTH || positionY < 0);
    }

    /**
     * Delegates Grid to record the info of worker if the position is valid
     * regardless of the validity of worker
     * 
     * @param worker
     * @param positionX
     * @param positionY
     * @return if position is invalid or grid is occupied, return false
     */
    public boolean placeWorker(Worker worker, int positionX, int positionY) {
        Grid gridOccpuied = getGrid(positionX, positionY);
        if (gridOccpuied == null) {
            System.out.print("Invalid position to place the worker: ");
            System.out.println("Position out of bound!");
            return false;
        }
        if (gridOccpuied.addWorker(worker)) {
            return true;
        }
        return false;
    }

    /**
     * A util function that verifies whether two grids are adjacent to each other
     * 
     * @param grid1
     * @param grid2
     * @return true if two grids are adjacent to each other
     */
    public static boolean isAdjacentGrid(Grid grid1, Grid grid2) {
        assert grid1 != null;
        assert grid2 != null;
        int[] pos1 = grid1.getPosition();
        int[] pos2 = grid2.getPosition();
        if (pos1[0] == pos2[0] && pos1[1] == pos2[1]) {
            return false;
        }
        if (Math.abs(pos1[0] - pos2[0]) <= 1 && Math.abs(pos1[1] - pos2[1]) <= 1) {
            return true;
        }
        return false;
    }

    /**
     * Delegates {@link Grid} to acutally build the tower regardless of whether the
     * build statisfies the requirement
     * 
     * @pre the build operation is valid
     * @param positionX
     * @param positionY
     * @param buildDome if 1 then build a dome
     */
    public void build(int positionX, int positionY, int buildDome) {
        assert this.getGrid(positionX, positionY) != null;
        Grid toGrid = this.getGrid(positionX, positionY);
        if (buildDome == 1) {
            toGrid.buildDome();
        } else {
            toGrid.buildTower();
        }
    }

    /**
     * Delegates {@link Grid} to acutally build the tower regardless of whether the
     * build statisfies the requirement
     * 
     * @param curGrid
     * @param positionX
     * @param positionY
     */
    public void move(Grid curGrid, int positionX, int positionY) {
        assert this.getGrid(positionX, positionY) != null;
        Grid toGrid = this.getGrid(positionX, positionY);
        toGrid.addWorker(curGrid);
    }

    /**
     * A util function that checks whether a move from {@link Grid origin} to
     * {@link Grid dest} is upward
     * 
     * @param origin
     * @param posX
     * @param posY
     * @return true if this move is upward
     */
    public boolean isMoveUp(Grid origin, int posX, int posY) {
        assert this.getGrid(posX, posY) != null;
        Grid dest = this.getGrid(posX, posY);
        if (dest == null) {
            return false;
        }
        return origin.isLowerThan(dest);
    }

    /**
     * A util function that checks whether a move from {@link Grid origin} to
     * {@link Grid dest} is downward
     * 
     * @param origin
     * @param posX
     * @param posY
     * @return true if this move is downward
     */
    public boolean isJump(Grid origin, int posX, int posY) {
        assert this.getGrid(posX, posY) != null;
        Grid dest = this.getGrid(posX, posY);
        return origin.isHigherTwoLevelThan(dest);
    }

    /**
     * 
     * A util function that filters out all operable grids given filter and player
     * 
     * @param filter
     * @param player
     * @return The ids of all operable grids
     */
    public List<String> getOperableGrids(GridsFilter filter, Player player) {
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (filter.isThisGridOperable(this, grids[i][j], player)) {
                    ids.add(grids[i][j].getId());
                }
            }
        }
        return ids;
    }

    /**
     * A util function that test whether the player still has movable workers
     * 
     * @param player
     * @return true if this player still has movable workers
     */
    public boolean testPlayerMovable(Player player) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (player.isSelfMovable(this, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
