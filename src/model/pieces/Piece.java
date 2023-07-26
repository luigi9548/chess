package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public abstract class Piece implements PieceInterface {
    private final String name;
    private char pieceSign;//p=peon,t=torre,c=caballo.....a,r=reina,k=king(rey) - mayusc:Negras
    private boolean isInPlay;
    private Position position;
    private final int color;
    private ArrayList<Position> lastMovePath;
    private Chessboard chessboard;
    private String icon;
    private boolean inAction = false;
    
    public Piece(final String name, Position position,final int color, Chessboard chessboard, char pieceSign){
        this.name = name;
        this.position = position;
        this.color = color;
        this.isInPlay = true;
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

    public void setPieceSign(char pieceSign) {
        this.pieceSign = pieceSign;
    }
    
    @Override
    public abstract ArrayList<Position> calculateMovement(Position position);
}
