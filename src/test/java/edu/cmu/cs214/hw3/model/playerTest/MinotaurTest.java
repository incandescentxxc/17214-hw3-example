package edu.cmu.cs214.hw3.model.playerTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.player.Minotaur;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class MinotaurTest {
    private Player player;
    private Player otherPlayer;
    private Worker worker1;
    private Worker worker2;
    private Board board;

    @Before
    public void setUp() {
        player = new Minotaur(0, "player1");
        otherPlayer = new Player(1, "player2");
        worker1 = new Worker(0, this.player);
        worker2 = new Worker(1, this.otherPlayer);
        player.addWorker(worker1);
        player.addWorker(worker2);
        board = new Board();
    }

    @Test
    public void isMovableTest() {
        board.getGrid(0, 1).addWorker(worker2);
        board.getGrid(0, 0).addWorker(worker1);
        worker2.setGrid(board.getGrid(0, 1));
        worker1.setGrid(board.getGrid(0, 0));
        assertTrue(player.isMovable(board, board.getGrid(0, 0), board.getGrid(0, 1)));
    }

    @Test
    public void moveTest() {
        board.getGrid(0, 1).addWorker(worker2);
        board.getGrid(0, 0).addWorker(worker1);
        worker2.setGrid(board.getGrid(0, 1));
        worker1.setGrid(board.getGrid(0, 0));
        worker1.setActive(true);
        player.move(board, 0, 1);
        assertEquals(board.getGrid(0, 1).getWorker(), worker1);
        assertEquals(board.getGrid(0, 2).getWorker(), worker2);
    }
}
