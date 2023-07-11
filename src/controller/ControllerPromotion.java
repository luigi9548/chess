/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.Bishop;
import model.pieces.Knight;
import model.pieces.Queen;
import model.pieces.Rook;
import view.GameView;
import view.GameConclusion;
import view.Promotion;

/**
 *
 * @author Elena
 */
public class ControllerPromotion {
    private Promotion promotion;
    private Position position;
    private GameView gameView;
    private Chessboard chessboard;
    private Icon icon;
    
    public ControllerPromotion(Promotion promotion){
        this.promotion = promotion;
    }
    
    private void init(String s){
        position = promotion.getPawn().getPosition();
        gameView = promotion.getGameView();
        chessboard = promotion.getChessboard();

        switch(s){
            case "QUEEN":
                if (promotion.getPawn().getColor() == 0) 
                    icon = new ImageIcon(".\\src\\images\\whiteQueen.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackQueen.png");
            break;
            case "ROOK":
                if (promotion.getPawn().getColor() == 0) 
                    icon = new ImageIcon(".\\src\\images\\whiteRook.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackRook.png");
            break;
            case "BISHOP":
                if (promotion.getPawn().getColor() == 0) 
                    icon = new ImageIcon(".\\src\\images\\whiteBishop.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackBishop.png");
            break;
            case "KNIGHT":
                if (promotion.getPawn().getColor() == 0) 
                    icon = new ImageIcon(".\\src\\images\\whiteKnight.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackKnight.png");
            break;    
        }        
    }

    public void queenActionPerformed(ActionEvent evt) {
        init("QUEEN");
        // imposto icona nella view
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        
        // posiziono il pezzo a livello model
        if(promotion.getPawn().getColor() == 0){
            Queen queenW = new Queen("Queen",position,0,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(queenW);
            queenW.setIcon(".\\src\\images\\whiteQueen.png");
        }else{
            Queen queenB = new Queen("Queen",position,1,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(queenB);
            queenB.setIcon(".\\src\\images\\blakcQueen.png");
        }
        
        promotion.setVisible(false);
    }

    public void rookActionPerformed(ActionEvent evt) {
        init("ROOK");
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        if(promotion.getPawn().getColor() == 0){
            Rook rookW = new Rook("Rook",position,0,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(rookW);
            rookW.setIcon(".\\src\\images\\whiteRook.png");
        }else{
            Rook rookB = new Rook("Rook",position,1,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(rookB);
            rookB.setIcon(".\\src\\images\\blackRook.png");
        }
        promotion.setVisible(false);
    }

    public void bishopActionPerformed(ActionEvent evt) {
        init("BISHOP");
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        if(promotion.getPawn().getColor() == 0){
            Bishop bishopW = new Bishop("Bishop",position,0,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(bishopW);
            bishopW.setIcon(".\\src\\images\\whiteBishop.png");
        }else{
            Bishop bishopB = new Bishop("ishop",position,1,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(bishopB);
            bishopB.setIcon(".\\src\\images\\blackBishop.png");
        }
        promotion.setVisible(false);
    }

    public void knightActionPerformed(ActionEvent evt) {
        init("KNIGHT");
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        if(promotion.getPawn().getColor() == 0){
            Knight knightW = new Knight("Knight",position,0,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(knightW);
            knightW.setIcon(".\\src\\images\\whiteKnight.png");
        }else{
            Knight knightB = new Knight("Knight",position,1,chessboard);
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(knightB);
            knightB.setIcon(".\\src\\images\\blackKnight.png");
        }
        promotion.setVisible(false);
    }
    
    
    
}
