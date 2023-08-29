/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.gameEnvironment.impl;

import model.functionality.impl.ChessTimer;

/**
 *
 * @author Elena
 */
public class Match {
    private final Chessboard chessboard;  
    private final Player whiteP;
    private final Player blackP;
    private final ChessTimer timer;
    
    public Match(Chessboard chessboard, Player whiteP, Player blackP, ChessTimer timer){
        this.chessboard = chessboard;
        this.whiteP = whiteP;
        this.blackP = blackP;
        this.timer = timer;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public Player getWhiteP() {
        return whiteP;
    }

    public Player getBlackP() {
        return blackP;
    }
    
    public ChessTimer getTimer(){
        return this.timer;
    }
    
    
    
}
