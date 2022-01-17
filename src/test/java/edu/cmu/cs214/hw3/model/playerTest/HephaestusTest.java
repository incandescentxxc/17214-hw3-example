package edu.cmu.cs214.hw3.model.playerTest;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.player.Hephaestus;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class HephaestusTest {
    private Player player;
    private Player otherPlayer;
    private Worker worker1;
    private Worker worker2;
    private Board board;

    @Before
    public void setUp() {
        player = new Hephaestus(0, "player1");
        otherPlayer = new Player(1, "player2");
        worker1 = new Worker(0, this.player);
        worker2 = new Worker(1, this.otherPlayer);
        player.addWorker(worker1);
        player.addWorker(worker2);
        player.setOpponent(otherPlayer);
        board = new Board();
    }

    @Test
    public void afterBuildTest() {
        board.getGrid(0, 0).addWorker(worker1);
        worker1.setGrid(board.getGrid(0, 0));
        worker1.setIsMoved(true);
        worker1.setActive(true);
        assertEquals(false, player.afterBuild(board, 0, 1));
        assertEquals(true, player.afterBuild(board, 1, 0));
    }
}
