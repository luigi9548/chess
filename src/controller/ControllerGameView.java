/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
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
    
    
    
}
