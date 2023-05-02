/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerMenu;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author luigi
 */
public class Menu extends JFrame{
    private ControllerMenu controller = new ControllerMenu(this);
    private JLabel title = new JLabel(new ImageIcon(".\\src\\images\\title1.png"));
    private JLabel iconB = new JLabel(new ImageIcon(".\\src\\images\\blackIconPlayer1.png"));
    private JLabel iconW = new JLabel(new ImageIcon(".\\src\\images\\whiteIconPlayer.png"));
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
        
        jPanelW.setBackground(new Color(102, 102, 255));
        jPanelB.setBackground(new Color(51, 51, 255));
        jLabelW.setBackground(new Color(153, 153, 255));
        jLabelB.setBackground(new Color(153, 153, 255));
        
               
        play.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        quit.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));        
        
        GroupLayout jPanelWLayout = new GroupLayout(jPanelW);
        jPanelW.setLayout(jPanelWLayout);
        jPanelWLayout.setHorizontalGroup(
            jPanelWLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelWLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldW, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelW))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanelWLayout.setVerticalGroup(
            jPanelWLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabelW)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanelBLayout = new GroupLayout(jPanelB);
        jPanelB.setLayout(jPanelBLayout);
        jPanelBLayout.setHorizontalGroup(
            jPanelBLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldB, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelB))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanelBLayout.setVerticalGroup(
            jPanelBLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelB)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(quit, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(play, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelB, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelW, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelB, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(play)
                    .addComponent(quit))
                .addGap(25, 25, 25))
        );
       
        play.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.playActionPerformed(evt);
        });
        
        quit.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.quitActionPerformed(evt);
        });

        //getContentPane().setBackground(new Color(153, 255, 153));
        getContentPane().setBackground(new Color(255,247,218));
        setTitle("Home");
        pack();
    }
}
