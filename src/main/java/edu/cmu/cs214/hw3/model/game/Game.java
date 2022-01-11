package edu.cmu.cs214.hw3.model.game;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.cmu.cs214.hw3.controller.dto.GameInitBody;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.filterStrategies.BuildFilter;
import edu.cmu.cs214.hw3.model.board.filterStrategies.DefaultFilter;
import edu.cmu.cs214.hw3.model.board.filterStrategies.GridsFilter;
import edu.cmu.cs214.hw3.model.board.filterStrategies.MoveFilter;
import edu.cmu.cs214.hw3.model.board.filterStrategies.PlaceFilter;
import edu.cmu.cs214.hw3.model.board.filterStrategies.SelectFilter;
import edu.cmu.cs214.hw3.model.player.Apollo;
import edu.cmu.cs214.hw3.model.player.Artemis;
import edu.cmu.cs214.hw3.model.player.Athena;
import edu.cmu.cs214.hw3.model.player.Atlas;
import edu.cmu.cs214.hw3.model.player.Demeter;
import edu.cmu.cs214.hw3.model.player.Hephaestus;
import edu.cmu.cs214.hw3.model.player.Minotaur;
import edu.cmu.cs214.hw3.model.player.Pan;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;
import lombok.Data;

/**
 * A model of the whole game. Stores all game-related data models such as
 * Player, Board, Worker etc. Also stores all game-related status, such as game
 * status. Delegates all actions to corresponding data models to finish those
 * actions.
 * 
 */
@Data
public class Game {
    private String id;
    private GameStatus gameStatus;
    private Player player1;
    private Player player2;
    @JsonIgnore
    private Worker worker1;
    @JsonIgnore
    private Worker worker2;
    @JsonIgnore
    private Worker worker3;
    @JsonIgnore
    private Worker worker4;
    private Board board;
    private List<String> operableGridIds;

    @JsonIgnore
    private HashMap<Integer, Player> playerMap;
    @JsonIgnore
    private HashMap<Integer, Worker> workerMap;

    private static final int PLAYERID_1 = 0;
    private static final int PLAYERID_2 = 1;
    private static final int WORKERID_1 = 0;
    private static final int WORKERID_2 = 1;
    private static final int WORKERID_3 = 2;
    private static final int WORKERID_4 = 3;

    public Game(GameInitBody body) {
        this.board = new Board();
        switch (body.getPower1()) {
        case ATHENA:
            this.player1 = new Athena(PLAYERID_1, body.getName1());
            break;
        case DEMETER:
            this.player1 = new Demeter(PLAYERID_1, body.getName1());
            break;
        case MINOTAUR:
            this.player1 = new Minotaur(PLAYERID_1, body.getName1());
            break;
        case PAN:
            this.player1 = new Pan(PLAYERID_1, body.getName1());
            break;
        case APOLLO:
            this.player1 = new Apollo(PLAYERID_1, body.getName1());
            break;
        case ARTEMIS:
            this.player1 = new Artemis(PLAYERID_1, body.getName1());
            break;
        case ATLAS:
            this.player1 = new Atlas(PLAYERID_1, body.getName1());
            break;
        case HEPHAESTUS:
            this.player1 = new Hephaestus(PLAYERID_1, body.getName1());
            break;
        case HERMES:
            this.player1 = new Hephaestus(PLAYERID_1, body.getName1());
            break;
        case PROMETHEUS:
            this.player1 = new Hephaestus(PLAYERID_1, body.getName1());
            break;
        default:
            this.player1 = new Player(PLAYERID_1, body.getName1());
        }
        switch (body.getPower2()) {
        case ATHENA:
            this.player2 = new Athena(PLAYERID_2, body.getName2());
            break;
        case DEMETER:
            this.player2 = new Demeter(PLAYERID_2, body.getName2());
            break;
        case MINOTAUR:
            this.player2 = new Minotaur(PLAYERID_2, body.getName2());
            break;
        case PAN:
            this.player2 = new Pan(PLAYERID_2, body.getName2());
            break;
        case APOLLO:
            this.player2 = new Apollo(PLAYERID_2, body.getName2());
            break;
        case ARTEMIS:
            this.player2 = new Artemis(PLAYERID_2, body.getName2());
            break;
        case ATLAS:
            this.player2 = new Atlas(PLAYERID_2, body.getName2());
            break;
        case HEPHAESTUS:
            this.player2 = new Hephaestus(PLAYERID_2, body.getName2());
            break;
        case HERMES:
            this.player2 = new Hephaestus(PLAYERID_2, body.getName2());
            break;
        case PROMETHEUS:
            this.player2 = new Hephaestus(PLAYERID_2, body.getName2());
            break;
        default:
            this.player2 = new Player(PLAYERID_2, body.getName2());
        }
        gameStatus = GameStatus.INIT;
        this.updateOperableGridIds(null);
        this.player1.setOpponent(this.player2);
        this.player2.setOpponent(this.player1);
        if (body.getStartPlayer() == PLAYERID_1) {
            this.player1.setIsActive(true);
        } else {
            this.player2.setIsActive(true);
        }
        this.playerMap = new HashMap<>();
        this.workerMap = new HashMap<>();
        this.worker1 = new Worker(WORKERID_1, player1);
        this.worker2 = new Worker(WORKERID_2, player1);
        this.worker3 = new Worker(WORKERID_3, player2);
        this.worker4 = new Worker(WORKERID_4, player2);
        this.player1.addWorker(this.worker1);
        this.player1.addWorker(this.worker2);
        this.player2.addWorker(this.worker3);
        this.player2.addWorker(this.worker4);

        this.playerMap.put(PLAYERID_1, this.player1);
        this.playerMap.put(PLAYERID_2, this.player2);
        this.workerMap.put(WORKERID_1, this.worker1);
        this.workerMap.put(WORKERID_2, this.worker2);
        this.workerMap.put(WORKERID_3, this.worker3);
        this.workerMap.put(WORKERID_4, this.worker4);
        gameStatus = GameStatus.PLACE;
    }

