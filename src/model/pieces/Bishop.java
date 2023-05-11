package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.*;

public class Bishop extends Piece {
    public Bishop(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
    }

    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();

        while(this.getChessboard().isValidPosition(++row,++col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }
        row = this.getPosition().getRow(); col = this.getPosition().getCol();
        
        while(this.getChessboard().isValidPosition(--row,++col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }        
        row = this.getPosition().getRow(); col = this.getPosition().getCol();
        
        while(this.getChessboard().isValidPosition(++row,--col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }        
        row = this.getPosition().getRow(); col = this.getPosition().getCol();
        
        while(this.getChessboard().isValidPosition(--row,--col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }        
  
        /*while(++row <= Chessboard.ROW_UPPER_LIMIT  && ++col <= Chessboard.COL_UPPER_LIMIT){
           // System.out.println(this.getChessboard().getSquare(0,0 ).getPiece().get().isEnemy(this));
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty())
                possiblePositions.add(new Position(row, col));
            else if(this.getChessboard().getSquare(row, col).getPiece().get().isEnemy(this)){
                possiblePositions.add(new Position(row, col));
            }
        }
        
        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(--row >= Chessboard.ROW_LOWER_LIMIT  && --col >= Chessboard.COL_LOWER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty() || this.getChessboard().getSquare(row, col).getPiece().get().isEnemy(this))
                possiblePositions.add(new Position(row, col));
        }
        
        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(--row >= Chessboard.ROW_LOWER_LIMIT  && ++col <= Chessboard.COL_UPPER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty() || this.getChessboard().getSquare(row, col).getPiece().get().isEnemy(this))
                possiblePositions.add(new Position(row, col));
        }

        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(++row <= Chessboard.ROW_UPPER_LIMIT  && --col >= Chessboard.COL_LOWER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty() || this.getChessboard().getSquare(row, col).getPiece().get().isEnemy(this))
                possiblePositions.add(new Position(row, col));
        }*/

        
        return possiblePositions;
    }
    

}
