package gameEnvironment;

import java.util.ArrayList;
import model.enumerations.ColorChessboardEnum;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Bishop;
import model.pieces.impl.King;
import model.pieces.impl.Knight;
import model.pieces.impl.Pawn;
import model.pieces.impl.Piece;
import model.pieces.impl.Queen;
import model.pieces.impl.Rook;
import static org.junit.Assert.*;
import org.junit.Test;

public class ChessboardTest {
   // private Chessboard chessboard;
    
    @Test
    public void testInitializeChessboard(){
        Chessboard chessboard = Chessboard.getIstance();
 
        // rook
        assertTrue(chessboard.getSquare(0, 0).getPiece().get() instanceof Rook);
        assertTrue(chessboard.getSquare(0, 7).getPiece().get() instanceof Rook);
        assertTrue(chessboard.getSquare(7, 0).getPiece().get() instanceof Rook);
        assertTrue(chessboard.getSquare(7, 7).getPiece().get() instanceof Rook);
        
        // knight
        assertTrue(chessboard.getSquare(0, 1).getPiece().get() instanceof Knight);
        assertTrue(chessboard.getSquare(0, 6).getPiece().get() instanceof Knight);
        assertTrue(chessboard.getSquare(7, 1).getPiece().get() instanceof Knight);
        assertTrue(chessboard.getSquare(7, 6).getPiece().get() instanceof Knight);
        
        // bishop
        assertTrue(chessboard.getSquare(0, 2).getPiece().get() instanceof Bishop);
        assertTrue(chessboard.getSquare(0, 5).getPiece().get() instanceof Bishop);
        assertTrue(chessboard.getSquare(7, 2).getPiece().get() instanceof Bishop);
        assertTrue(chessboard.getSquare(7, 5).getPiece().get() instanceof Bishop);
        
        // queen
        assertTrue(chessboard.getSquare(0, 4).getPiece().get() instanceof Queen);
        assertTrue(chessboard.getSquare(7, 4).getPiece().get() instanceof Queen);
        
        // king
        assertTrue(chessboard.getSquare(0, 3).getPiece().get() instanceof King);
        assertTrue(chessboard.getSquare(7, 3).getPiece().get() instanceof King);
        
        // pawn
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            assertTrue(chessboard.getSquare(1, i).getPiece().get() instanceof Pawn);
            assertTrue(chessboard.getSquare(6, i).getPiece().get() instanceof Pawn);
        }
        
