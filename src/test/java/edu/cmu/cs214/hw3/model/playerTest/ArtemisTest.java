package edu.cmu.cs214.hw3.model.playerTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.player.Artemis;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class ArtemisTest {
    private Player player;
    private Player otherPlayer;
    private Worker worker1;
    private Worker worker2;
    private Board board;

    @Before
    public void setUp() {
        player = new Artemis(0, "player1");
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
    public void moveTwiceTest() {
        board.getGrid(0, 0).addWorker(worker1);
        board.getGrid(0, 1).buildTower();
        worker1.setGrid(board.getGrid(0, 0));
        worker1.setActive(true);
        player.move(board, 0, 1);
        assertFalse(player.afterMove(board, 0, 1));
        player.move(board, 0, 2);
        assertTrue(player.afterMove(board, 0, 2));
    }
}
