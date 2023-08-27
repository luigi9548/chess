/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.impl;

import java.awt.event.ActionEvent;
import view.GameConclusion;
import view.Menu;

/**
 *
 * @author Elena
 */
public class ControllerGameConclusion {
    private final GameConclusion gameConclusion;
    private final Menu menu = new Menu();
    
    public ControllerGameConclusion(final GameConclusion gameConclusion){
        this.gameConclusion = gameConclusion;
    }

    public void quitActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    public void newMatchActionPerformed(ActionEvent evt) {
        gameConclusion.getGameView().setVisible(false);
        gameConclusion.setVisible(false);
        
        menu.setVisible(true);
        
    }
    
    
    
}
