package edu.cmu.cs214.hw3.model.board.filterStrategies;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;
import edu.cmu.cs214.hw3.model.worker.Worker;

public interface GridsFilter {
    boolean isThisGridOperable(Board board, Grid curGrid, Player player);
}
