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

    /* Metodo volto all'aggiunta di un pezzo nel cimitero */
    void addPieceCemetery(Piece piece);

    /* Metodo volto all'aggiunta di una mossa alla cronologia delle mosse */
    void addToHistory(String str);

    // metodo per rimuovere l'ultima stringa dalla cronologia
    void removeLastString();
    
}
