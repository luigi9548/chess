/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.impl.ControllerGameView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.pieces.impl.Pawn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import view.GameView;

/**
 *
 * @author Elena
 */
public class ControllerGameViewTest {
    
    private GameView gameView; 
    private ControllerGameView controller;
    
    @Before
    public void setUp() {
        gameView = new GameView("White","Black");
        controller = ControllerGameView.getInstance(gameView);
    }
    
    @Test
    public void testAction(){
        
        // Test case 1: Button with white background
        ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "WHITE_SQUARE");
        controller.actions(mockEvent, ControllerGameView.WHITE_SQUARE);
        assertTrue(controller.isShowMovementCalled());
        assertFalse(controller.isMoveCalled());
        assertFalse(controller.isCastlingCalled());
        controller.resetFlags();
        
        // Test case 2: Button with black background
        ActionEvent mockEvent2 = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "BLACK_SQUARE");
        controller.actions(mockEvent2, ControllerGameView.BLACK_SQUARE);
        assertTrue(controller.isShowMovementCalled());
        assertFalse(controller.isMoveCalled());
        assertFalse(controller.isCastlingCalled());
        controller.resetFlags();
        
        // Test case 3: Button with red background
        ActionEvent mockEvent3 = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RED_SQUARE");
        controller.actions(mockEvent3, ControllerGameView.RED_SQUARE);
        assertTrue(controller.isMoveCalled());
        assertFalse(controller.isShowMovementCalled());
        assertFalse(controller.isCastlingCalled());
        controller.resetFlags();
        
        // Test case 4: Button with green background
        ActionEvent mockEvent4 = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "GREEN_SQUARE");
        controller.actions(mockEvent4, ControllerGameView.GREEN_SQUARE);
        assertTrue(controller.isCastlingCalled());
        assertFalse(controller.isShowMovementCalled());
        assertFalse(controller.isMoveCalled());   
    }
    
    @Test
    public void testShowMovement() {   
        Pawn pawn = new Pawn(new Position(1,1), ColorChessboard.WHITE ,controller.getMatch().getChessboard(), 'p');
        var button = gameView.getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol());
        ActionEvent mockEvent = new ActionEvent(button, ActionEvent.ACTION_PERFORMED, button.getActionCommand());
        ArrayList<Position> expectedLegalMoves = controller.getMatch().getChessboard().legalMovements(pawn);
        
        controller.actions(mockEvent, ControllerGameView.WHITE_SQUARE);
     
        assertEquals(2, expectedLegalMoves.size());
        for (Position position : expectedLegalMoves) {
            var buttonExp = gameView.getButtonGrid(position.getRow(), position.getCol());
            Color buttonColor = buttonExp.getBackground();
            assertEquals(Color.RED, buttonColor);
        }
    }
    
    @Test
    public void testUpdatePositionAndIcon(){
        // updatePosition() already tested in ChessboardTest.
        
        // Simulation of a movement.
        // show movement.
        Pawn pawn = new Pawn(new Position(1,1), ColorChessboard.WHITE ,controller.getMatch().getChessboard(), 'p');
        pawn.setIcon(".\\src\\images\\whitePawn.png");
        var buttonShow = gameView.getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol());
        ActionEvent show = new ActionEvent(buttonShow, ActionEvent.ACTION_PERFORMED, buttonShow.getActionCommand());
        controller.actions(show, ControllerGameView.WHITE_SQUARE);
        // move.
        var buttonMove = gameView.getButtonGrid(2, 1);
        ActionEvent move = new ActionEvent(buttonMove, ActionEvent.ACTION_PERFORMED,buttonMove.getActionCommand());
        controller.actions(move, ControllerGameView.RED_SQUARE);
        
        assertEquals(pawn.getIcon(), gameView.getButtonGrid(1, 2).getIcon().toString());
        assertNull(gameView.getButtonGrid(1, 1).getIcon());
    }

    @Test
    public void testUpdateEmptyCementary(){
        // updateCemetery() is called every time a movement is performed.
        
        Pawn pawn = new Pawn(new Position(1,1), ColorChessboard.WHITE ,controller.getMatch().getChessboard(), 'p');    
        // Show movement.
        var buttonShow = gameView.getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol());
        ActionEvent show = new ActionEvent(buttonShow, ActionEvent.ACTION_PERFORMED, buttonShow.getActionCommand());
        controller.actions(show, ControllerGameView.WHITE_SQUARE);
        // Move.
        var buttonMove = gameView.getButtonGrid(2, 1);
        ActionEvent move = new ActionEvent(buttonMove, ActionEvent.ACTION_PERFORMED,buttonMove.getActionCommand());
        controller.actions(move, ControllerGameView.RED_SQUARE);
        assertTrue(controller.getMatch().getPlayer(ColorChessboard.WHITE).getCemetery().isEmpty());
        assertTrue(controller.getMatch().getPlayer(ColorChessboard.BLACK).getCemetery().isEmpty());
        assertEquals("", gameView.getjLabelCemeteryBlack().getText());
        assertEquals("", gameView.getjLabelCemeteryWhite().getText());
    }
    
    @Test
    public void testUpdateCemeteryWithPiece(){
        
        Pawn pawn = new Pawn(new Position(1,1), ColorChessboard.WHITE ,controller.getMatch().getChessboard(), 'p');
        Pawn pawn2 = new Pawn(new Position(2,2), ColorChessboard.BLACK, controller.getMatch().getChessboard(), 'P');
        controller.getMatch().getChessboard().getSquare(pawn2.getPosition().getRow(),pawn2.getPosition().getCol()).setPiece(pawn2);   
        // Show movement
        var buttonShow = gameView.getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol());
        ActionEvent show = new ActionEvent(buttonShow, ActionEvent.ACTION_PERFORMED, buttonShow.getActionCommand());
        controller.actions(show, ControllerGameView.WHITE_SQUARE);
        // Move.
        var buttonMove = gameView.getButtonGrid(2, 2);
        ActionEvent move = new ActionEvent(buttonMove, ActionEvent.ACTION_PERFORMED,buttonMove.getActionCommand());
        controller.actions(move, ControllerGameView.RED_SQUARE);
        assertFalse(controller.getMatch().getPlayer(ColorChessboard.BLACK).getCemetery().isEmpty());
        assertEquals("♟", gameView.getjLabelCemeteryBlack().getText());
        
    }
    
    @Test
    public void testUpdateHistory(){
        // The functioning of the history calculation has already been tested in MatchTest, 
        // we just need to test that the previously calculated history is displayed.
        
        Pawn pawn = new Pawn(new Position(1,1), ColorChessboard.WHITE ,controller.getMatch().getChessboard(), 'p');    
        // Show movement.
        var buttonShow = gameView.getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol());
        ActionEvent show = new ActionEvent(buttonShow, ActionEvent.ACTION_PERFORMED, buttonShow.getActionCommand());
        controller.actions(show, ControllerGameView.WHITE_SQUARE);
        // Move.
        var buttonMove = gameView.getButtonGrid(2, 1);
        ActionEvent move = new ActionEvent(buttonMove, ActionEvent.ACTION_PERFORMED,buttonMove.getActionCommand());
        controller.actions(move, ControllerGameView.RED_SQUARE);

        String history ;
        //history = "                 Bianco \t\tNero" + "\n0. b1 - b2";
        history = "                Bianco 		Nero\n" +
                    "0. b1 - b2		             \n" +
                    "1.         	        ";
                
        assertEquals(history,gameView.getHistory().getText());
    }
        
}
