package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import model.enumerations.ColorChessboardEnum;
import model.enumerations.IconEnum;
import model.gameEnvironment.impl.Square;

public class Pawn extends Piece {
    private boolean firstMove;
    private boolean enPassant;
    
    public Pawn(Position position,final ColorChessboardEnum color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
        this.firstMove = true;
        if(color == ColorChessboardEnum.WHITE)
            this.setIcon(IconEnum.WPAWN_ICON.getIcon());
        else
            this.setIcon(IconEnum.BPAWN_ICON.getIcon());
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
    public ArrayList<Position> calculateMovement() {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();

        int direction = (this.getColor() == ColorChessboardEnum.WHITE) ? 1 : -1;

        // Forward movement by one square
        Position forwardOne = new Position(row + direction, col);
        if (isPossiblePosition(forwardOne)) {
            possiblePositions.add(forwardOne);
            // Forward movement by one square
            if (isFirstMove()) {
                Position forwardTwo = new Position(row + 2 * direction, col);
                if(canForwardTwo(forwardTwo)){
                    if (isPossiblePosition(forwardTwo)) {
                        possiblePositions.add(forwardTwo);
                    }
                }
            }
        }

        // Capturing an opponent's piece
        int[] colsToEat = { col - 1, col + 1 };
        for (int c : colsToEat) {
            Position eatPosition = new Position(row + direction, c);
            if (getChessboard().isValidPosition(eatPosition)) {
                Square targetSquare = getChessboard().getSquare(eatPosition.getRow(), eatPosition.getCol());
                if (!targetSquare.getPiece().isEmpty() && isEnemy(targetSquare.getPiece().get())) {
                    possiblePositions.add(eatPosition);
                }
            }
        }

        // En passant movement
        Position enPassantPosition = this.getChessboard().enPassant(this);
        if (enPassantPosition != null) {
            possiblePositions.add(new Position(row + direction, enPassantPosition.getCol()));
        }

        return possiblePositions;
    }
    
    /**
    * Checks if a given position is a valid and empty square.
    *
    * @param position The position to check.
    * @return True if the position is valid and empty, false otherwise.
    */
    private boolean isPossiblePosition(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return getChessboard().isValidPosition(new Position(row, col)) &&
               (getChessboard().getSquare(row, col).getPiece().isEmpty());
    }
    
    /**
    * Checks if the Pawn can move forward by two squares.
    *
    * @param position The position to check.
    * @return True if the Pawn can move forward by two squares, false otherwise.
    */
    private boolean canForwardTwo(Position position){
        int row = position.getRow();
        int col = position.getCol();
        int direction = (this.getColor() == ColorChessboardEnum.WHITE) ? -1 : 1;
        
        return getChessboard().getSquare(row + direction, col).getPiece().isEmpty();
    }
}
