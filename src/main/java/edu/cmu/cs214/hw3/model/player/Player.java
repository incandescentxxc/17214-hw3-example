package edu.cmu.cs214.hw3.model.player;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.worker.Worker;
import lombok.Data;

/**
 * The Player data model representing a player in the Game
 */
@Data // lombok package for avoiding boiler code
public class Player {
    private int playerId;
    private String name;
    private String power;
    private boolean hasWon;
    @JsonIgnore
    private Player opponent;
    @JsonIgnore
    private List<Worker> workers;
    /**
     * Represents whether it is the turn for this player
     */
    private boolean isActive;

    public Player(int id, String name) {
        this.playerId = id;
        this.name = name;
        this.workers = new ArrayList<>();
        this.isActive = false;
        this.hasWon = false;
        this.power = "Default";
    }

    /**
     * Bind a worker with a player (2 workers at most)
     * 
     * @param worker
     * @return true if it is successfully binded
     */
    public boolean addWorker(Worker worker) {
        if (this.workers.size() >= 2) {
            System.out.println("One player has exactly 2 workers.");
            return false;
        }
        this.workers.add(worker);
        return true;
    }

    /**
     * Getter function for the active status of the player
     * 
     * @return isActive
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * Getter function for the winning status of the player
     * 
     * @return hasWon
     */
    public boolean getHasWon() {
        return this.hasWon;
    }

    /**
     * Setter function for the active status of the player
     * 
     * @param isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Helper function to check whether a worker is in the list of this player
     * 
     * @param worker
     * @return true if this worker is in the list of this player
     */
    public boolean hasRightWorker(Worker worker) {
        return this.workers.contains(worker);
    }

    /**
     * Gets the worker that is not settled (placed on the board)
     * 
     * @return the corresponding worker
     */
    @JsonIgnore
    public Worker getUnsettledWorker() {
        for (Worker worker : this.workers) {
            if (worker.getGridOccupied() == null) {
                return worker;
            }
        }
        return null;
    }

    /**
     * Checks if there is any condition that disallows other player's worker to move
     * 
     * @param board
     * @param worker
     * @param posX
     * @param posY
     * @return true if there is no restriction on other player's move
     */
    public boolean agreeOtherMove(Board board, Worker worker, int posX, int posY) {
        return true;
    }

    /**
     * Checks if there is any condition that disallows other player's worker to
     * build
     * 
     * @param board
     * @param worker
     * @param posX
     * @param posY
     * @return true if there is no restriction on pther player's build
     */
    public boolean agreeOtherBuild(Board board, Worker worker, int posX, int posY) {
        return true;
    }

    /**
     * Checks if it is possible to move from the origin grid to another grid
     * 
     * @param board
     * @param origin
     * @param dest
     * @return true if it is poosible
     */
    public boolean isMovable(Board board, Grid origin, Grid dest) {
        if (origin == null || dest == null) {
            return false;
        }
        // space relationship check
        if (!isBuildable(board, origin, dest)) {
            return false;
        }
        // Delegate to Grid to check this logic
        // quantatitive relationship check
        return dest.canMove(origin);
    }

    /**
     * Checks if it is possible to build a tower on dest grid from the origin grid
     * 
     * @param board
     * @param origin
     * @param dest
     * @return true if it is possible
     */
    public boolean isBuildable(Board board, Grid origin, Grid dest) {
        if (origin == null || dest == null) {
            return false;
        }
        // space relationship check: adjacent
        if (!Board.isAdjacentGrid(origin, dest)) {
            return false;
        }
        // space relationship check: no occupation
        if (dest.isOccupied()) {
            return false;
        }

        if (!dest.canBuildTower()) {
            return false;
        }

        return true;
    }