    private Player getAnotherPlayer(Player player) {
        return (player == this.player1) ? this.player2 : this.player1;
    }

    private void updateOperableGridIds(Player player) {
        GridsFilter filter;
        switch (gameStatus) {
        case PLACE:
            filter = new PlaceFilter();
            break;
        case SELECT:
            filter = new SelectFilter();
            break;
        case MOVE:
            filter = new MoveFilter();
            break;
        case MOVE_OR_SKIP:
            filter = new MoveFilter();
            break;
        case BUILD:
            filter = new BuildFilter();
            break;
        case BUILD_OR_SKIP:
            filter = new BuildFilter();
            break;
        default:
            filter = new DefaultFilter();
        }
        // Delegate Board to find the operable grids
        this.operableGridIds = board.getOperableGrids(filter, player);
    }

    /**
     * @breif Verifies whether playerId is valid, and whether player is in the right
     *        round of the game. If no problem, delegates the board to acutally
     *        place the worker into corresponding grid.
     * 
     * @param playerId
     * @param positionX
     * @param positionY
     * @return true if a worker is succesfully placed
     */
    public boolean placeWorker(int playerId, int positionX, int positionY) {
        // Controller parameter verfication
        if (!this.playerMap.containsKey(playerId)) {
            return false;
        }

        Player player = this.playerMap.get(playerId);
        // Game Logic check(GLC): correct turn of player
        if (!player.getIsActive()) {
            return false;
        }
        Worker worker = player.getUnsettledWorker();
        if (worker == null) {
            return false;
        }
        // Board Check and Update
        if (!board.placeWorker(worker, positionX, positionY)) {
            return false;
        }
        // State change
        if (player.getUnsettledWorker() == null) {
            player.setIsActive(false);
            getAnotherPlayer(player).setIsActive(true);
            if (getAnotherPlayer(player).getUnsettledWorker() == null) {
                gameStatus = GameStatus.SELECT;
                updateOperableGridIds(getAnotherPlayer(player));
                return true;
            }
        }
        updateOperableGridIds(player);
        return true;
    }

