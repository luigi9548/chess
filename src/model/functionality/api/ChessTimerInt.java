/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.functionality.api;

import java.util.Timer;

/**
 *
 * @author luigi
 */
public interface ChessTimerInt {

    //metodo che formatta i millisecondi in minuti e secondi
    String formatTime(long time);

    //metodo che termina il timer
    void stopTimer();

    //metodo che cambia il turno del giocatore
    void switchTurn();
    
}
