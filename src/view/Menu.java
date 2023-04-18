/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerMenu;
import javax.swing.*;
/**
 *
 * @author luigi
 */
public class Menu extends JFrame{
    private ControllerMenu controller = new ControllerMenu(this);
    private JPanel jPanelW = new JPanel();
    private JLabel jLabelW = new JLabel("Inserisci il nome del giocatore bianco");
    private JTextField jTextFieldW = new JTextField("GuestW");
    private JPanel jPanelB = new JPanel();
    private JLabel jLabelB = new JLabel("Inserisci il nome del giocatore nero");
    private JTextField jTextFieldB = new JTextField("GuestB");
    private JToggleButton play = new JToggleButton("Play");
    private JToggleButton quit = new JToggleButton("Quit");

    public JTextField getjTextFieldW() {
        return jTextFieldW;
    }

    public JTextField getjTextFieldB() {
        return jTextFieldB;
    }
    
    public JToggleButton getPlay() {
        return play;
    }

    public JToggleButton getQuit() {
        return quit;
    }
   
    public Menu() {
        initComponents();
    }
    
    private void initComponents(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        jPanelW.setBackground(new java.awt.Color(102, 102, 255));
        jPanelB.setBackground(new java.awt.Color(51, 51, 255));
        jLabelW.setBackground(new java.awt.Color(153, 153, 255));
        jLabelB.setBackground(new java.awt.Color(153, 153, 255));
        
        play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));        
        
        GroupLayout jPanelWLayout = new GroupLayout(jPanelW);
        jPanelW.setLayout(jPanelWLayout);
        jPanelWLayout.setHorizontalGroup(
            jPanelWLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelWLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabelW, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelWLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldW, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelWLayout.setVerticalGroup(
            jPanelWLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelW, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        GroupLayout jPanelBLayout = new GroupLayout(jPanelB);
        jPanelB.setLayout(jPanelBLayout);
        jPanelBLayout.setHorizontalGroup(
            jPanelBLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelBLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabelB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldB, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBLayout.setVerticalGroup(
            jPanelBLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelB, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );      
               
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanelW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanelB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quit, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(play, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelW, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(play)
                    .addComponent(quit))
                .addContainerGap())
        );
        
        play.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.playActionPerformed(evt);
        });
        
        quit.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.quitActionPerformed(evt);
        });

        

        pack();
    }
}
