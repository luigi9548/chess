package controller.impl;

import view.GameView;
import view.Menu;

public class ControllerMenu {
    private static ControllerMenu controllerMenu;
    private final Menu menu;
    
    public static ControllerMenu getInstance(Menu menu){
        return controllerMenu == null ? controllerMenu = new ControllerMenu(menu) : controllerMenu;
    }
    
    private ControllerMenu(final Menu menu){
        this.menu = menu;
    }
    
    public void playActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String whiteN = menu.getjTextFieldW().getText();
        String blackN = menu.getjTextFieldB().getText();
        
        menu.setVisible(false);
        GameView gameView = new GameView(whiteN, blackN);
    } 
    
    public void quitActionPerformed(java.awt.event.ActionEvent evt) {                                               
        System.exit(0);
    }
}
