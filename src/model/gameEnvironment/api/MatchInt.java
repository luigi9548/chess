package model.gameEnvironment.api;

import model.pieces.impl.Piece;

public interface MatchInt {

    /**
     * Calculates the string representation of a move for use in the move history.
     *
     * @param isEnPassant True if the move is an en passant capture, false otherwise.
     * @param p           The piece making the move.
     * @param row         The row of the destination position.
     * @param col         The column of the destination position.
     * @return A string representing the move in standard algebraic notation.
     */
    String calculateHistory(boolean isEnPassant, Piece p, int row, int col);
    
}