        // void squares
        for(int i = 2; i <= Chessboard.ROW_UPPER_LIMIT -2; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                assertTrue(chessboard.getSquare(i, j).getPiece().isEmpty());
            }
        }
    }
    
    @Test
    public void testGetWPieces(){
        Chessboard chessboard = Chessboard.getIstance();
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces = chessboard.getPiecesByColor(ColorChessboardEnum.WHITE);
        assertTrue(pieces.size() == 16);
        for(Piece piece : pieces)
            assertTrue(piece.getColor() == ColorChessboardEnum.WHITE);
    }
     
    @Test
    public void testGetBPieces(){
        Chessboard chessboard = Chessboard.getIstance();
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces = chessboard.getPiecesByColor(ColorChessboardEnum.BLACK);
        assertTrue(pieces.size() == 16);
        for(Piece piece : pieces)
            assertTrue(piece.getColor() == ColorChessboardEnum.BLACK);
    }
    
    @Test
    public void testValidPosition() {
        Chessboard chessboard = Chessboard.getIstance();
        assertTrue(chessboard.isValidPosition(new Position(2, 3))); // Test a valid position
        assertTrue(chessboard.isValidPosition(new Position(0, 0))); // Test a valid position at the lower limit
        assertTrue(chessboard.isValidPosition(new Position(7, 7))); // Test a valid position at the upper limit
    }

    @Test
    public void testInvalidPosition() {
        Chessboard chessboard = Chessboard.getIstance();
        assertFalse(chessboard.isValidPosition(new Position(-1, 3))); // Test an invalid row
        assertFalse(chessboard.isValidPosition(new Position(2, -1))); // Test an invalid column
        assertFalse(chessboard.isValidPosition(new Position(8, 3))); // Test an invalid row beyond the upper limit
        assertFalse(chessboard.isValidPosition(new Position(2, 8))); // Test an invalid column beyond the upper limit
    }
    
    @Test
    public void testCanCastlingValid() {
        Position kingPosition = new Position(0, 3);
        Position rookPosition = new Position(0, 0);
        Chessboard chessboard = Chessboard.getIstanceForTest();

        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'k'));
        chessboard.getSquare(rookPosition.getRow(), rookPosition.getCol()).setPiece(new Rook(rookPosition, ColorChessboardEnum.WHITE, chessboard, 'r'));


        Position castlingPosition = chessboard.canCastling(ColorChessboardEnum.WHITE);

        assertNotNull(castlingPosition);
    }
    
    @Test
    public void testCanCastlingInvalid() {
        Position kingPosition = new Position(0, 3);
        Position rookPosition = new Position(0, 0);
        Position avvRookPosition = new Position(5,2);
        Chessboard chessboard = Chessboard.getIstanceForTest();

        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'k'));
        chessboard.getSquare(rookPosition.getRow(), rookPosition.getCol()).setPiece(new Rook(rookPosition, ColorChessboardEnum.WHITE, chessboard, 'r'));
        chessboard.getSquare(avvRookPosition.getRow(), avvRookPosition.getCol()).setPiece(new Rook(avvRookPosition, ColorChessboardEnum.BLACK, chessboard, 'R'));


        Position castlingPosition = chessboard.canCastling(ColorChessboardEnum.WHITE);
        assertNull(castlingPosition);
    }
    
    @Test
    public void testEnPassantValid() {
        Position pawnPosition = new Position(3,2);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        Pawn pawn = new Pawn(pawnPosition, ColorChessboardEnum.WHITE, chessboard, 'p');
        
        chessboard.getSquare(pawnPosition.getRow(), pawnPosition.getCol()).setPiece(pawn);

        Position enemyPawnPosition = new Position(3,1);
        Pawn enemyPawn = new Pawn(enemyPawnPosition, ColorChessboardEnum.BLACK, chessboard, 'P');
        enemyPawn.setEnPassant(true);
        chessboard.getSquare(enemyPawnPosition.getRow(), enemyPawnPosition.getCol()).setPiece(enemyPawn);

        Position enPassantPosition = chessboard.enPassant(pawn);

        assertNotNull(enPassantPosition);
        assertEquals(new Position(3, 1), enPassantPosition);
    }
    
    @Test
    public void testEnPassantInvalid() {
        Position pawnPosition = new Position(3,2);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        Pawn pawn = new Pawn(pawnPosition, ColorChessboardEnum.WHITE, chessboard, 'p');
        
        chessboard.getSquare(pawnPosition.getRow(), pawnPosition.getCol()).setPiece(pawn);

        Position enemyPawnPosition = new Position(4,1);
        Pawn enemyPawn = new Pawn(enemyPawnPosition, ColorChessboardEnum.BLACK, chessboard, 'P');
        enemyPawn.setEnPassant(true);
        chessboard.getSquare(enemyPawnPosition.getRow(), enemyPawnPosition.getCol()).setPiece(enemyPawn);

        Position enPassantPosition = chessboard.enPassant(pawn);

        assertNull(enPassantPosition);
    }
    
    @Test 
    public void testPromotionValid(){
        Position pawnPosition = new Position(7,2);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        Pawn pawn = new Pawn(pawnPosition, ColorChessboardEnum.WHITE, chessboard, 'p');
        chessboard.getSquare(pawnPosition.getRow(), pawnPosition.getCol()).setPiece(pawn);
        assertTrue(chessboard.promotion(pawn));
    }
    
    @Test 
    public void testPromotionInvalid(){
        Position pawnPosition = new Position(5,2);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        Pawn pawn = new Pawn(pawnPosition, ColorChessboardEnum.WHITE, chessboard, 'p');
        chessboard.getSquare(pawnPosition.getRow(), pawnPosition.getCol()).setPiece(pawn);
        assertFalse(chessboard.promotion(pawn));
    }
    
    @Test
    public void testIsCheck(){
        Position kingPosition = new Position(0,2);
        Position avvRookPosition = new Position(3, 2);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'p'));
        chessboard.getSquare(avvRookPosition.getRow(), avvRookPosition.getCol()).setPiece(new Rook(avvRookPosition, ColorChessboardEnum.BLACK, chessboard, 'R'));
        
        assertTrue(chessboard.isCheck(ColorChessboardEnum.WHITE));
    }
    
    @Test
    public void testIsNotCheck(){
        Position kingPosition = new Position(0,2);
        Position avvRookPosition = new Position(3, 1);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'p'));
        chessboard.getSquare(avvRookPosition.getRow(), avvRookPosition.getCol()).setPiece(new Rook(avvRookPosition, ColorChessboardEnum.BLACK, chessboard, 'R'));
        
        assertFalse(chessboard.isCheck(ColorChessboardEnum.WHITE));
    }
    
    @Test 
    public void testLegalMovements(){
        Position kingPosition = new Position(0,0);
        Position avvBishopPosition = new Position(7, 7);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        King king = new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'p');
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(king);
        chessboard.getSquare(avvBishopPosition.getRow(), avvBishopPosition.getCol()).setPiece(new Bishop(avvBishopPosition, ColorChessboardEnum.BLACK, chessboard, 'R')); 
        
        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(0, 1));
        expectedPositions.add(new Position(1, 0));
        
        ArrayList<Position> calculatedPositions = chessboard.legalMovements(king);
        
        assertEquals(expectedPositions.size(), calculatedPositions.size());
        
        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }
    
    @Test
    public void testIsFlap(){
        Position kingPosition = new Position(0,3);
        Position avvQueenPosition = new Position(2, 2);
        Position AvvRookPosition = new Position(2,4);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'k'));
        chessboard.getSquare(avvQueenPosition.getRow(), avvQueenPosition.getCol()).setPiece(new Queen(avvQueenPosition, ColorChessboardEnum.BLACK, chessboard, 'Q'));
        chessboard.getSquare(AvvRookPosition.getRow(), AvvRookPosition.getCol()).setPiece(new Rook(AvvRookPosition, ColorChessboardEnum.BLACK, chessboard, 'R'));
        
        assertEquals(1, chessboard.isCheckmateOrFlap(ColorChessboardEnum.WHITE)); 
    }
    
    @Test
    public void testIsNotFlapOrCheckmate(){
        Position kingPosition = new Position(0,3);
        Position avvQueenPosition = new Position(2, 2);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'k'));
        chessboard.getSquare(avvQueenPosition.getRow(), avvQueenPosition.getCol()).setPiece(new Queen(avvQueenPosition, ColorChessboardEnum.BLACK, chessboard, 'Q'));
        
        assertEquals(2, chessboard.isCheckmateOrFlap(ColorChessboardEnum.WHITE)); 
    }
    
    @Test
    public void testIsCheckmate(){
        Position kingPosition = new Position(0,4);
        Position AvvRook1Position = new Position(0,0);
        Position AvvRook2Position = new Position(1,1);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'k'));
        chessboard.getSquare(AvvRook1Position.getRow(), AvvRook1Position.getCol()).setPiece(new Rook(AvvRook1Position, ColorChessboardEnum.BLACK, chessboard, 'R'));
        chessboard.getSquare(AvvRook2Position.getRow(), AvvRook2Position.getCol()).setPiece(new Rook(AvvRook2Position, ColorChessboardEnum.BLACK, chessboard, 'R'));
        
        assertEquals(0, chessboard.isCheckmateOrFlap(ColorChessboardEnum.WHITE)); 
    }
    
    @Test
    public void testUpdatePosition(){
        Position kingPosition = new Position(0,4);
        Chessboard chessboard = Chessboard.getIstanceForTest();
        King king = new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'k');
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(king);
        chessboard.updatePosition(kingPosition.getRow(), kingPosition.getCol(), 3, 5);
        assertEquals(new Position(3,5), king.getPosition());
    }
    
    @Test
    public void testChangeEnPassant(){
        Chessboard chessboard = Chessboard.getIstance();
        Pawn p = (Pawn) chessboard.getSquare(1, 2).getPiece().get();
        p.setEnPassant(true);
        chessboard.changeEnPassant(ColorChessboardEnum.WHITE);
        assertFalse(p.getEnPassant());
    }
}

