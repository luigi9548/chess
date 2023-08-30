package model.gameEnvironment.api;

import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Square;
import model.pieces.impl.Pawn;
import model.pieces.impl.Piece;

public interface ChessboardInt {

    // metodo per verificare se può avvenire arrocco
    // (ritorna la posizione della torre con cui può fare arrocco altrimenti null)
    Position canCastling(ColorChessboard color);

    // metodo pe reseguire enPassant (ritorna la casella dove esegue en Passant altrimenti null)
    Position enPassant(Pawn p);

    // metodo che restituisce una determinata casella nella scacchiera
    Square getSquare(int row, int col);

    // metodo per verificare se è scacco 
    boolean isCheck(ColorChessboard color);

    // metodo per verificare se è patta o scacco matto (Scacco matto = 0, patta = 1, niente = 2;
    int isCheckmateOrFlap(ColorChessboard color);

    // metodo per verificare se la posizione è contenuta nella scacchiera
    boolean isValidPosition(Position p);

    // metodo per filtrare i movimenti legali del pezzo passato come parametro
    // (in caso di scacco la posizione non può essere valida)
    ArrayList<Position> legalMovements(Piece p);

    // metodo per verficare se il pedone è arrivato in fondo per effettuare la promozione
    boolean promotion(Pawn p);

    // metodo per cambiare turno
    void switchTurn();
    
}
