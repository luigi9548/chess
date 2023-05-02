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
    
    private ControllerGameView controller = new ControllerGameView(this);
    private JPanel scacchiera = new JPanel(); 
    //private JButton buttonGrid[][] = new JButton[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.ROW_UPPER_LIMIT + 1];
    /*private JLabel jLabelB = new JLabel("ciao"); 
    private JLabel jLabelHistory = new JLabel("History");
    private JLabel jLabelW = new JLabel("ciao");
    private JToggleButton home = new JToggleButton("Quit");*/
    
    public void initChessboard(Chessboard chessboard){      
               
        scacchiera.setLayout(new GridLayout(8,8));
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
                scacchiera.add(b);
                                
            }
        
        //getContentPane().add(scacchiera, BorderLayout.CENTER);
        //getContentPane().add(home, BorderLayout.EAST);
        setContentPane(scacchiera);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        pack();
        setVisible(true); 
    }
}
