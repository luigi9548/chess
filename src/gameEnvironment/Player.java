package gameEnvironment;

import functionality.Position;
import java.util.ArrayList;
import java.util.Timer;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

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
    public void createPieces(){
        /* Creazione pedoni */
        for(int i = 0; i < 8; i++){
            this.pieces.add(new Pawn("Pedone_" + i, (new Position((this.color ? 1 : 6), i)), (this.color ? 1 : 0), new Chessboard()));
        }
        
        /* Creazione torri */
        for(int i = 0; i < 2; i++){
            if(this.color){
                this.pieces.add(new Rook("Torre_" + i, new Position(0, i == 0 ? 0 : 7), 1, new Chessboard()));
            }else{
                this.pieces.add(new Rook("Torre_" + i, new Position(7, i == 0 ? 0 : 7), 0, new Chessboard()));
            }
        }
        
        /* Creazione cavalli */
        for(int i = 0; i < 2; i++){
            if(this.color){
                this.pieces.add(new Knight("Cavallo_" + i, new Position(0, i == 0 ? 1 : 6), 1, new Chessboard()));
            }else{
                this.pieces.add(new Knight("Cavallo_" + i, new Position(7, i == 0 ? 1 : 6), 0, new Chessboard()));
            }
        }
        
        /* Creazione alfieri */
        for(int i = 0; i < 2; i++){
            if(this.color){
                this.pieces.add(new Bishop("Alfiere_" + i, new Position(0, i == 0 ? 2 : 5), 1, new Chessboard()));
            }else{
                this.pieces.add(new Bishop("Alfiere_" + i, new Position(7, i == 0 ? 2 : 5), 0, new Chessboard()));
            }
        }
        
        /* Creazione regina */
        if(this.color){
            this.pieces.add(new Queen("Regina_0", new Position(0, 3), 1, new Chessboard()));
        }else{
            this.pieces.add(new Queen("Regina_0", new Position(7, 3), 0, new Chessboard()));
        }
        
        /* Creazione re */
        if(this.color){
            this.pieces.add(new King("Re_0", new Position(0, 4), 1, new Chessboard()));
        }else{
            this.pieces.add(new King("Re_0", new Position(7, 4), 0, new Chessboard()));
        }
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
