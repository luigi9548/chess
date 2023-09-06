/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.gameEnvironment.impl;

import model.gameEnvironment.api.MatchInt;
import model.functionality.impl.ChessTimer;
import model.enumerations.ColorChessboardEnum;
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

    public Player getPlayer(ColorChessboardEnum color){
        return (color == ColorChessboardEnum.WHITE)? this.whiteP : this.blackP;
    }
    
    public ChessTimer getTimer(){
        return this.timer;
    }
    
    @Override
    public String calculateHistory(boolean isEnPassant, Piece p, int row, int col){
        boolean hasEaten = false;
        String history;
        
        // Check if there is an en passant capture.
        if(isEnPassant){
            hasEaten = true;
            history = p.getPosition().numToLetterBySubstr() + "x" + new Position(row,col).getStringPosition() + " e. p.";
        }else{
            // Check if a regolar capture has occured.
            if(this.chessboard.getSquare(row, col).getPiece().isPresent())
                hasEaten = true;
                
            history = this.moveString(p.getPosition(), new Position(row, col), hasEaten, p.getPieceSign());   
        }
        return history;
    }
    
    /**
    * Creates a string representing a move in a chess game.
    *
    * @param initial    The initial position of the piece.
    * @param finalP     The final position of the piece after the move.
    * @param hasEaten   True if a capture occurred during the move, otherwise false.
    * @param type       The type of piece making the move (e.g., 'P' for pawn, 'R' for rook, etc.).
    * @return           A string representing the move (e.g., "e4 - e5", "Bf0 - c3").
    */
    private String moveString(Position initial, Position finalP, boolean hasEaten, char type){
        String move;
        char typeUpper = Character.toUpperCase(type);
        
        // If it's a pawn, there's no need to include the piece type in the string. 
        if(typeUpper == 'P'){ 
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
    
    /**
    * Handles a player's turn, including updating the en passant status, configuring pawns,
    * recording move history, and handling captures and promotions.
    *
    * @param p    The piece being moved.
    * @param row  The target row for the piece's move.
    * @param col  The target column for the piece's move.
    * @return     The en passant position if en passant occurred, otherwise null.
    */
    public Position turnHandler(Piece p, int row, int col){
        // Update en passant status based on the current player's turn.
        if(this.chessboard.getTurn() == 0)
            this.chessboard.changeEnPassant(ColorChessboardEnum.WHITE);
        else
            this.chessboard.changeEnPassant(ColorChessboardEnum.BLACK);

        boolean isEnPassant = false;

        // Configure the pawn for en passant, first move, and promotion.
        if(p instanceof Pawn pawn)
            isEnPassant = this.chessboard.configurePawn(pawn, row, col);
        
        // determino cronologia
        String history = this.calculateHistory(isEnPassant, p, row, col);
        
        if(isEnPassant){
            // If en passant occurred, update the target row and column.
            Position pos = this.chessboard.enPassant((Pawn) p);
            row = pos.getRow();
            col = pos.getCol();
        }
                
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
            // Return the en passant position if en passant occurred, otherwise return null.
            return new Position(row, col);
        }else
            return null;
    }
    
    /**
     * Handles the check condition and updates the move history with a '+' symbol if the current player's king is in check.
     */
    public void checkHandler(){
        String checkString;
        if(this.chessboard.isCheck(ColorChessboardEnum.BLACK)){
            checkString = this.whiteP.getHistory().get(this.whiteP.getHistory().size() - 1).concat("+");
            this.whiteP.removeLastString();
            this.whiteP.addToHistory(checkString);
        }else if(this.chessboard.isCheck(ColorChessboardEnum.WHITE)){
            checkString = this.blackP.getHistory().get(this.blackP.getHistory().size() - 1).concat("+");
            this.blackP.removeLastString();
            this.blackP.addToHistory(checkString);
        }
    }
    
}
