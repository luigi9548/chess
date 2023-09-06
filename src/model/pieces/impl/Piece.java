package model.pieces.impl;

import model.pieces.api.PieceInterface;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import model.enumerations.ColorChessboardEnum;

public abstract class Piece implements PieceInterface {
    private final char pieceSign;        // The character representing the type of chess piece (e.g., 'K' for King, 'Q' for Queen).
    private Position position;
    private final ColorChessboardEnum color;
    private final Chessboard chessboard;
    private String icon;
    private boolean inAction = false;
    
    public Piece(Position position,final ColorChessboardEnum color,final Chessboard chessboard,final char pieceSign){
        this.position = position;
        this.color = color;
        this.chessboard = chessboard;
        this.pieceSign = pieceSign;   
    }
    
    public boolean isInAction() {
        return inAction;
    }
    
    public boolean isEnemy(Piece p){
        return p.getColor() != this.color;
    }

    public void setInAction(boolean inAction) {
        this.inAction = inAction;
    }
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public void setPosition(Position position){
        this.position = position;
    }
    
    public Position getPosition() {
        return this.position;
    }
    
    protected Chessboard getChessboard(){
        return this.chessboard;
    }
    
    public ColorChessboardEnum getColor(){
        return this.color;
    }
    
    public char getPieceSign() {
        return pieceSign;
    }
    
    /**
    * Swaps the chess piece symbol with a corresponding Unicode chess piece character.
    * This method is used to convert chess piece symbols for displaying on the cemetery.
    *
    * @return The Unicode character representing the chess piece.
    */
    public String pieceSwap(){
        String str = new String();
        
        char piecesLowerCase[]={'p','k','q','b','h','r'};
        char swapLowerCase[]={'♙','♔','♕','♗','♘','♖'};
        char swapUpperCase[]={'♟','♚','♛','♝','♞','♜'};
        if(Character.isLowerCase(pieceSign)){
            for (int i = 0; i < 6; i++)
                if(pieceSign == piecesLowerCase[i])str += swapLowerCase[i];
        }else{
            char lowercased = Character.toLowerCase(pieceSign);
            for (int i = 0; i < 6; i++) 
                if(lowercased == piecesLowerCase[i])str += swapUpperCase[i];
        }
        return str;
    }
        
    @Override
    public abstract ArrayList<Position> calculateMovement();
}
