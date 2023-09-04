package model.gameEnvironment.impl;

import model.gameEnvironment.api.PlayerInt;
import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.pieces.impl.Piece;

public class Player implements PlayerInt {
    
    private final ColorChessboard color; 
    private final ArrayList<Piece> cemetery;
    private final ArrayList<String> history;
    
    public Player(final ColorChessboard color){
        this.color = color;
        this.cemetery = new ArrayList<>();
        this.history = new ArrayList<>();
    }
    
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
    
    @Override
    public void addPieceCemetery(Piece piece){
        this.cemetery.add(piece);
    }
    
    public ArrayList<Piece> getCemetery(){
        return this.cemetery;
    }

    public ColorChessboard getColor() {
        return color;
    }
}
