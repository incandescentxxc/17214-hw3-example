package edu.cmu.cs214.hw3.model.player;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class Atlas extends Player {
    public Atlas(int id, String name) {
        super(id, name);
        this.setPower("Atlas");
    }

    @Override
    public boolean beforeBuild(Board board, int posX, int posY, int buildDome) {
        Worker worker = super.getActiveWorker();

        // check round restriction
        if (!super.getIsActive() || !worker.getIsMoved()) {
            return false;
        }
        // check other players' restriction
        if (!super.getOpponent().agreeOtherBuild(board, worker, posX, posY)) {
            return false;
        }

        // check self restriction
        Grid origin = worker.getGridOccupied();
        Grid dest = board.getGrid(posX, posY);
        return this.isBuildable(board, origin, dest);
    }
}
