package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(final String name, Position position,final int color, Chessboard chessboard, char pieceSign){
        super(name, position, color, chessboard, pieceSign);
    }

    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        //diagonali
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
        row = this.getPosition().getRow(); col = this.getPosition().getCol();
        
        //righe e colonne
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
        
        
                
        //riga
        /* for(int i = Chessboard.ROW_LOWER_LIMIT; i <= Chessboard.ROW_UPPER_LIMIT; i++){
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
        } */
       
        // secondo tentativo di codice
        /*
        while(++row <= Chessboard.ROW_UPPER_LIMIT  && ++col <= Chessboard.COL_UPPER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty())
            possiblePositions.add(new Position(row, col));
        
        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(--row >= Chessboard.ROW_LOWER_LIMIT  && --col >= Chessboard.COL_LOWER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty())
            possiblePositions.add(new Position(row, col));
        
        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(--row >= Chessboard.ROW_LOWER_LIMIT  && ++col <= Chessboard.COL_UPPER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty())
            possiblePositions.add(new Position(row, col));

        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
        while(++row <= Chessboard.ROW_UPPER_LIMIT  && --col >= Chessboard.COL_LOWER_LIMIT && this.getChessboard().getSquare(row, col).getPiece().isEmpty())
            possiblePositions.add(new Position(row, col));
        
        row = this.getPosition().getRow(); 
        col = this.getPosition().getCol();
        
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
