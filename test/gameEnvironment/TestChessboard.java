/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameEnvironment;
import java.util.ArrayList;
import model.gameEnvironment.Chessboard;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;
import static org.junit.Assert.*;

public class TestChessboard {
    private final Chessboard chessboard = new Chessboard();
    
    @org.junit.Test
    public void testInitializeChessboard(){
        // rook
        assertTrue(this.chessboard.getSquare(0, 0).getPiece().get() instanceof Rook);
        assertTrue(this.chessboard.getSquare(0, 7).getPiece().get() instanceof Rook);
        assertTrue(this.chessboard.getSquare(7, 0).getPiece().get() instanceof Rook);
        assertTrue(this.chessboard.getSquare(7, 7).getPiece().get() instanceof Rook);
        
        // knight
        assertTrue(this.chessboard.getSquare(0, 1).getPiece().get() instanceof Knight);
        assertTrue(this.chessboard.getSquare(0, 6).getPiece().get() instanceof Knight);
        assertTrue(this.chessboard.getSquare(7, 1).getPiece().get() instanceof Knight);
        assertTrue(this.chessboard.getSquare(7, 6).getPiece().get() instanceof Knight);
        
        // bishop
        assertTrue(this.chessboard.getSquare(0, 2).getPiece().get() instanceof Bishop);
        assertTrue(this.chessboard.getSquare(0, 5).getPiece().get() instanceof Bishop);
        assertTrue(this.chessboard.getSquare(7, 2).getPiece().get() instanceof Bishop);
        assertTrue(this.chessboard.getSquare(7, 5).getPiece().get() instanceof Bishop);
        
        // queen
        assertTrue(this.chessboard.getSquare(0, 3).getPiece().get() instanceof Queen);
        assertTrue(this.chessboard.getSquare(7, 3).getPiece().get() instanceof Queen);
        
        // king
        assertTrue(this.chessboard.getSquare(0, 4).getPiece().get() instanceof King);
        assertTrue(this.chessboard.getSquare(7, 4).getPiece().get() instanceof King);
        
        // pawn
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            assertTrue(this.chessboard.getSquare(1, i).getPiece().get() instanceof Pawn);
            assertTrue(this.chessboard.getSquare(6, i).getPiece().get() instanceof Pawn);
        }
        
        // void squares
        for(int i = 2; i <= Chessboard.ROW_UPPER_LIMIT -2; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                assertTrue(this.chessboard.getSquare(i, j).getPiece().isEmpty());
            }
        }
    }
    
    @org.junit.Test
     public void testGetWPieces(){
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces = this.chessboard.getWPieces();
        assertTrue(pieces.size() == 16);
        for(Piece piece : pieces)
            assertTrue(piece.getColor() == 0);
    }
     
    @org.junit.Test
    public void testGetBPieces(){
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces = this.chessboard.getBPieces();
        assertTrue(pieces.size() == 16);
        for(Piece piece : pieces)
            assertTrue(piece.getColor() == 1);
    }
}
