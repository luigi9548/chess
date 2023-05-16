package model.pieces;

import model.pieces.Piece;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        while(this.getChessboard().isValidPosition(++row,col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }
        row = this.getPosition().getRow(); col = this.getPosition().getCol();
        
        while(this.getChessboard().isValidPosition(--row,col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }        
        row = this.getPosition().getRow(); col = this.getPosition().getCol();
        
        while(this.getChessboard().isValidPosition(row,--col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }        
        row = this.getPosition().getRow(); col = this.getPosition().getCol();
        
        while(this.getChessboard().isValidPosition(row,++col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                possiblePositions.add(new Position(row,col));
            }else if(this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get())){
                possiblePositions.add(new Position(row,col));
                break;
            }else if(!this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()))
                break;
        }
        

        // movimento torre è indifferente sia per nero sia per bianco, si muove lungo orizzontale e verticale
        // riga
        /*  for(int i = Chessboard.ROW_LOWER_LIMIT; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            if(i != row)
                possiblePositions.add(new Position(i,col));
        }
        
        //colonna
        for(int i = Chessboard.COL_LOWER_LIMIT; i <= Chessboard.COL_UPPER_LIMIT; i++){
            if(i != col)
                possiblePositions.add(new Position(row,i));
        }*/ 
        
        // secondo tentativo
        /*
        while(++row <= Chessboard.ROW_UPPER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
            possiblePositions.add(new Position(row, col));
            System.out.println(row + " " + col);
        }
            
        
        row = this.getPosition().getRow();
        
        while(--row >= Chessboard.ROW_LOWER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty())
            possiblePositions.add(new Position(row, col));
        
        row = this.getPosition().getRow();
        
        while(++col <= Chessboard.COL_UPPER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty())
            possiblePositions.add(new Position(row, col));
        
        col = this.getPosition().getCol();
        
        while(--col >= Chessboard.COL_LOWER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty())
            possiblePositions.add(new Position(row, col));*/
        
        return possiblePositions;
    }
}
