package model.functionality.api;

public interface ChessTimerInt {

    //metodo che formatta i millisecondi in minuti e secondi
    String formatTime(long time);

    //metodo che termina il timer
    void stopTimer();

    //metodo che cambia il turno del giocatore
    void switchTurn();
    
}
