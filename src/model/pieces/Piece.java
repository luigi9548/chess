package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public abstract class Piece implements PieceInterface {
    private final char pieceSign;
    private Position position;
    private final int color;
    private final Chessboard chessboard;
    private String icon;
    private boolean inAction = false;
    
    public Piece(Position position,final int color,final Chessboard chessboard,final char pieceSign){
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
    
    public int getColor(){
        return this.color;
    }
    
    public char getPieceSign() {
        return pieceSign;
    }
    @Override
    public abstract ArrayList<Position> calculateMovement();
}
