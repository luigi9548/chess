package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        if(row + 1 < Chessboard.ROW_UPPER_LIMIT + 1 && col + 2 < Chessboard.COL_UPPER_LIMIT + 1)
            possiblePositions.add(new Position(row + 1, col + 2));
        
        if(row + 1 < Chessboard.ROW_UPPER_LIMIT + 1 && col - 2 > Chessboard.COL_LOWER_LIMIT - 1)
            possiblePositions.add(new Position(row + 1, col - 2));
        
        if(row + 2 < Chessboard.ROW_UPPER_LIMIT + 1 && col + 1 < Chessboard.COL_UPPER_LIMIT + 1)
            possiblePositions.add(new Position(row + 2, col + 1)); 
        
        if(row + 2 < Chessboard.ROW_UPPER_LIMIT + 1 && col - 1 > Chessboard.COL_LOWER_LIMIT - 1)
            possiblePositions.add(new Position(row + 2, col - 1)); 
        
        if(row - 2 > Chessboard.ROW_LOWER_LIMIT - 1 && col + 1 < Chessboard.COL_UPPER_LIMIT + 1)
            possiblePositions.add(new Position(row - 2, col + 1)); 
        
        if(row - 2 > Chessboard.ROW_LOWER_LIMIT - 1 && col - 1 > Chessboard.COL_LOWER_LIMIT - 1)
            possiblePositions.add(new Position(row - 2, col - 1)); 
        
        if(row - 1 > Chessboard.ROW_LOWER_LIMIT - 1 && col - 2 > Chessboard.COL_LOWER_LIMIT - 1)
            possiblePositions.add(new Position(row - 1, col - 2)); 
        
        if(row - 1 > Chessboard.ROW_LOWER_LIMIT - 1 && col + 2 < Chessboard.COL_UPPER_LIMIT + 1)
            possiblePositions.add(new Position(row - 1, col + 2)); 
        
        return possiblePositions;
    }
}
