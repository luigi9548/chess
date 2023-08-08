package controller;

import model.gameEnvironment.Chessboard;
import view.GameView;
import view.Menu;

public class ControllerMenu {
    private final Menu menu;

    public ControllerMenu(final Menu menu){
        this.menu = menu;
    }
    
    public void playActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String whiteN = menu.getjTextFieldW().getText();
        String blackN = menu.getjTextFieldB().getText();
        
        menu.setVisible(false);
        
        Chessboard chessboard = new Chessboard();
        GameView gameView = new GameView(chessboard, whiteN, blackN);
    } 
    
    public void quitActionPerformed(java.awt.event.ActionEvent evt) {                                               
        System.exit(0);
    }
}
