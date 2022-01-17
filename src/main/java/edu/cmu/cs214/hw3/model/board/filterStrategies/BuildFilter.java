package edu.cmu.cs214.hw3.model.board.filterStrategies;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;

public class BuildFilter implements GridsFilter {
    public boolean isThisGridOperable(Board board, Grid curGrid, Player player) {
        return player.beforeBuild(board, curGrid.getPositionX(), curGrid.getPositionY(), 0);
    }
}