    /**
     * This function sets a worker to active status and updates operable grids
     * 
     * @param workerId
     * @param playerId
     * @param posX
     * @param posY
     * @return false if the worker does not belong to the player
     */
    public boolean select(int workerId, int playerId, int posX, int posY) {
        // Controller parameter verfication: wrong worker and player IDs
        if (!this.workerMap.containsKey(workerId) || !this.playerMap.containsKey(playerId)) {
            return false;
        }
        Worker worker = this.workerMap.get(workerId);
        Player player = this.playerMap.get(playerId);
        worker.setActive(true);
        // GLC: player manipulates right worker
        if (!player.hasRightWorker(worker)) {
            return false;
        }
        gameStatus = GameStatus.MOVE;
        updateOperableGridIds(player);
        return true;
    }

    /**
     * This function restores a worker to inactive status and updates operable grids
     * 
     * @param playerId
     * @param posX
     * @param posY
     * @return false if the worker does not belong to the player
     */
    public boolean unSelect(int playerId, int posX, int posY) {
        // Controller parameter verfication: wrong worker and player IDs
        if (!this.playerMap.containsKey(playerId)) {
            return false;
        }
        Player player = this.playerMap.get(playerId);
        player.getActiveWorker().setActive(false);
        gameStatus = GameStatus.SELECT;
        updateOperableGridIds(player);
        return true;
    }

    /**
     * Checks the validity of player ID and whether the player is in the right
     * round. If they are valid, delegate board to further move the worker.
     * 
     * @param playerId
     * @param posX
     * @param posY
     * @return true if successfully move a worker
     */
    public boolean move(int playerId, int posX, int posY) {
        // Controller parameter verfication
        if (!this.playerMap.containsKey(playerId)) {
            return false;
        }
        Player player = this.playerMap.get(playerId);

        // before move check
        if (!player.beforeMove(this.board, posX, posY)) {
            return false;
        }

        player.move(this.board, posX, posY);

        /**
         * after move status update: if false, this player still can move or build; if
         * true, this player has finished moving, updates Game to BUILD status
         */
        if (!player.afterMove(this.board, posX, posY)) {
            gameStatus = GameStatus.MOVE_OR_SKIP;
        } else {
            gameStatus = GameStatus.BUILD;
        }

        updateOperableGridIds(player);

        return true;
    }

    /**
     * Checks the validity of player ID and whether the player is in the right
     * round. If they are valid, delegate board to further build the tower.
     * 
     * @param playerId
     * @param posX
     * @param posY
     * @param buildDome equals to 1 if this is a buildDome operation
     * @return true if a block or a dome is successfully built at the position given
     */
    public boolean build(int playerId, int posX, int posY, int buildDome) {
        // Controller parameter verfication
        if (!this.playerMap.containsKey(playerId)) {
            return false;
        }
        Player player = this.playerMap.get(playerId);

        if (!player.beforeBuild(this.board, posX, posY, buildDome)) {
            return false;
        }

        /**
         * after build status update: if false, this player still can build or skip this
         * round optionally; if true, this player has finished building, updates Game to
         * SELECT status.
         */
        player.build(this.board, posX, posY, buildDome);

        if (!player.afterBuild(this.board, posX, posY)) {
            gameStatus = GameStatus.BUILD_OR_SKIP;
            updateOperableGridIds(player);
        } else {
            gameStatus = GameStatus.SELECT;
            updateOperableGridIds(getAnotherPlayer(player));
        }

        return true;
    }

    /**
     * Updates the GAME status to SELECT, namely, skips the MOVE_OR_SKIP or
     * BUILD_OR_SKIP round.
     * 
     * @param playerId
     * @param posX
     * @param posY
     * @return true if skip this turn succesfully
     */
    public boolean skip(int playerId, int posX, int posY) {
        // Controller parameter verfication
        if (!this.playerMap.containsKey(playerId)) {
            return false;
        }
        Player player = this.playerMap.get(playerId);

        if (gameStatus == GameStatus.MOVE_OR_SKIP) {
            player.afterMove(board, posX, posY);
            gameStatus = GameStatus.BUILD;
            updateOperableGridIds(player);
        }

        if (gameStatus == GameStatus.BUILD_OR_SKIP) {
            player.afterBuild(this.board, posX, posY);
            gameStatus = GameStatus.SELECT;
            updateOperableGridIds(getAnotherPlayer(player));
        }
        return true;
    }

}
