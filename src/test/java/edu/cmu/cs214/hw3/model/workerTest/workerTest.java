package edu.cmu.cs214.hw3.model.workerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class workerTest {
    private Worker worker;
    private Player player;

    @Before
    public void setUp() {
        this.player = new Player(0, "player1");
        this.worker = new Worker(0, this.player);
    }

    @Test
    public void testSetAndGetGrid() {
        Grid grid = new Grid(1, 2);
        worker.setGrid(grid);
        assertEquals(grid, worker.getGridOccupied());
    }

    @Test
    public void testGetGridOccupiedWithNoGridSet() {
        assertNull(worker.getGridOccupied());
    }

    @Test
    public void testGetPlayerOwned() {
        assertEquals(player, worker.getPlayerOwned());
    }

}
