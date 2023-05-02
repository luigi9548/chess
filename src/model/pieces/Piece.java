package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public abstract class Piece implements PieceInterface {
    private final String name;
    private boolean isInPlay;
    private Position position;
    private final int color;
    private ArrayList<Position> lastMovePath;
    private Chessboard chessboard;

    private String icon;
    
    public Piece(final String name, Position position,final int color, Chessboard chessboard){
        this.name = name;
        this.position = position;
        this.color = color;
        this.isInPlay = true;
        this.chessboard = chessboard;
    }
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
    
    @Override
    public abstract ArrayList<Position> calculateMovement(Position position);
}
