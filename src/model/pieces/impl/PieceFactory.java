package model.pieces.impl;

import model.enumerations.PieceEnum;
import model.enumerations.ColorChessboardEnum;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;

public class PieceFactory {
    
    public Piece getPiece(PieceEnum type, Position position, ColorChessboardEnum color, Chessboard chessboard, char pieceSign){
        Piece retval = null;
        switch(type){
            case BISHOP -> retval = new Bishop(position, color, chessboard, pieceSign);
            case KING -> retval = new King(position, color, chessboard, pieceSign);
            case KNIGHT -> retval = new Knight(position, color, chessboard, pieceSign);
            case PAWN -> retval = new Pawn(position, color, chessboard, pieceSign);
            case QUEEN -> retval = new Queen(position, color, chessboard, pieceSign);
            case ROOK -> retval = new Rook(position, color, chessboard, pieceSign);     
        }
        return retval;
    }
}
