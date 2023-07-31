package model.functionality;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessTimer{
    private Timer timer;
    private long timeLimit;             //tempo limite
    private boolean isPlayer1Turn;      //boolean del turno del giocatore
    private long player1RemainingTime;  //tempo in minuti e secondi rimanenti del primo giocatore
    private long player2RemainingTime;  //tempo in minuti e secondi rimanenti del secondo giocatore

    public ChessTimer(long timeLimit) {
        timer = new Timer();
        this.timeLimit = timeLimit;
        this.isPlayer1Turn = true;
        this.player1RemainingTime = timeLimit;
        this.player2RemainingTime = timeLimit;
    }
    
    public long getPlayer1RemainingTime(){
        return this.player1RemainingTime;
    }
    
    public long getPlayer2RemainingTime(){
        return this.player2RemainingTime;
    }
    
    public void setPlayer1RemainingTime(long player1RemainingTime){
        this.player1RemainingTime = player1RemainingTime;
    }
    
    public void setPlayer2RemainingTime(long player2RemainingTime){
        this.player2RemainingTime = player2RemainingTime;
    }
    public Timer getTimer(){
        return this.timer;
    }
    
    public boolean getIsPlayer1Turn(){
        return this.isPlayer1Turn;
    }
    
    //metodo che termina il timer
    public void stopTimer() {
        timer.cancel();
    }

    //metodo che cambia il turno del giocatore
    public void switchTurn() {
        isPlayer1Turn = !isPlayer1Turn;
    }

    //metodo che formatta i millisecondi in minuti e secondi
    public String formatTime(long time) {
        long minutes = (time / 1000) / 60;
        long seconds = (time / 1000) % 60;
        
        return String.format("%02d:%02d", minutes, seconds);
    }
}