/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.impl.ControllerGameView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Pawn;
import model.pieces.impl.Rook;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        controller = new ControllerGameView(gameView);
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
    
}
