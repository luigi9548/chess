/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.gameEnvironment.impl;

import model.gameEnvironment.api.MatchInt;
import model.functionality.impl.ChessTimer;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.pieces.impl.Pawn;
import model.pieces.impl.Piece;

/**
 *
 * @author Elena
 */
public class Match implements MatchInt {
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

    public Player getPlayer(ColorChessboard color){
        return (color == ColorChessboard.WHITE)? this.whiteP : this.blackP;
    }
    
    public ChessTimer getTimer(){
        return this.timer;
    }
    
    @Override
    public String calculateHistory(boolean isEnPassant, Piece p, int row, int col){
        boolean hasEaten = false;
        String history;
        
        // se è avvenuto enPassant aggiungo nella fine della stringa "e.p."
        if(isEnPassant){
            hasEaten = true;
            if(p.getColor() == ColorChessboard.WHITE)
                history = p.getPosition().numToLetterBySubstr() + "x" + new Position(row,col).getStringPosition() + " e. p.";
            else
                history = p.getPosition().numToLetterBySubstr() + "x" + this.chessboard.enPassant((Pawn) p).getStringPosition() + " e. p."; 
        }else{
            if(this.chessboard.getSquare(row, col).getPiece().isPresent())
                hasEaten = true;
                
            history = this.moveString(p.getPosition(), new Position(row, col), hasEaten, p.getPieceSign());   
        }
        return history;
    }
    
    private String moveString(Position initial, Position finalP, boolean hasEaten, char type){
        String move;
        char typeUpper = Character.toUpperCase(type);
        if(typeUpper == 'P'){ // se è un pedone non devo scrivere il segno P davanti alle coordinate
            if(hasEaten)
                move = initial.getStringPosition() + "x" + finalP.getStringPosition();
            else
                move = initial.getStringPosition() + " - " + finalP.getStringPosition();
        }else{
            if(hasEaten)
                move = typeUpper + initial.getStringPosition() + "x" + finalP.getStringPosition();
            else
                move = typeUpper + initial.getStringPosition() + " - " + finalP.getStringPosition();
        }
        return move;
    }
    
    public Position turnHandler(Piece p, int row, int col){
        if(this.chessboard.getTurn() == 0)
            this.chessboard.changeEnPassant(ColorChessboard.WHITE);
        else
            this.chessboard.changeEnPassant(ColorChessboard.BLACK);

        boolean isEnPassant = false;

        // configuro pedone per: enPassant, prima mossa, promotion
        if(p instanceof Pawn pawn)
            isEnPassant = this.chessboard.configurePawn(pawn, row, col);
        
        if(isEnPassant){
            Position pos = this.chessboard.enPassant((Pawn) p);
            row = pos.getRow();
            col = pos.getCol();
        }
        
        // determino cronologia
        String history = this.calculateHistory(isEnPassant, p, row, col);
        
        if(this.chessboard.getTurn() == 0){
            this.whiteP.addToHistory(history);
            if(this.chessboard.getSquare(row, col).getPiece().isPresent()){
                this.blackP.addPieceCemetery(this.chessboard.getSquare(row, col).getPiece().get());
            }
        }else{
            this.blackP.addToHistory(history);
            if(this.chessboard.getSquare(row, col).getPiece().isPresent())
                this.whiteP.addPieceCemetery(this.chessboard.getSquare(row, col).getPiece().get());
        }
        
        if(isEnPassant){
            return new Position(row, col);
        }else
            return null;
    }
    
    public void checkHandler(){
        String checkString;
        if(this.chessboard.isCheck(ColorChessboard.BLACK)){
            checkString = this.whiteP.getHistory().get(this.whiteP.getHistory().size() - 1).concat("+");
            this.whiteP.removeLastString();
            this.whiteP.addToHistory(checkString);
        }else if(this.chessboard.isCheck(ColorChessboard.WHITE)){
            checkString = this.blackP.getHistory().get(this.blackP.getHistory().size() - 1).concat("+");
            this.blackP.removeLastString();
            this.blackP.addToHistory(checkString);
        }
    }
    
}
