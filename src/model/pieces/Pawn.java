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
        if(this.getColor() == 0){
            if(this.isPossiblePosition(row + 1, col)){
                possiblePositions.add(new Position(row + 1, col));
                if(this.isFirstMove() && this.isPossiblePosition(row + 2, col)){
                    if(this.getChessboard().getSquare(row + 1, col).getPiece().isEmpty())  // -> questo serve perchè io posso andare avanti
                        possiblePositions.add(new Position(row + 2, col));                 // di 2 caselle se nella prima casella avanti 
                    this.switchFirstMove();                                                // a me non c'è nessuno
                }
            }    
        }else{
            if(this.isPossiblePosition(row - 1, col)){
                possiblePositions.add(new Position(row - 1, col));
                if(this.isFirstMove() && this.isPossiblePosition(row - 2, col)){
                    if(this.getChessboard().getSquare(row - 1, col).getPiece().isEmpty())
                        possiblePositions.add(new Position(row - 2, col));
                    this.switchFirstMove();
                }
            }
        }
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
