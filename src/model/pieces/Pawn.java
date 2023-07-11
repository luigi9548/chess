package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean firstMove;
    private boolean enPassant;
    
    public Pawn(final String name, Position position,final int color, Chessboard chessboard){
        super(name, position, color, chessboard);
        this.firstMove = true;
    }

    public boolean isFirstMove(){
        return this.firstMove;
    }
    
    public void switchFirstMove(){
        if(this.firstMove){
            this.firstMove = false;
        }
    }
    
    public void setEnPassant(boolean value){
        this.enPassant = value;
    }
    
    public boolean getEnPassant(){
        return this.enPassant;
    }
    
    @Override
    public ArrayList<Position> calculateMovement(Position position){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        ArrayList<Position> possibleEat = new ArrayList<>();
        Position enPassant;
        
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        
        if(this.getColor() == 0){
            if(this.isPossiblePosition(row + 1, col)){
                possiblePositions.add(new Position(row + 1, col));
                if(this.isFirstMove() && this.isPossiblePosition(row + 2, col)){
                    if(this.getChessboard().getSquare(row + 1, col).getPiece().isEmpty())  // -> questo serve perchè io posso andare avanti
                        possiblePositions.add(new Position(row + 2, col));                 // di 2 caselle se nella prima casella avanti 
                    //this.switchFirstMove();                                                // a me non c'è nessuno
                }
            }    
        }else{
            if(this.isPossiblePosition(row - 1, col)){
                possiblePositions.add(new Position(row - 1, col));
                if(this.isFirstMove() && this.isPossiblePosition(row - 2, col)){
                    if(this.getChessboard().getSquare(row - 1, col).getPiece().isEmpty())
                        possiblePositions.add(new Position(row - 2, col));
                    //this.switchFirstMove();
                }
            }
        }
        possibleEat = this.eat();
        if(!possibleEat.isEmpty()){
            for(Position p : possibleEat)
                possiblePositions.add(p);
        }
        
        enPassant = this.getChessboard().enPassant(this);
        if(enPassant != null){
            if(this.getColor() == 0){
                possiblePositions.add(new Position(row + 1, enPassant.getCol()));
            }else{
                possiblePositions.add(new Position(row - 1, enPassant.getCol()));
            }
        }
            
        return possiblePositions;
    }
    
    private boolean isPossiblePosition(int row, int col){
        boolean ret = false;
        
        if(this.getChessboard().isValidPosition(row, col)){
            if(this.getChessboard().getSquare(row, col).getPiece().isEmpty()){
                ret = true;
            }
        }
        return ret;
    }
    
    private ArrayList<Position> eat(){
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();
        if(this.getColor() == 0){
            if(this.getChessboard().isValidPosition(row + 1, col - 1)  && this.getChessboard().getSquare(row + 1, col - 1).getPiece().isPresent() && 
                    this.getColor() != this.getChessboard().getSquare(row + 1, col - 1).getPiece().get().getColor())
                possiblePositions.add(new Position(row + 1, col -1));

            if(this.getChessboard().isValidPosition(row + 1, col + 1)  && this.getChessboard().getSquare(row + 1, col + 1).getPiece().isPresent() && 
                    this.getColor() != this.getChessboard().getSquare(row + 1, col + 1).getPiece().get().getColor())
                possiblePositions.add(new Position(row + 1, col + 1));
        }else{
            if(this.getChessboard().isValidPosition(row - 1, col - 1)  && this.getChessboard().getSquare(row - 1, col - 1).getPiece().isPresent() && 
                    this.getColor() != this.getChessboard().getSquare(row - 1, col - 1).getPiece().get().getColor())
                possiblePositions.add(new Position(row - 1, col -1));

            if(this.getChessboard().isValidPosition(row - 1, col + 1)  && this.getChessboard().getSquare(row - 1, col + 1).getPiece().isPresent() && 
                    this.getColor() != this.getChessboard().getSquare(row - 1, col + 1).getPiece().get().getColor())
                possiblePositions.add(new Position(row - 1, col + 1));
        }
        return possiblePositions;
    }    
}
