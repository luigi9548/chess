/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameEnvironment;

import model.functionality.impl.ChessTimer;
import model.enumerations.ColorChessboardEnum;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.gameEnvironment.impl.Match;
import model.gameEnvironment.impl.Player;
import model.pieces.impl.Bishop;
import model.pieces.impl.King;
import model.pieces.impl.Pawn;
import model.pieces.impl.Queen;
import model.pieces.impl.Rook;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Elena
 */
public class MatchTest {
    private Match match;
    private Chessboard chessboard;
    private Player playerW;
    private Player playerB;
    
    @Before
    public void setUp(){
        playerW = new Player(ColorChessboardEnum.WHITE);
        playerB = new Player(ColorChessboardEnum.BLACK);
        chessboard = Chessboard.getIstanceForTest();
        match = new Match(chessboard,playerW,playerB, new ChessTimer(600 * 1000));
    }
    
    @Test
    public void testCalcuteHistory(){
        
        // Test 1: Regular move for a non-pawn piece.
        Bishop bishop = new Bishop(new Position(0,2),ColorChessboardEnum.WHITE,chessboard,'b');
        String history1 = match.calculateHistory(false, bishop, 2, 4);
        assertEquals("Bc0 - e2", history1);
        
        // Test case 2: Capture move for a non-pawn piece
        Queen queen = new Queen(new Position(3,3),ColorChessboardEnum.WHITE,chessboard,'q');
        Bishop bishop2 = new Bishop(new Position(3, 5),ColorChessboardEnum.BLACK,chessboard,'B');
        chessboard.getSquare(bishop2.getPosition().getRow(), bishop2.getPosition().getCol()).setPiece(bishop2);
        String history2 = match.calculateHistory(false, queen, 3, 5);
        assertEquals("Qd3xf3",history2);
        
        // Test case 3: Regular move for a pawn
        Pawn pawn = new Pawn(new Position(1,0),ColorChessboardEnum.WHITE,chessboard,'p');
        String history3 = match.calculateHistory(false, pawn, 2, 0);
        assertEquals("a1 - a2", history3);
        
        // Test case 4: Capture move for a pawn
        Pawn pawn2 = new Pawn(new Position(2,4),ColorChessboardEnum.WHITE,chessboard, 'p');
        String history4 = match.calculateHistory(false, pawn2, 3, 5);
        assertEquals("e2xf3",history4);
        
        // Test case 5: En Passant capture
        Pawn pawn3 = new Pawn(new Position(3,2),ColorChessboardEnum.BLACK,chessboard,'P');
        String history5 = match.calculateHistory(true, pawn3, 2, 1);
        assertEquals("cxb2 e. p.",history5);      
    }
    
    @Test
    public void testTurnHandlerForRegularMove(){
        // Test 1: Regular move 
        Pawn whitePawn = new Pawn(new Position(1, 2), ColorChessboardEnum.WHITE, chessboard, 'p');
        chessboard.getSquare(1, 2).setPiece(whitePawn);
        Position enPassantPosition = match.turnHandler(whitePawn, 2, 2);
        assertEquals("c1 - c2", playerW.getHistory().get(0));
        assertNull(enPassantPosition);  
        playerW.removeLastString();
        
        // Test 2: Regular move with capture
        Pawn blackPawn = new Pawn(new Position(2,3), ColorChessboardEnum.BLACK, chessboard, 'P');
        chessboard.getSquare(2, 3).setPiece(blackPawn);
        Position enPassant = match.turnHandler(whitePawn, 2, 3);
        assertEquals("c1xd2", playerW.getHistory().get(0));
        assertNull(enPassant);     
        assertTrue(playerB.getCemetery().size() == 1);
        assertTrue(playerB.getCemetery().contains(blackPawn));
    }
    
    @Test
    public void testTurnHandlerForEnPassantMove() {
        chessboard.switchTurn();
        Pawn whitePawn = new Pawn(new Position(3, 3), ColorChessboardEnum.WHITE, chessboard, 'p');
        Pawn blackPawn = new Pawn(new Position(3,4), ColorChessboardEnum.BLACK, chessboard, 'P');
        chessboard.getSquare(3, 3).setPiece(whitePawn);
        chessboard.getSquare(3, 4).setPiece(blackPawn); 
        whitePawn.setEnPassant(true);
        Position enPassant = match.turnHandler(blackPawn, 2, 3);
        assertTrue(enPassant.equals(new Position(3,3)));
        assertEquals("exd2 e. p.", playerB.getHistory().get(0));
        assertTrue(playerW.getCemetery().size() == 1);
        assertTrue(playerW.getCemetery().contains(whitePawn));             
    }
    
    @Test
    public void testCheckHandler(){
        // Test 1: isCheck(ColorChessboardEnum.WHITE)
        Position kingPosition = new Position(0,2);
        Position avvRookPosition = new Position(3, 2);
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboardEnum.WHITE, chessboard, 'k'));
        chessboard.getSquare(avvRookPosition.getRow(), avvRookPosition.getCol()).setPiece(new Rook(avvRookPosition, ColorChessboardEnum.BLACK, chessboard, 'R'));
        playerB.addToHistory("Rf3 - c3");
        match.checkHandler();
        assertEquals("Rf3 - c3+", playerB.getHistory().get(0));
        
        // Test 2: isCheck(ColorChessboardEnum.BLACK)
        Position kingPosition2 = new Position(7,2);
        Position avvRookPosition2 = new Position(5, 2);
        chessboard.getSquare(kingPosition2.getRow(), kingPosition2.getCol()).setPiece(new King(kingPosition2, ColorChessboardEnum.BLACK, chessboard, 'K'));
        chessboard.getSquare(avvRookPosition2.getRow(), avvRookPosition2.getCol()).setPiece(new Rook(avvRookPosition2, ColorChessboardEnum.WHITE, chessboard, 'r'));
        playerW.addToHistory("Rf5 - c5");
        match.checkHandler();
        assertEquals("Rf5 - c5+", playerW.getHistory().get(0));
    }
}
