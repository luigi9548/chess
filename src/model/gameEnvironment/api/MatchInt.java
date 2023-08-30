package model.gameEnvironment.api;

import model.pieces.impl.Piece;

public interface MatchInt {

    // metodo utilizzato per calcolare la stringa da utilizzare nella cronologia delle mosse
    String calculateHistory(boolean isEnPassant, Piece p, int row, int col);
    
}
