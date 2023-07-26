package model.gameEnvironment;

import java.util.ArrayList;
import java.util.Timer;
import model.functionality.ColorM;
import model.pieces.Piece;

public class Player {
    private String name;
    private ColorM color;  /* white = true, black = false */
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> cementery;
    private ArrayList<String> history;
    private Timer timer;
    
    public Player(String name, ColorM color){
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<>();
        this.cementery = new ArrayList<>();
        this.history = new ArrayList<>();
        this.timer = new Timer();
    }
    
    /* Metodo volto all' assegnazione dei pezzi del giocatore */
    public void createPieces(Chessboard chessboard){
        /* I pezzi vengono assegnati a seconda del colore del Player */
        if(this.color == ColorM.BIANCO){
            this.pieces = chessboard.getPiecesByColor(0);
        }else{
            this.pieces = chessboard.getPiecesByColor(1);
        }
    }
    
    /* Metodo volto all'aggiunta di una mossa alla history delle mosse */
    public void addToHistory(String str){
        if(!str.equals("")){
            this.history.add(str);
        }
    }
    
    public ArrayList<String> getHistory(){
        return this.history;
    }
    
    public void removeLastString(){
        this.history.remove(this.history.size()-1);
    }
    
    /* Metodo volto all'aggiunta di un pezzo nel cimitero */
    public void addPieceCemetery(Piece piece){
        this.cementery.add(piece);
    }
    
    public ArrayList<Piece> getCemetery(){
        return this.cementery;
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

    public ColorM isColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
