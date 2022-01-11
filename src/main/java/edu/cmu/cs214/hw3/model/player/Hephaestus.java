package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;

/**
 * Player with God Power Hephasetus
 * 
 * Capacity: Your Worker may build one additional block (not dome) on top of
 * your first block.
 */
public class Hephaestus extends Player {
    private int[] buildPos = { -1, -1 };

    public Hephaestus(int id, String name) {
        super(id, name);
        this.setPower("Hephaestus");

    }

    /**
     * checks whether a build is valid
     */
    @Override
    public boolean isBuildable(Board board, Grid origin, Grid dest) {
        if (buildPos[0] == -1) {
            return super.isBuildable(board, origin, dest);
        }
        if (dest.getPositionX() == buildPos[0] && dest.getPositionY() == buildPos[1] && dest.canBuildBlock()) {
            return true;
        }
        return false;
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
