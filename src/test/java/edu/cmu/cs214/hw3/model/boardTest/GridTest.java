package edu.cmu.cs214.hw3.model.boardTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class GridTest {
    private Grid grid;
    private Grid grid1;

    @Before
    public void setUp() {
        grid = new Grid(1, 2);
        grid1 = new Grid(1, 3);
    }

    @Test
    public void testGridAddWorker() {
        Worker worker1 = new Worker(0, new Player(0, "player1"));
        assertTrue(grid.addWorker(worker1));
        Worker worker2 = new Worker(1, new Player(1, "player2"));
        assertFalse(grid.addWorker(worker2));
    }

    @Test
    public void testGridBuildTowerWithNoBlock() {
        assertTrue(grid.canBuildTower());
        grid.buildTower();
    }

    @Test
    public void testGridBuildTower() {
        testGridBuildTowerWithNoBlock();
        assertTrue(grid.canBuildTower());
        assertTrue(grid.canBuildTower());
        assertTrue(grid.canBuildTower());// dome
        grid.buildTower();
        grid.buildTower();
        grid.buildTower();
        assertTrue(grid.getTower().hasDome());
        assertEquals(3, grid.getTower().getLevel());
    }

    @Test
    public void testGetWinningWorkerWithRealWin() {
        grid.buildTower();
        grid.buildTower();
        grid.buildTower();
        Worker worker1 = new Worker(0, new Player(0, "player1"));
        assertTrue(grid.addWorker(worker1));
        worker1.setGrid(grid);
        assertEquals(worker1, grid.getWiningWorker());
    }

    @Test
    public void testGetWinningWorkerWithNoWin() {
        grid.buildTower();
        grid.buildTower();
        Worker worker1 = new Worker(0, new Player(0, "player1"));
        assertTrue(grid.addWorker(worker1));
        worker1.setGrid(grid);
        assertNull(grid.getWiningWorker());
    }

    @Test
    public void testIsCorrectlyLowerThan() {
        grid.buildTower();
        grid1.buildTower();
        grid1.buildTower();
        assertTrue(grid.isLowerThan(grid1));
    }

    @Test
    public void testIsIncorrectlyLowerThan() {
        grid.buildTower();
        grid1.buildTower();
        assertFalse(grid.isLowerThan(grid1));
    }

}
