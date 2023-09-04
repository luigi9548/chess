package model.functionality.impl;

import model.functionality.api.ChessTimerInt;
import java.util.Timer;

public class ChessTimer implements ChessTimerInt{
    private final Timer timer;
    private boolean isPlayerWTurn;      // Boolean indicating the turn of the white player (true for player white's turn, false for player black's turn)
    private long playerWRemainingTime;  // Remaining time in minutes and seconds for the white player
    private long playerBRemainingTime;  // Remaining time in minutes and seconds for the black player

    public ChessTimer(final long timeLimit) {
        timer = new Timer();
        this.isPlayerWTurn = true;
        this.playerWRemainingTime = timeLimit;
        this.playerBRemainingTime = timeLimit;
    }
    
    public long getPlayerWRemainingTime(){
        return this.playerWRemainingTime;
    }
    
    public long getPlayerBRemainingTime(){
        return this.playerBRemainingTime;
    }
    
    public void setPlayerWRemainingTime(long playerWRemainingTime){
        this.playerWRemainingTime = playerWRemainingTime;
    }

    public void setPlayerBRemainingTime(long playerBRemainingTime){
        this.playerBRemainingTime = playerBRemainingTime;
    }

    public Timer getTimer(){
        return this.timer;
    }
    
    public boolean getIsPlayerWTurn(){
        return this.isPlayerWTurn;
    }
    
    @Override
    public void stopTimer() {
        timer.cancel();
    }

    @Override
    public void switchTurn() {
        isPlayerWTurn = !isPlayerWTurn;
    }

    @Override
    public String formatTime(long time) {
        long minutes = (time / 1000) / 60;
        long seconds = (time / 1000) % 60;
        
        return String.format("%02d:%02d", minutes, seconds);
    }
}