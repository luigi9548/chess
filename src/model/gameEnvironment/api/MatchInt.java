package model.gameEnvironment.api;

import model.functionality.impl.Position;
import model.pieces.impl.Piece;

public interface MatchInt {
    
    /**
    * Calculates the string representation of a move for use in the move history.
    *
    * @param isEnPassant True if the move is an en passant capture, false otherwise.
    * @param p           The piece making the move.
    * @param row         The row of the destination position.
    * @param col         The column of the destination position.
    * @return            A string representing the move in standard algebraic notation.
    */
    String calculateHistory(boolean isEnPassant, Piece p, int row, int col);

    /**
    * Handles a player's turn, including updating the en passant status, configuring pawns,
    * recording move history, and handling captures and promotions.
    *
    * @param p    The piece being moved.
    * @param row  The target row for the piece's move.
    * @param col  The target column for the piece's move.
    * @return     The en passant position if en passant occurred, otherwise null.
    */
    Position turnHandler(Piece p, int row, int col);
    
    /**
     * Handles the check condition and updates the move history with a '+' symbol if the current player's king is in check.
     */
    void checkHandler();
    
}
