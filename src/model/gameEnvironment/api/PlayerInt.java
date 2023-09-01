/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.gameEnvironment.api;

import model.pieces.impl.Piece;

/**
 *
 * @author luigi
 */
public interface PlayerInt {

    /**
     * Adds a piece to the player's cemetery (captured pieces collection).
     *
     * @param piece The piece to be added to the cemetery.
     */
    void addPieceCemetery(Piece piece);

    /**
     * Adds a move to the player's move history.
     *
     * @param str The move to be added to the history.
     */
    void addToHistory(String str);

    /**
     * Removes the last string from the move history.
     */
    void removeLastString();
    
}
