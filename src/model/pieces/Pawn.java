package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean firstMove;
    
    public Pawn(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
        this.firstMove = true;
    }
    
    private boolean isFirstMove(){
        return this.firstMove;
    }
    
    public void switchFirstMove(){
        if(this.firstMove){
            this.firstMove = false;
        }
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        if(col + 1 != Chessboard.COL_UPPER_LIMIT)
            possiblePositions.add(new Position(row, col + 1));
        
        if(this.isFirstMove()){
            possiblePositions.add(new Position(row, col + 2));
            this.switchFirstMove();
        }
        return possiblePositions;
    }
}
