package model.gameEnvironment.api;

import model.functionality.impl.Position;
import model.pieces.impl.Piece;

public interface MatchInt {

    /**
    * Handles a player's turn, including updating the en passant status, configuring pawns,
    * recording move history, and handling captures and promotions.
    *
    * @param p    The piece being moved.
    * @param row  The target row for the piece's move.
    * @param col  The target column for the piece's move.
    * @return     The en passant position if en passant occurred, otherwise null.
    */
    public Position turnHandler(Piece p, int row, int col);
    
    /**
     * Handles the check condition and updates the move history with a '+' symbol if the current player's king is in check.
     */
    public void checkHandler();
    
}
