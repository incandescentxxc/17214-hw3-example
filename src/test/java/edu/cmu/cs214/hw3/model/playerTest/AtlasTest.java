package edu.cmu.cs214.hw3.model.playerTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Athena;
import edu.cmu.cs214.hw3.model.player.Atlas;
import edu.cmu.cs214.hw3.model.player.Hephaestus;
import edu.cmu.cs214.hw3.model.player.Minotaur;
import edu.cmu.cs214.hw3.model.player.Pan;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class AtlasTest {
    private Player player;
    private Player otherPlayer;
    private Worker worker1;
    private Worker worker2;
    private Board board;

    @Before
    public void setUp() {
        player = new Atlas(0, "player1");
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
    public void testBuildAnyDome() {
        worker1.setActive(true);
        worker1.setGrid(board.getGrid(0, 1));
        board.getGrid(0, 1).addWorker(worker1);
        player.build(board, 0, 0, 1);
        assertEquals(true, board.getGrid(0, 0).getTower().hasDome());
    }
}
