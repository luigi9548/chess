import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessTimerWithButtons extends JFrame {
    private Timer timer;
    private long timeLimit;             //tempo limite
    private boolean isPlayer1Turn;      //boolean del turno del giocatore
    private long player1RemainingTime;  //tempo in minuti e secondi rimanenti del primo giocatore
    private long player2RemainingTime;  //tempo in minuti e secondi rimanenti del secondo giocatore

    private JButton switchTurnButton;   //pulsante di switch del turno giocatore
    private JLabel timerLabel;          //label che mostra il tempo rimanente del giocatore corrente

    public ChessTimerWithButtons(long timeLimit) {
        this.timeLimit = timeLimit;
        this.isPlayer1Turn = true;
        this.player1RemainingTime = timeLimit;
        this.player2RemainingTime = timeLimit;

        setTitle("Chess Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timerLabel = new JLabel(formatTime(player1RemainingTime));
        add(timerLabel);

        switchTurnButton = new JButton("Switch Turn");
        switchTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchTurn();
            }
        });
        add(switchTurnButton);

        pack();
        setVisible(true);

        startTimer();
    }

    //metodo volto ad iniziare il countdown del giocatore corrente
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isPlayer1Turn) {
                    player1RemainingTime -= 1000; //viene sottratto un secondo dal totale
                    if (player1RemainingTime <= 0) {
                        //se il tempo termina allora viene invocato il metodo che termina il timer
                        timerLabel.setText("Player 1 ran out of time!");
                        stopTimer();
                    } else {
                        //aggiornamento del timer
                        timerLabel.setText(formatTime(player1RemainingTime));
                    }
                } else {
                    player2RemainingTime -= 1000; // Subtract one second
                    if (player2RemainingTime <= 0) {
                        timerLabel.setText("Player 2 ran out of time!");
                        stopTimer();
                    } else {
                        timerLabel.setText(formatTime(player2RemainingTime));
                    }
                }
            }
        }, 1000, 1000);
    }

    //metodo che termina il timer
    private void stopTimer() {
        timer.cancel();
        switchTurnButton.setEnabled(false);
    }

    //metodo che cambia il turno del giocatore
    private void switchTurn() {
        isPlayer1Turn = !isPlayer1Turn;
        switchTurnButton.setText(isPlayer1Turn ? "Player 1 Turn" : "Player 2 Turn");
    }

    //metodo che formatta i millisecondi in minuti e secondi
    private String formatTime(long time) {
        long minutes = (time / 1000) / 60;
        long seconds = (time / 1000) % 60;
        
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChessTimerWithButtons(1200 * 1000);
            }
        });
    }
}