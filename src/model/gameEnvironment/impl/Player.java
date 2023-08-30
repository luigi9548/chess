package model.gameEnvironment.impl;

import model.gameEnvironment.api.PlayerInt;
import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.pieces.impl.Piece;

public class Player implements PlayerInt {
    
    private final ColorChessboard color; 
    private final ArrayList<Piece> pieces;
    private final ArrayList<Piece> cemetery;
    private final ArrayList<String> history;
    
    public Player(final ColorChessboard color){
        this.color = color;
        this.pieces = new ArrayList<>();
        this.cemetery = new ArrayList<>();
        this.history = new ArrayList<>();
    }
    
    /* Metodo volto all'aggiunta di una mossa alla history delle mosse */
    @Override
    public void addToHistory(String str){
        if(!str.equals("")){
            this.history.add(str);
        }
    }
    
    public ArrayList<String> getHistory(){
        return this.history;
    }
    
    @Override
    public void removeLastString(){
        this.history.remove(this.history.size()-1);
    }
    
    /* Metodo volto all'aggiunta di un pezzo nel cimitero */
    @Override
    public void addPieceCemetery(Piece piece){
        this.cemetery.add(piece);
    }
    
    public ArrayList<Piece> getCemetery(){
        return this.cemetery;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public ColorChessboard getColor() {
        return color;
    }
}
