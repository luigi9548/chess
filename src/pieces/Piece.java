package pieces;

import functionality.Position;
import gameEnvironment.Chessboard;
import java.util.ArrayList;

public abstract class Piece implements PieceInterface {
    private final String name;
    private boolean isInPlay;
    private Position position;
    private final int color;
    private ArrayList<Position> lastMovePath;
    private Chessboard chessboard;
    
    public Piece(final String name, Position position,final int color, Chessboard chessboard){
        this.name = name;
        this.position = position;
        this.color = color;
        this.isInPlay = true;
        this.chessboard = chessboard;
    }
    
    protected Position getPosition() {
        return this.position;
    }
    
    @Override
    public abstract ArrayList<Position> calculateMovement(Position position);
}
