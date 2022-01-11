package edu.cmu.cs214.hw3.model.board.filterStrategies;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public class SelectFilter implements GridsFilter {
    public boolean isThisGridOperable(Board board, Grid curGrid, Player player) {
        return curGrid.getWorker() != null && curGrid.getWorker().getPlayerOwned() == player;
    }
}
