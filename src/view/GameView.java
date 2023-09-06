package view;
import controller.impl.ControllerGameView;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;

public final class GameView extends JFrame {
    
    private final JPanel chessboard = new JPanel();
    private final JButton[][] buttonGrid = new JButton[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
    private final ControllerGameView controller = ControllerGameView.getInstance(this);
    private final JLabel jLabelCemeteryBlack = new JLabel();
    private final JLabel jLabelCemeteryWhite = new JLabel();
    private final JLabel jLabelWhiteN = new JLabel();
    private final JLabel jLabelBlackN = new JLabel();
    private final JLabel timerPlayerW = new JLabel();
    private final JLabel timerPlayerB = new JLabel();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JTextArea history = new JTextArea();

    public GameView(final String whiteN,final String blackN){
        init(whiteN, blackN);
        initChessboard();
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
    
    public JLabel getTimerPlayerW(){
        return this.timerPlayerW;
    }
    
    public JLabel getTimerPlayerB(){
        return this.timerPlayerB;
    }
    public ControllerGameView getController(){
        return this.controller;
    }
    
    public void init(String whiteN, String blackN){
        this.jLabelCemeteryBlack.setFont(new Font("SansSerif", 0, 40));
        this.jLabelCemeteryWhite.setFont(new Font("SansSerif", 0, 40));
        this.jLabelWhiteN.setFont(new Font("SansSerif", 1, 25));
        this.jLabelBlackN.setFont(new Font("SansSerif", 1, 25));
        this.timerPlayerW.setFont(new Font("SansSerif", 1, 25));
        this.timerPlayerB.setFont(new Font("SansSerif", 1, 25));
        this.jLabelWhiteN.setText(whiteN);
        this.jLabelBlackN.setText(blackN);
        this.timerPlayerW.setText("10:00");
        this.timerPlayerB.setText("10:00");
        history.setEditable(false);
        history.setColumns(20);
        history.setText("");
        history.append("                Bianco \t\tNero");
        history.setRows(5);
        jScrollPane1.setViewportView(history);
        
        chessboard.setPreferredSize(new java.awt.Dimension(600, 600));
        GroupLayout scacchieraLayout = new GroupLayout(chessboard);
        chessboard.setLayout(scacchieraLayout);
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
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelBlackN, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(timerPlayerB, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelCemeteryBlack, GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                                .addComponent(jLabelCemeteryWhite, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelWhiteN, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(timerPlayerW, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chessboard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelWhiteN, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(timerPlayerW, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelCemeteryBlack, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(chessboard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCemeteryWhite, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBlackN, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(timerPlayerB, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game");
        setVisible(true);
        pack();
    }

    /**
     * Initializes the chessboard UI, setting up buttons and their backgrounds.
     */
    public void initChessboard(){  
        this.chessboard.setLayout(new GridLayout(8,8));
        this.chessboard.setSize(600,600);
        for (int i=0; i<=7; i++)
            for (int j=0; j<=7; j++) {
                JButton b = new JButton();
                b.setSize(50, 50);
                if ((i+j)%2==0)
                    b.setBackground(new Color(255, 250, 219));
                else
                    b.setBackground(new Color(156, 92, 8));
        
                if(i == 0 || i == 1 || i == 6 || i == 7){
                    Icon icon = new ImageIcon(controller.getMatch().getChessboard().getSquare(i, j).getPiece().get().getIcon());
                    b.setIcon(icon);
                }
                buttonGrid[i][j] = b;
                b.addActionListener((java.awt.event.ActionEvent evt) -> {
                    controller.actions(evt, b.getBackground().getRGB());
                });
                this.chessboard.add(b);
            }
    }
    
    
    /**
    * Resets the background colors of all chessboard buttons to their original alternating colors.
    */
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
        
    /**
    * Changes the background color of buttons corresponding to the given positions to red.
    * 
    * @param positions The positions on the chessboard to change the button color for.
    */
    public void changeBottonColor(ArrayList<Position> positions){
        for(Position p : positions){
            this.getButtonGrid(p.getRow(), p.getCol()).setBackground(Color.red);
        }
    }
    
    /**
    * Updates the icon on the buttons at the given positions.
    * 
    * @param row The row of the button to update.
    * @param col The column of the button to update.
    * @param newRow The new row of the button to update.
    * @param newCol The new column of the button to update.
    * @param icon The new icon to set on the button.
    */
    public void updateIcon(int row, int col, int newRow, int newCol, Icon icon) {
        this.getButtonGrid(newRow, newCol).setIcon(icon);
        this.getButtonGrid(row, col).setIcon(null);  
    }
}