    /**
     * All game-related logic check before a move
     * 
     * @param board
     * @param posX
     * @param posY
     * @return true if the worker can move to that grid
     */
    public boolean beforeMove(Board board, int posX, int posY) {

        Worker worker = this.getActiveWorker();

        // workers have not been properly placed yet
        if (worker == null) {
            return false;
        }
        // it is the turn of this player and the worker has not been moved in this round
        // yet
        if (!this.isActive || worker.getIsMoved()) {
            return false;
        }
        // check other players' restriction
        if (!opponent.agreeOtherMove(board, worker, posX, posY)) {
            return false;
        }
        // check self restriction
        Grid origin = worker.getGridOccupied();
        Grid dest = board.getGrid(posX, posY);
        return this.isMovable(board, origin, dest);
    }

    /**
     * Actual action of movement
     * 
     * @param board
     * @param posX
     * @param posY
     */
    public void move(Board board, int posX, int posY) {
        Worker worker = this.getActiveWorker();
        board.move(worker.getGridOccupied(), posX, posY);
    }

    /**
     * All round-control-related updates after a move and also checks whether this
     * worker leads to win
     * 
     * @param board
     * @param posX
     * @param posY
     * @return true if move is finished
     */
    public boolean afterMove(Board board, int posX, int posY) {
        Worker worker = this.getActiveWorker();
        worker.setIsMoved(true);

        // logic related to wining
        Grid grid = worker.getGridOccupied();
        if (grid.getWiningWorker() == worker || this.getOpponent().hasLost(board)) {
            this.setHasWon(true);
        }
        return true;
    };

    /**
     * All game-related logic check before a build operation
     * 
     * @param board
     * @param posX
     * @param posY
     * @param buildDome
     * @return true if the build condition is satisfied
     */
    public boolean beforeBuild(Board board, int posX, int posY, int buildDome) {

        Worker worker = this.getActiveWorker();
        // check round restriction
        if (!this.isActive || !worker.getIsMoved()) {
            return false;
        }
        // check other players' restriction
        if (!opponent.agreeOtherBuild(board, worker, posX, posY)) {
            return false;
        }
        // normal player cannot buildDome directly
        if (buildDome == 1) {
            return false;
        }
        // check self restriction
        Grid origin = worker.getGridOccupied();
        Grid dest = board.getGrid(posX, posY);
        return this.isBuildable(board, origin, dest);
    }

    /**
     * Actual action of build
     * 
     * @param board
     * @param posX
     * @param posY
     * @param buildDome
     */
    public void build(Board board, int posX, int posY, int buildDome) {
        board.build(posX, posY, buildDome);
    }

    /**
     * All round-control-related updates after a build and also checks whether this
     * worker leads to win
     * 
     * @param board
     * @param posX
     * @param posY
     * @return true if all build action is finished
     */
    public boolean afterBuild(Board board, int posX, int posY) {
        Worker worker = this.getActiveWorker();
        worker.setActive(false);
        worker.setIsMoved(false);
        this.isActive = false;
        opponent.setIsActive(true);
        // win logic check

        if (this.getOpponent().hasLost(board)) {
            this.setHasWon(true);
        }

        return true;
    };

    /**
     * Checks whether this player has a worker that can move in this particular
     * position
     * 
     * @param board
     * @param posX
     * @param posY
     * @return true if the player has at least one movable worker
     */
    public boolean isSelfMovable(Board board, int posX, int posY) {
        for (Worker worker : this.workers) {
            Grid origin = worker.getGridOccupied();
            Grid dest = board.getGrid(posX, posY);

            if (opponent.agreeOtherMove(board, worker, posX, posY) && this.isMovable(board, origin, dest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether this player is lost
     * 
     * @param board
     * @return true if this player is lost
     */
    public boolean hasLost(Board board) {
        return !board.testPlayerMovable(this);
    }

    /**
     * Get all the active workers
     * 
     * @return a list of active workers
     */
    @JsonIgnore
    public Worker getActiveWorker() {
        for (Worker worker : this.workers) {
            if (worker.getIsActive()) {
                return worker;
            }
        }
        return null;
    }
}
