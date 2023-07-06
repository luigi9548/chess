/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.King;
import model.pieces.Piece;
import model.pieces.Rook;
import view.GameView;
/**
 *
 * @author luigi
 */
public class ControllerGameView {
    private GameView gameView;
    private Chessboard chessboard = new Chessboard();
    
    public ControllerGameView(GameView gameView){
        this.gameView = gameView;
    }
    
    public void actions(java.awt.event.ActionEvent evt, int color){
        if(color == -1317 || color == -6530040) //biamco e nero
            this.showMovement(evt);
        else if(color == -65536) //rosso
            this.move(evt);
        else if(color == -16711936){ // verde
            Rook rook = (Rook) this.getEvtPiece(evt);
            if(rook != null)
                this.castling(rook);
        }
        
    }
    private void showMovement(java.awt.event.ActionEvent evt) { 
        Position castling;
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                   gameView.resetColors();
                   this.searchPiece();
                   chessboard.getSquare(row, col).getPiece().get().setInAction(true);
                   this.changeBottonColor(chessboard.getSquare(row, col).getPiece().get().calculateMovement(null));
                   if(chessboard.getSquare(row, col).getPiece().get() instanceof King){
                        castling = chessboard.canCastling(chessboard.getSquare(row, col).getPiece().get().getColor());
                        if(castling != null)
                            gameView.getButtonGrid(castling.getRow(), castling.getCol()).setBackground(Color.GREEN);
                   }
                }
            }
        }
    } 
    
    private void castling(Rook rook){
        Icon rookIcon = new ImageIcon(chessboard.getSquare(rook.getPosition().getRow(), rook.getPosition().getCol()).getPiece().get().getIcon());
        Icon kingIcon = null;
        if(rook.getColor() == 0){
            kingIcon = new ImageIcon(chessboard.getSquare(0, 3).getPiece().get().getIcon());
            if(rook.getPosition().compare(new Position(0,0))){
                gameView.getButtonGrid(0, 1).setIcon(kingIcon);
                gameView.getButtonGrid(0, 3).setIcon(null);
                chessboard.getSquare(0, 1).setPiece(chessboard.getSquare(0, 3).getPiece().get());
                chessboard.getSquare(0, 1).getPiece().get().setPosition(new Position(0, 1));
                chessboard.getSquare(0, 3).setPiece(null);
                gameView.getButtonGrid(0, 2).setIcon(rookIcon);
                gameView.getButtonGrid(0, 0).setIcon(null);
                chessboard.getSquare(0, 2).setPiece(chessboard.getSquare(0, 0).getPiece().get());
                chessboard.getSquare(0, 2).getPiece().get().setPosition(new Position(0, 2));
                chessboard.getSquare(0, 0).setPiece(null);
            }else{
                gameView.getButtonGrid(0, 5).setIcon(kingIcon);
                gameView.getButtonGrid(0, 3).setIcon(null);
                chessboard.getSquare(0, 5).setPiece(chessboard.getSquare(0, 3).getPiece().get());
                chessboard.getSquare(0, 5).getPiece().get().setPosition(new Position(0, 5));
                chessboard.getSquare(0, 3).setPiece(null);
                gameView.getButtonGrid(0, 4).setIcon(rookIcon);
                gameView.getButtonGrid(0, 7).setIcon(null);
                chessboard.getSquare(0, 4).setPiece(chessboard.getSquare(0, 7).getPiece().get());
                chessboard.getSquare(0, 4).getPiece().get().setPosition(new Position(0, 4));
                chessboard.getSquare(0, 7).setPiece(null);
            }
        }else{
            kingIcon = new ImageIcon(chessboard.getSquare(7, 3).getPiece().get().getIcon());
            if(rook.getPosition().compare(new Position(7,0))){
                gameView.getButtonGrid(7, 1).setIcon(kingIcon);
                gameView.getButtonGrid(7, 3).setIcon(null);
                chessboard.getSquare(7, 1).setPiece(chessboard.getSquare(7, 3).getPiece().get());
                chessboard.getSquare(7, 1).getPiece().get().setPosition(new Position(7, 1));
                chessboard.getSquare(7, 3).setPiece(null);
                gameView.getButtonGrid(7, 2).setIcon(rookIcon);
                gameView.getButtonGrid(7, 0).setIcon(null);
                chessboard.getSquare(7, 2).setPiece(chessboard.getSquare(7, 0).getPiece().get());
                chessboard.getSquare(7, 2).getPiece().get().setPosition(new Position(7, 2));
                chessboard.getSquare(7, 0).setPiece(null);
            }else{
                gameView.getButtonGrid(7, 5).setIcon(kingIcon);
                gameView.getButtonGrid(7, 3).setIcon(null);
                chessboard.getSquare(7, 5).setPiece(chessboard.getSquare(7, 3).getPiece().get());
                chessboard.getSquare(7, 5).getPiece().get().setPosition(new Position(7, 5));
                chessboard.getSquare(7, 3).setPiece(null);
                gameView.getButtonGrid(7, 4).setIcon(rookIcon);
                gameView.getButtonGrid(7, 7).setIcon(null);
                chessboard.getSquare(7, 4).setPiece(chessboard.getSquare(7, 7).getPiece().get());
                chessboard.getSquare(7, 4).getPiece().get().setPosition(new Position(7, 4));
                chessboard.getSquare(7, 7).setPiece(null);
            }
        }
        gameView.resetColors();
    }
    private void move(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    Piece p = this.searchPiece();
                    if(p != null){
                        gameView.resetColors();
                        Icon icon = new ImageIcon(chessboard.getSquare(p.getPosition().getRow(), p.getPosition().getCol()).getPiece().get().getIcon());
                        gameView.getButtonGrid(row, col).setIcon(icon);
                        gameView.getButtonGrid(p.getPosition().getRow(), p.getPosition().getCol()).setIcon(null);
                        chessboard.getSquare(row, col).setPiece(p);
                        chessboard.getSquare(p.getPosition().getRow(), p.getPosition().getCol()).setPiece(null);
                        chessboard.getSquare(row, col).getPiece().get().setPosition(new Position(row,col));
                    }
                }
            }
        }
    }
    
    private Piece getEvtPiece(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    return chessboard.getSquare(row, col).getPiece().get();
                }
            }
        }
        return null;
    }
    
    private Piece searchPiece(){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(chessboard.getSquare(row, col).getPiece().isPresent()){
                    if(chessboard.getSquare(row, col).getPiece().get().isInAction()== true){
                        chessboard.getSquare(row, col).getPiece().get().setInAction(false);
                        return chessboard.getSquare(row, col).getPiece().get();
                    }
                }
            }
        }
        return null;
    }
    private void changeBottonColor(ArrayList<Position> positions){
        for(Position p : positions){
          //  System.out.println(p.getRow() + " " + p.getCol());
            gameView.getButtonGrid(p.getRow(), p.getCol()).setBackground(Color.red);
        }
    }
}
