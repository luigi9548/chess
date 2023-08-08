package view;

import java.awt.Color;
import javax.swing.*;

public class GameConclusion extends JFrame {
    private final String message;
    private JLabel title;
    private JLabel text;
    
    public GameConclusion(final String message) {
        this.message = message;
        initComponents();
    }
    
    private void initComponents() {
        title = new JLabel("Fine del gioco");
        text = new JLabel(this.message);


        getContentPane().setBackground(new Color(218,189,171));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setLocation(100,70);

        title.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        title.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
               
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
                    )
        ));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                )
        );
        pack();
    }
}
