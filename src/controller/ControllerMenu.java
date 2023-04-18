/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.gameEnvironment.Player;
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
        String nameW = menu.getjTextFieldW().getText();
        String nameB = menu.getjTextFieldB().getText();
        System.out.println(nameW);
        Player playerW = new Player(nameW, true);
        Player playerB = new Player(nameB, false);
    } 
    
    public void quitActionPerformed(java.awt.event.ActionEvent evt) {                                               
        System.exit(0);
    }
}
