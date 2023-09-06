/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameEnvironment;

import model.functionality.impl.ChessTimer;
import model.functionality.impl.ColorChessboard;
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
        playerW = new Player(ColorChessboard.WHITE);
        playerB = new Player(ColorChessboard.BLACK);
        chessboard = new Chessboard(new Position(0,0));
        match = new Match(chessboard,playerW,playerB, new ChessTimer(600 * 1000));
    }
    
    @Test
    public void testTurnHandlerForRegularMove(){
        // Test 1: Regular move 
        Pawn whitePawn = new Pawn(new Position(1, 2), ColorChessboard.WHITE, chessboard, 'p');
        chessboard.getSquare(1, 2).setPiece(whitePawn);
        Position enPassantPosition = match.turnHandler(whitePawn, 2, 2);
        assertEquals("c1 - c2", playerW.getHistory().get(0));
        assertNull(enPassantPosition);  
        playerW.removeLastString();
        
        // Test 2: Regular move with capture
        Pawn blackPawn = new Pawn(new Position(2,3), ColorChessboard.BLACK, chessboard, 'P');
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
        Pawn whitePawn = new Pawn(new Position(3, 3), ColorChessboard.WHITE, chessboard, 'p');
        Pawn blackPawn = new Pawn(new Position(3,4), ColorChessboard.BLACK, chessboard, 'P');
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
        // Test 1: isCheck(ColorChessboard.WHITE)
        Position kingPosition = new Position(0,2);
        Position avvRookPosition = new Position(3, 2);
        chessboard.getSquare(kingPosition.getRow(), kingPosition.getCol()).setPiece(new King(kingPosition, ColorChessboard.WHITE, chessboard, 'k'));
        chessboard.getSquare(avvRookPosition.getRow(), avvRookPosition.getCol()).setPiece(new Rook(avvRookPosition, ColorChessboard.BLACK, chessboard, 'R'));
        playerB.addToHistory("Rf3 - c3");
        match.checkHandler();
        assertEquals("Rf3 - c3+", playerB.getHistory().get(0));
        
        // Test 2: isCheck(ColorChessboard.BLACK)
        Position kingPosition2 = new Position(7,2);
        Position avvRookPosition2 = new Position(5, 2);
        chessboard.getSquare(kingPosition2.getRow(), kingPosition2.getCol()).setPiece(new King(kingPosition2, ColorChessboard.BLACK, chessboard, 'K'));
        chessboard.getSquare(avvRookPosition2.getRow(), avvRookPosition2.getCol()).setPiece(new Rook(avvRookPosition2, ColorChessboard.WHITE, chessboard, 'r'));
        playerW.addToHistory("Rf5 - c5");
        match.checkHandler();
        assertEquals("Rf5 - c5+", playerW.getHistory().get(0));
    }
}
