/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ControllerPromotion;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.*;
import javax.swing.border.Border;
import model.gameEnvironment.Chessboard;
import model.pieces.Pawn;

/**
 *
 * @author Elena
 */
public class Promotion extends JFrame {
    private final Pawn pawn;
    private final ControllerPromotion controller = new ControllerPromotion(this);
    private GameView gameView;
    private Chessboard chessboard;
    private String choice;
    
    private JButton jButtonQueen;
    private JButton jButtonRook;
    private JButton jButtonBishop;
    private JButton jButtonKnight;
    private JLabel title;
    private JLabel text;
    private JLabel jLabelQueen;
    private JLabel jLabelKnight;
    private JLabel jLabelRook;
    private JLabel jLabelBishop;
    private Icon iconQueen;
    private Icon iconRook;
    private Icon iconBishop;
    private Icon iconKnight;
    
    public Promotion(Pawn pawn, GameView gameView, Chessboard chessboard) {
        this.pawn = pawn;
        this.gameView = gameView;
        this.chessboard = chessboard;
        initComponents();
    }
    
    public GameView getGameView(){
        return this.gameView;
    }
    
    public Pawn getPawn(){
        return this.pawn;
    }
    
    public Chessboard getChessboard(){
        return this.chessboard;
    }
    
    private void initComponents() {
        title = new JLabel("Promozione pedone");
        text = new JLabel("Scegli un pezzo per la promozione del pedone:");
        jButtonQueen = new JButton();
        jButtonRook = new JButton();
        jButtonBishop = new JButton();
        jButtonKnight = new JButton();
        jLabelQueen = new JLabel("Regina");
        jLabelKnight = new JLabel("Cavallo");
        jLabelRook = new JLabel("Torre");
        jLabelBishop = new JLabel("Alfiere");

        getContentPane().setBackground(new Color(218,189,171));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setLocation(100,70);

        title.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        title.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        if(pawn.getColor() == 0){
            iconQueen = new ImageIcon(".\\src\\images\\whiteQueen.png");
            iconRook = new ImageIcon(".\\src\\images\\whiteRook.png");
            iconBishop = new ImageIcon(".\\src\\images\\whiteBishop.png");
            iconKnight = new ImageIcon(".\\src\\images\\whiteKnight.png");
            
            Color color = gameView.getButtonGrid(pawn.getPosition().getRow(),pawn.getPosition().getCol()).getBackground();
            initButton(color);
        }else{
            iconQueen = new ImageIcon(".\\src\\images\\blackQueen.png");
            iconRook = new ImageIcon(".\\src\\images\\blackRook.png");
            iconBishop = new ImageIcon(".\\src\\images\\blackBishop.png");
            iconKnight = new ImageIcon(".\\src\\images\\blackKnight.png");
            
            Color color = gameView.getButtonGrid(pawn.getPosition().getRow(),pawn.getPosition().getCol()).getBackground();
            initButton(color);
        }
                

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(text, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
                            .addComponent(title, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelQueen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonQueen ,GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonRook, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabelRook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBishop, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBishop, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonKnight, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelKnight, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQueen, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRook)
                    .addComponent(jLabelBishop)
                    .addComponent(jLabelKnight, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonQueen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBishop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonKnight, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        
        jButtonQueen.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.queenActionPerformed(evt);
        });
        
        jButtonRook.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.rookActionPerformed(evt);
        });
        
        jButtonBishop.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.bishopActionPerformed(evt);
        });
        
        jButtonKnight.addActionListener((java.awt.event.ActionEvent evt) -> {
            controller.knightActionPerformed(evt);
        });

        pack();
    }
    
    private void initButton(Color color){
        jButtonQueen.setIcon(iconQueen);
        jButtonRook.setIcon(iconRook);
        jButtonBishop.setIcon(iconBishop);
        jButtonKnight.setIcon(iconKnight);
       
        jButtonQueen.setBackground(color);
        jButtonRook.setBackground(color);
        jButtonBishop.setBackground(color);
        jButtonKnight.setBackground(color);
        
        jButtonQueen.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRook.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBishop.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonKnight.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
                        
    }
    
}
