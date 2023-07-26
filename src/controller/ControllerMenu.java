/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.gameEnvironment.Chessboard;
import model.gameEnvironment.Player;
import view.GameView;
import view.Menu;
/**
 *
 * @author luigi
 */
public class ControllerMenu {
    private final Menu menu;
    
    /**
     *
     * @param menu
     */
    public ControllerMenu(final Menu menu){
        this.menu = menu;
    }
    
    public void playActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String whiteN = menu.getjTextFieldW().getText();
        String blackN = menu.getjTextFieldB().getText();
        
        menu.setVisible(false);
        
        Chessboard chessboard = new Chessboard();
        GameView gameView = new GameView(chessboard, whiteN, blackN);
        
        // a gameView gli passo la chessboard ma come gli passo i player?
    } 
    
    public void quitActionPerformed(java.awt.event.ActionEvent evt) {                                               
        System.exit(0);
    }
}
