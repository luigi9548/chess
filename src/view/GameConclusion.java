package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GameConclusion extends JFrame {
    private final String message;
    private final GameView gameView;
    private JLabel title;
    private JLabel text;
    private JButton quit;
    private JButton newMatch;
    
    public GameConclusion(final String message, GameView gameView) {
        this.message = message;
        this.gameView = gameView;
        initComponents();
    }
    
    public GameView getGameView(){
        return this.gameView;
    }
        
    private void initComponents() {
        title = new JLabel("Fine del gioco");
        text = new JLabel(this.message);
        quit = new JButton("Quit");
        newMatch = new JButton("New match");

        getContentPane().setBackground(new Color(218, 189, 171));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setLocation(100, 70);

        
        quit.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));  
        newMatch.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));  
        
        title.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14));
        title.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
                    .addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(quit)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newMatch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(quit)
                    .addComponent(newMatch))
                .addContainerGap())
        );
        
        quit.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.quitActionPerformed(evt);
        });
        
        newMatch.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.newMatchActionPerformed(evt);
        });     
        
        setTitle("Game Conclusion");
        pack();
    }

    public void quitActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    public void newMatchActionPerformed(ActionEvent evt) {
        this.getGameView().setVisible(false);
        this.setVisible(false);
        
        Menu menu = new Menu();
        menu.setVisible(true);
        
    }
}
