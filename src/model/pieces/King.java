package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class King extends Piece {
    
    public King(final String name, Position position,final int color, Chessboard chessboard, char pieceSign){
        super(name, position, color, chessboard, pieceSign);
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        if(this.isPossiblePosition(row + 1,col))
                possiblePositions.add(new Position(row + 1,col));
        
        if(this.isPossiblePosition(row - 1,col))
                possiblePositions.add(new Position(row - 1,col));
        
        if(this.isPossiblePosition(row,col + 1))
                possiblePositions.add(new Position(row,col + 1));
        
        if(this.isPossiblePosition(row,col - 1))
                possiblePositions.add(new Position(row,col -1));
        
        if(this.isPossiblePosition(row + 1,col + 1))
                possiblePositions.add(new Position(row + 1,col + 1));
        
        if(this.isPossiblePosition(row - 1, col + 1))
                possiblePositions.add(new Position(row - 1,col + 1));
        
        if(this.isPossiblePosition(row + 1, col - 1))
                possiblePositions.add(new Position(row +1,col - 1));
        
        if(this.isPossiblePosition(row - 1, col -1))
                possiblePositions.add(new Position(row - 1,col - 1));
        
        
        /*if(row + 1 <= Chessboard.ROW_UPPER_LIMIT)
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
            possiblePositions.add(new Position(row + 1, col - 1));*/
        
        return possiblePositions;
    }
    
    private boolean isPossiblePosition(int row, int col){
        boolean ret = false;
        
        if(this.getChessboard().isValidPosition(row, col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()|| this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                ret = true;
            }
        }
        return ret;
    }
}
