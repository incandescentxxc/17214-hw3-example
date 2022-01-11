package edu.cmu.cs214.hw3.model.playerTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Athena;
import edu.cmu.cs214.hw3.model.player.Hephaestus;
import edu.cmu.cs214.hw3.model.player.Minotaur;
import edu.cmu.cs214.hw3.model.player.Pan;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class AthenaTest {
    private Player player;
    private Player otherPlayer;
    private Worker worker1;
    private Worker worker2;
    private Board board;

    @Before
    public void setUp() {
        player = new Athena(0, "player1");
        otherPlayer = new Player(1, "player2");
        worker1 = new Worker(0, this.player);
        worker2 = new Worker(1, this.otherPlayer);
        player.addWorker(worker1);
        player.addWorker(worker2);
        player.setOpponent(otherPlayer);
        otherPlayer.setOpponent(player);
        board = new Board();
    }

    @Test
    public void testAgreeOtherMove() {
        board.getGrid(0, 0).addWorker(worker1);
        board.getGrid(0, 1).buildTower();
        worker1.setGrid(board.getGrid(0, 0));
        worker1.setActive(true);
        board.getGrid(2, 0).addWorker(worker2);
        board.getGrid(2, 1).buildTower();
        worker2.setGrid(board.getGrid(2, 0));
        player.move(board, 0, 1);
        assertFalse(player.agreeOtherMove(board, worker2, 2, 1));
    }
}
