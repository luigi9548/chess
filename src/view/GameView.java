/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerGameView;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.gameEnvironment.Chessboard;
import model.gameEnvironment.Player;

/**
 *
 * @author luigi
 */
public class GameView extends JFrame{
    
    private JPanel scacchiera = new JPanel();
    private JButton[][] buttonGrid = new JButton[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
    private ControllerGameView controller = new ControllerGameView(this);
    private JLabel jLabelCemeteryBlack = new JLabel();
    private JLabel jLabelCemeteryWhite = new JLabel();
    private JLabel jLabelWhiteN = new JLabel();
    private JLabel jLabelBlackN = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea history = new JTextArea();

    public GameView(Chessboard chessboard, String whiteN, String blackN){
        init(whiteN, blackN);
        initChessboard(chessboard);
    }
    
    public JButton getButtonGrid(int x, int y){
        return this.buttonGrid[x][y];
    }
    
    public JTextArea getHistory(){
        return this.history;
    }
    
    public JLabel getjLabelCemeteryWhite(){
        return this.jLabelCemeteryWhite;
    }
    
    public JLabel getjLabelCemeteryBlack(){
        return this.jLabelCemeteryBlack;
    }
    
    public ControllerGameView getController(){
        return this.controller;
    }
    
    public void init(String whiteN, String blackN){
        this.jLabelCemeteryBlack.setFont(new Font("SansSerif", 0, 40));
        this.jLabelCemeteryWhite.setFont(new Font("SansSerif", 0, 40));
        this.jLabelWhiteN.setFont(new Font("SansSerif", 1, 25));
        this.jLabelBlackN.setFont(new Font("SansSerif", 1, 25));
        this.jLabelWhiteN.setText(whiteN);
        this.jLabelBlackN.setText(blackN);
        history.setEditable(false);
        history.setColumns(20);
        history.setText("");
        history.append("                Bianco \t\tNero");
        history.setRows(5);
        jScrollPane1.setViewportView(history);
        
        scacchiera.setPreferredSize(new java.awt.Dimension(600, 600));
        GroupLayout scacchieraLayout = new GroupLayout(scacchiera);
        scacchiera.setLayout(scacchieraLayout);
        scacchieraLayout.setHorizontalGroup(
            scacchieraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        scacchieraLayout.setVerticalGroup(
            scacchieraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBlackN, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelCemeteryBlack, GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                                .addComponent(jLabelCemeteryWhite, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabelWhiteN, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scacchiera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelWhiteN, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelCemeteryBlack, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scacchiera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCemeteryWhite, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBlackN, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void initChessboard(Chessboard chessboard){  
        scacchiera.setLayout(new GridLayout(8,8));
        scacchiera.setSize(600,600);
        for (int i=0; i<=7; i++)
            for (int j=0; j<=7; j++) {
                JButton b = new JButton();
                b.setSize(50, 50);
                if ((i+j)%2==0)
                    b.setBackground(new Color(255, 250, 219));
                else
                    b.setBackground(new Color(156, 92, 8));
        
                if(i == 0 || i == 1 || i == 6 || i == 7){
                    Icon icon = new ImageIcon(chessboard.getSquare(i, j).getPiece().get().getIcon());
                    b.setIcon(icon);
                }
                buttonGrid[i][j] = b;
                b.addActionListener((java.awt.event.ActionEvent evt) -> {
                    controller.actions(evt, b.getBackground().getRGB());
                });
                scacchiera.add(b);
            }
    }
    
    public void resetColors(){
        for (int i=0; i<=7; i++){
            for (int j=0; j<=7; j++) {
                if ((i+j)%2==0)
                    this.getButtonGrid(i, j).setBackground(new Color(255, 250, 219));
                else
                    this.getButtonGrid(i, j).setBackground(new Color(156, 92, 8));
            }
        }
    }
}
