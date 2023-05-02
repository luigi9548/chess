/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.ControllerGameView;
import java.awt.*;
import javax.swing.*;
import model.gameEnvironment.Chessboard;

/**
 *
 * @author luigi
 */
public class GameView extends JFrame{
    
    private JPanel pannello;
    private JButton[][] buttonGrid = new JButton[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
    private ControllerGameView controller = new ControllerGameView(this);

    public JButton getButtonGrid(int x, int y){
        return this.buttonGrid[x][y];
    }
    public void initChessboard(Chessboard chessboard){
        pannello = new JPanel(new GridLayout(8,8));
        for (int i=0; i<=7; i++)
            for (int j=0; j<=7; j++) {
                JButton b = new JButton();
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
                    controller.showMovement(evt);
                });
                pannello.add(b);
            }
        setContentPane(pannello);
        setTitle("Scacchiera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(600,600);
        setVisible(true); 
    }
    
    
}
