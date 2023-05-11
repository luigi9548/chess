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
import model.pieces.Piece;
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
        
    }
    private void showMovement(java.awt.event.ActionEvent evt) { 
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                   gameView.resetColors();
                   chessboard.getSquare(row, col).getPiece().get().setInAction(true);
                   this.changeBottonColor(chessboard.getSquare(row, col).getPiece().get().calculateMovement(null));
                }
            }
        }
    } 
    
    /* problema: se prima schiaccio un pezzo p1 per vedere le sue possibili mosse senza pero muoverlo
       e poi schiaccio un altro pezzo p2 per muoverlo, si muoverà p1 
       evidentemente se schiaccio senza muovere rimane il isInAction = true
    */
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
