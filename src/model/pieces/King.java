package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class King extends Piece {
    
    public King(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        if(row + 1 <= Chessboard.ROW_UPPER_LIMIT)
            possiblePositions.add(new Position(row + 1, col));
        
        if(row - 1 >= Chessboard.ROW_LOWER_LIMIT)
            possiblePositions.add(new Position(row - 1, col));
        
        if(col + 1 <= Chessboard.COL_UPPER_LIMIT)
            possiblePositions.add(new Position(row, col + 1));
            
        if(col - 1 >= Chessboard.COL_LOWER_LIMIT)
            possiblePositions.add(new Position(row, col - 1));
        
        if(row + 1 <= Chessboard.ROW_UPPER_LIMIT  && col + 1 <= Chessboard.COL_UPPER_LIMIT)
            possiblePositions.add(new Position(row + 1, col + 1));
              
        if(row - 1 >= Chessboard.ROW_LOWER_LIMIT  && col - 1 >= Chessboard.COL_LOWER_LIMIT)
            possiblePositions.add(new Position(row - 1, col - 1));
        
        if(row - 1 >= Chessboard.ROW_LOWER_LIMIT  && col + 1 <= Chessboard.COL_UPPER_LIMIT)
            possiblePositions.add(new Position(row - 1, col + 1));
        
        if(row + 1 <= Chessboard.ROW_UPPER_LIMIT  && col - 1 >= Chessboard.COL_LOWER_LIMIT)
            possiblePositions.add(new Position(row + 1, col - 1));
        
        return possiblePositions;
    }
}
