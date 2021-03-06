package edu.cmu.cs214.hw3.model.board.filterStrategies;

import edu.cmu.cs214.hw3.model.board.Board;
import edu.cmu.cs214.hw3.model.board.Grid;
import edu.cmu.cs214.hw3.model.player.Player;
public class MoveFilter implements GridsFilter {
    public boolean isThisGridOperable(Board board, Grid curGrid, Player player) {
        return player.beforeMove(board, curGrid.getPositionX(), curGrid.getPositionY());
    }
}
