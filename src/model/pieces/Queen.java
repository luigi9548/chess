package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
                
        //riga
        for(int i = Chessboard.ROW_LOWER_LIMIT; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            if(i != row)
                possiblePositions.add(new Position(i,col));
        }
        
        //colonna
        for(int i = Chessboard.COL_LOWER_LIMIT; i <= Chessboard.COL_UPPER_LIMIT; i++){
            if(i != col)
                possiblePositions.add(new Position(row,i));
        }
        
        //diagonale colonne verso dx
        int upRow = row;
        int downRow = row;
        for(int i = col; i <= Chessboard.COL_UPPER_LIMIT; i++){
            if(i != col){
                if(upRow <= Chessboard.ROW_UPPER_LIMIT)
                    possiblePositions.add(new Position(upRow,i));
                
                if(downRow >= Chessboard.ROW_LOWER_LIMIT)
                    possiblePositions.add(new Position(downRow,i));
            }
            upRow++;
            downRow--;
        }
        
        //diagonale colonne verso sx
        upRow = row;
        downRow = row;
        for(int i = col; i >= Chessboard.COL_LOWER_LIMIT; i--){
            if(i != col){
                if(upRow <= Chessboard.ROW_UPPER_LIMIT)
                    possiblePositions.add(new Position(upRow,i));
                
                if(downRow >= Chessboard.ROW_LOWER_LIMIT)
                    possiblePositions.add(new Position(downRow,i));
            }
            upRow++;
            downRow--;
        }
         
        return possiblePositions;
    }
}
