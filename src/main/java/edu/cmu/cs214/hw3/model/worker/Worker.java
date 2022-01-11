package edu.cmu.cs214.hw3.model.worker;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;
import lombok.Data;

/**
 * Represents a worker model in the game.
 */
@Data
public class Worker {
    private int workerId;
    @JsonIgnore
    private Grid gridOccupied;
    @JsonIgnore
    private Player playerOwned;
    /**
     * isActive represents whether the worker is selected or not
     */
    @JsonIgnore
    private boolean isActive;
    /**
     * isMoved represents whether the worker has finished move operation in a round
     */
    @JsonIgnore
    private boolean isMoved;

    public Worker(int id, Player player) {
        this.workerId = id;
        this.gridOccupied = null;
        this.playerOwned = player;
        isMoved = false;
    }

    /**
     * Getter function of isMoved field
     * 
     * @return isMoved
     */
    @JsonIgnore
    public boolean getIsMoved() {
        return this.isMoved;
    }

    /**
     * Setter function of isMoved field
     * 
     * @param status
     */
    public void setIsMoved(boolean status) {
        this.isMoved = status;
    }

    /**
     * Records the grid info regardless of its validity
     * 
     * @param grid
     */
    public void setGrid(Grid grid) {
        assert grid != null;
        this.gridOccupied = grid;
    }

    /**
     * Getter function of isActive field
     * 
     * @return isActive
     */
    public boolean getIsActive() {
        return this.isActive;
    }

}
