package edu.cmu.cs214.hw3.model.playerTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Artemis;
import edu.cmu.cs214.hw3.model.player.Athena;
import edu.cmu.cs214.hw3.model.player.Demeter;
import edu.cmu.cs214.hw3.model.player.Hephaestus;
import edu.cmu.cs214.hw3.model.player.Minotaur;
import edu.cmu.cs214.hw3.model.player.Pan;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class DemeterTest {
    private Player player;
    private Player otherPlayer;
    private Worker worker1;
    private Worker worker2;
    private Board board;

    @Before
    public void setUp() {
        player = new Demeter(0, "player1");
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
    public void buildTwiceTest() {
        board.getGrid(0, 0).addWorker(worker1);
        board.getGrid(0, 1).buildTower();
        worker1.setGrid(board.getGrid(0, 0));
        worker1.setActive(true);
        assertFalse(player.afterBuild(board, 0, 1));
        assertTrue(player.afterBuild(board, 1, 1));
    }
}
