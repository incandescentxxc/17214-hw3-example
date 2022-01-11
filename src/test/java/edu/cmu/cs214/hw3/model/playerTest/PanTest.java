package edu.cmu.cs214.hw3.model.playerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.player.Pan;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class PanTest {
    private Player player;
    private Player otherPlayer;
    private Worker worker1;
    private Worker worker2;
    private Board board;

    @Before
    public void setUp() {
        player = new Pan(0, "player1");
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
    public void downTwoLevelTest() {
        worker1.setActive(true);
        board.getGrid(0, 1).addWorker(worker1);
        worker1.setGrid(board.getGrid(0, 1));
        board.getGrid(0, 1).buildTower();
        board.getGrid(0, 1).buildTower();
        assertEquals(2, board.getGrid(0, 1).getTower().getLevel());
        player.move(board, 0, 0);
        player.afterMove(board, 0, 0);
        assertTrue(player.getHasWon());
    }
}
