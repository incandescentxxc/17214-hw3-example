package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.worker.Worker;

/**
 * Player with God Power Demeter
 * 
 * Capacity: Your Worker may build one additional time, but not on the same
 * space.
 */
public class Demeter extends Player {

    private int[] buildPos = { -1, -1 };

    public Demeter(int id, String name) {
        super(id, name);
        this.setPower("Demeter");

    }

    private boolean isBuildPosOverlaped(Grid dest) {
        int[] pos = dest.getPosition();
        if (pos[0] == buildPos[0] && pos[1] == buildPos[1]) {
            return true;
        }
        return false;
    }

    /**
     * checks whether a build is valid
     */
    @Override
    public boolean isBuildable(Board board, Grid origin, Grid dest) {
        if (!super.isBuildable(board, origin, dest) || isBuildPosOverlaped(dest)) {
            return false;
        }
        return true;
    }

    /**
     * updates all build-related status
     */
    @Override
    public boolean afterBuild(Board board, int posX, int posY) {

        if (buildPos[0] == -1) {
            buildPos[0] = posX;
            buildPos[1] = posY;
            return false;
        } else {
            buildPos[0] = -1;
            buildPos[1] = -1;
            return super.afterBuild(board, posX, posY);
        }
    }

}
