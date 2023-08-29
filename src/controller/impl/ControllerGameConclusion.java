package controller.impl;

import java.awt.event.ActionEvent;
import view.GameConclusion;
import view.Menu;

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
