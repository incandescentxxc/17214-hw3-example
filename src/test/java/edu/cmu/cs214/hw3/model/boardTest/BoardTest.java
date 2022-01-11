package edu.cmu.cs214.hw3.model.boardTest;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BoardTest {
    private Board board;
    private Player player1;
    private Player player2;
    private Worker worker1;
    private Worker worker2;
    private Worker worker3;
    private Worker worker4;
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int PLAYERID_1 = 0;
    private static final int PLAYERID_2 = 1;
    private static final int WORKERID_1 = 0;
    private static final int WORKERID_2 = 1;
    private static final int WORKERID_3 = 2;
    private static final int WORKERID_4 = 3;

    @Before
    public void setUp() {
        player1 = new Player(PLAYERID_1, "player1");
        player2 = new Player(PLAYERID_2, "player2");
        this.worker1 = new Worker(WORKERID_1, player1);
        this.worker2 = new Worker(WORKERID_2, player1);
        this.worker3 = new Worker(WORKERID_3, player2);
        this.worker4 = new Worker(WORKERID_4, player2);
        player1.addWorker(worker1);
        player1.addWorker(worker2);
        player2.addWorker(worker3);
        player2.addWorker(worker4);
        board = new Board();
    }

    @Test
    public void testPlaceWorkerWithNonExistPos() {
        // position invalid
        assertFalse(board.placeWorker(worker1, 5, 1));
    }

    @Test
    public void testPlaceWorkerWithOccupiedPos() {
        // position occupied
        assertTrue(board.placeWorker(worker1, 1, 1));
        assertFalse(board.placeWorker(worker1, 1, 1));
    }

    @Test
    public void testCompletePlaceWorker() {
        assertTrue(board.placeWorker(worker1, 1, 1));
        assertTrue(board.placeWorker(worker2, 2, 1));
        assertTrue(board.placeWorker(worker3, 1, 4));
        assertTrue(board.placeWorker(worker4, 1, 3));
        // worker1 : (1,1); worker2: (2,1); worker3: (1,4); worker4(1,2)
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == 1 && j == 1) {
                    assertEquals(worker1, board.getGrid(i, j).getWorker());
                } else if (i == 2 && j == 1) {
                    assertEquals(worker2, board.getGrid(i, j).getWorker());
                } else if (i == 1 && j == 4) {
                    assertEquals(worker3, board.getGrid(i, j).getWorker());
                } else if (i == 1 && j == 3) {
                    assertEquals(worker4, board.getGrid(i, j).getWorker());
                } else {
                    assertNull(board.getGrid(i, j).getWorker());
                }
            }
        }
    }

}
