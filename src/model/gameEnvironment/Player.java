package model.gameEnvironment;

import model.gameEnvironment.Chessboard;
import model.functionality.Position;
import java.util.ArrayList;
import java.util.Timer;
import model.pieces.Piece;

public class Player {
    private String name;
    private boolean color;  /* white = true, black = false */
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> cementery;
    private ArrayList<String> history;
    private Timer timer;
    
    public Player(String name, boolean color){
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<>();
        this.cementery = new ArrayList<>();
        this.history = new ArrayList<>();
        this.timer = new Timer();
    }
    
    /* Metodo volto all' assegnazione dei pezzi del giocatore */
    public void createPieces(Chessboard chessboard){
        if(this.color){
            
        }else{
            
        }
    }
    
    private void addToHistory(String str){
        if(!str.equals("")){
            this.history.add(str);
        }
    }
    
    private void addPieceCemetery(Piece piece){
        
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
