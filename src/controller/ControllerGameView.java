/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
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
    public void showMovement(java.awt.event.ActionEvent evt) {                                               
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                   this.changeBottonColor(chessboard.getSquare(row, col).getPiece().get().calculateMovement(null));
                }
            }
        }
    } 
    
    private void changeBottonColor(ArrayList<Position> positions){
        for(Position p : positions){
          //  System.out.println(p.getRow() + " " + p.getCol());
            gameView.getButtonGrid(p.getRow(), p.getCol()).setBackground(Color.red);
        }
    }
}
