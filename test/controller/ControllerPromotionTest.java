/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.impl.ControllerGameView;
import controller.impl.ControllerPromotion;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import model.enumerations.ColorChessboardEnum;
import model.enumerations.IconEnum;
import model.functionality.impl.Position;
import model.pieces.impl.Bishop;
import model.pieces.impl.Knight;
import model.pieces.impl.Pawn;
import model.pieces.impl.Queen;
import model.pieces.impl.Rook;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import view.GameView;
import view.Promotion;

/**
 *
 * @author Elena
 */
public class ControllerPromotionTest {
    
    private GameView gameView;
    private ControllerGameView controllerGameView;
    private Promotion promotion;
    private ControllerPromotion controller;
    private Pawn pawn;
    
    @Before
    public void setUp(){
        gameView = new GameView("White","Black");
        controllerGameView = ControllerGameView.getInstance(gameView);
        pawn = new Pawn(new Position(7,7), ColorChessboardEnum.WHITE,controllerGameView.getMatch().getChessboard(), 'p');
        this.promotion = new Promotion(pawn, controllerGameView, controllerGameView.getMatch());
        this.controller = ControllerPromotion.getInstance(promotion);
    }
    
    @Test
    public void testQueen(){
        ActionEvent evt = new ActionEvent(promotion.getQueen(), ActionEvent.ACTION_PERFORMED, promotion.getQueen().getActionCommand());
        controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).addToHistory("test");
        controller.queenActionPerformed(evt);
        Queen queenW = new Queen(pawn.getPosition(), ColorChessboardEnum.WHITE, controllerGameView.getMatch().getChessboard(), 'q');      
        ArrayList<String> history = new ArrayList();
        history.add("h7Q");
        
        assertEquals(IconEnum.WQUEEN_ICON.getIcon(), controllerGameView.getGameView().getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol()).getIcon().toString());
        assertTrue(controllerGameView.getMatch().getChessboard().getSquare(queenW.getPosition().getRow(), queenW.getPosition().getCol()).getPiece().get() instanceof Queen);
        assertEquals(history,controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).getHistory());
        
    }
    
    @Test 
    public void testRook(){
        ActionEvent evt = new ActionEvent(promotion.getRook(), ActionEvent.ACTION_PERFORMED, promotion.getRook().getActionCommand());
        controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).addToHistory("test");
        controller.rookActionPerformed(evt);
        Rook rookW = new Rook(pawn.getPosition(), ColorChessboardEnum.WHITE, controllerGameView.getMatch().getChessboard(), 'r');      
        ArrayList<String> history = new ArrayList();
        history.add("h7R");
        
        assertEquals(IconEnum.WROOK_ICON.getIcon(), controllerGameView.getGameView().getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol()).getIcon().toString());
        assertTrue(controllerGameView.getMatch().getChessboard().getSquare(rookW.getPosition().getRow(), rookW.getPosition().getCol()).getPiece().get() instanceof Rook);
        assertEquals(history,controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).getHistory());
        
    }
    
    @Test
    public void testBishop(){
        ActionEvent evt = new ActionEvent(promotion.getBishop(), ActionEvent.ACTION_PERFORMED, promotion.getBishop().getActionCommand());
        controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).addToHistory("test");
        controller.bishopActionPerformed(evt);
        Bishop bishopW = new Bishop(pawn.getPosition(), ColorChessboardEnum.WHITE, controllerGameView.getMatch().getChessboard(), 'b');      
        ArrayList<String> history = new ArrayList();
        history.add("h7B");
        
        assertEquals(IconEnum.WBISHOP_ICON.getIcon(), controllerGameView.getGameView().getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol()).getIcon().toString());
        assertTrue(controllerGameView.getMatch().getChessboard().getSquare(bishopW.getPosition().getRow(), bishopW.getPosition().getCol()).getPiece().get() instanceof Bishop);
        assertEquals(history,controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).getHistory());
        
    }
    
    @Test
    public void testKnight(){
        ActionEvent evt = new ActionEvent(promotion.getKnight(), ActionEvent.ACTION_PERFORMED, promotion.getKnight().getActionCommand());
        controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).addToHistory("test");
        controller.knightActionPerformed(evt);
        Knight knightW = new Knight(pawn.getPosition(), ColorChessboardEnum.WHITE, controllerGameView.getMatch().getChessboard(), 'k');      
        ArrayList<String> history = new ArrayList();
        history.add("h7H");
        
        assertEquals(IconEnum.WKNIGHT_ICON.getIcon(), controllerGameView.getGameView().getButtonGrid(pawn.getPosition().getRow(), pawn.getPosition().getCol()).getIcon().toString());
        assertTrue(controllerGameView.getMatch().getChessboard().getSquare(knightW.getPosition().getRow(), knightW.getPosition().getCol()).getPiece().get() instanceof Knight);
        assertEquals(history,controllerGameView.getMatch().getPlayer(ColorChessboardEnum.WHITE).getHistory());
        
    }
    
}
