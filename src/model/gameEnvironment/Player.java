package model.gameEnvironment;

import model.gameEnvironment.Chessboard;
import model.functionality.Position;
import java.util.ArrayList;
import java.util.Timer;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;

public class Player {
    private String name;
    private boolean color;  /* white = true, black = false */
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> cementery;
    private ArrayList<Position> history;
    private Timer timer;
    
    public Player(String name, boolean color){
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<Piece>();
        this.cementery = new ArrayList<Piece>();
        this.history = new ArrayList<Position>();
        this.timer = new Timer();
    }
    
    /* Metodo volto alla creazione dei pezzi del giocatore */
    public void createPieces(Chessboard chessboard){
        
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public ArrayList<Piece> getCementery() {
        return cementery;
    }

    public void setCementery(ArrayList<Piece> cementery) {
        this.cementery = cementery;
    }

    public ArrayList<Position> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Position> history) {
        this.history = history;
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

    public void setColor(boolean color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
