package pieces;

import functionality.Position;
import gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        while(++row != Chessboard.ROW_UPPER_LIMIT)
            possiblePositions.add(new Position(row, col));
        
        row = this.getPosition().getRow();
        
        while(--row != Chessboard.ROW_LOWER_LIMIT)
            possiblePositions.add(new Position(row, col));
        
        while(++col != Chessboard.COL_UPPER_LIMIT)
            possiblePositions.add(new Position(row, col));
        
        col = this.getPosition().getCol();
        
        while(--col != Chessboard.COL_LOWER_LIMIT)
            possiblePositions.add(new Position(row, col));
        
        while(++row != Chessboard.ROW_UPPER_LIMIT  && ++col != Chessboard.COL_UPPER_LIMIT)
            possiblePositions.add(new Position(row, col));
        
        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(--row != Chessboard.ROW_LOWER_LIMIT  && --col != Chessboard.COL_LOWER_LIMIT)
            possiblePositions.add(new Position(row, col));
        
        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(--row != Chessboard.ROW_LOWER_LIMIT  && ++col != Chessboard.COL_UPPER_LIMIT)
            possiblePositions.add(new Position(row, col));

        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(++row != Chessboard.ROW_UPPER_LIMIT  && --col != Chessboard.COL_LOWER_LIMIT)
            possiblePositions.add(new Position(row, col));
        return possiblePositions;
    }
}
