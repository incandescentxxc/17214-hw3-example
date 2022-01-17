package edu.cmu.cs214.hw3.model.playerTest;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class playerTest {
    private Player player1;
    private Worker worker1;
    private Worker worker2;
    private Worker worker3;
    private Board board;

    @Before
    public void setUp() {
        player1 = new Player(0, "player1");
        worker1 = new Worker(0, this.player1);
        worker2 = new Worker(1, this.player1);
        worker3 = new Worker(2, this.player1);
        board = new Board();
    }

    @Test
    public void testAddWorkerBelowTwo() {
        assertTrue(player1.addWorker(worker1));
        assertTrue(player1.addWorker(worker3));
        assertEquals(worker1, player1.getWorkers().get(0));
        assertEquals(worker3, player1.getWorkers().get(1));
    }

    @Test
    public void tetsAddWorkerMorethanTwo() {
        assertTrue(player1.addWorker(worker1));
        assertTrue(player1.addWorker(worker3));
        assertFalse(player1.addWorker(worker2));
    }

    @Test
    public void testGetUnsettledWorker() {
        worker1.setGrid(new Grid(1, 1));
        player1.addWorker(worker1);
        player1.addWorker(worker2);
        assertEquals(worker2, player1.getUnsettledWorker());
    }

    @Test
    public void testIsMovableTrue() {
        Grid origin = board.getGrid(0, 0);
        Grid dest = board.getGrid(0, 1);
        origin.addWorker(worker1);
        worker1.setGrid(origin);
        assertTrue(player1.isMovable(board, origin, dest));
    }

    @Test
    public void testIsMovableFalse() {
        Grid origin = board.getGrid(2, 0);
        Grid dest = board.getGrid(0, 1);
        origin.addWorker(worker1);
        worker1.setGrid(origin);
        assertFalse(player1.isMovable(board, origin, dest));
    }

    @Test
    public void testIsBuildableTrue() {
        Grid origin = board.getGrid(0, 0);
        Grid dest = board.getGrid(0, 1);
        origin.addWorker(worker1);
        worker1.setGrid(origin);
        assertTrue(player1.isBuildable(board, origin, dest));
    }

    @Test
    public void testIsBuildableFalse() {
        Grid origin = board.getGrid(0, 0);
        Grid dest = board.getGrid(0, 2);
        origin.addWorker(worker1);
        worker1.setGrid(origin);
        assertFalse(player1.isMovable(board, origin, dest));
    }

    @Test
    public void testHasLostTrue() {
        Grid origin = board.getGrid(0, 0);
        board.getGrid(0, 1).buildDome();
        board.getGrid(1, 0).buildDome();
        board.getGrid(1, 1).buildDome();
        origin.addWorker(worker1);
        worker1.setGrid(origin);
        assertTrue(player1.hasLost(board));
    }
}
