package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.gameEnvironment.impl.Square;

public class Pawn extends Piece {
    private boolean firstMove;
    private boolean enPassant;
    
    public Pawn(Position position,final ColorChessboard color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
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
    public ArrayList<Position> calculateMovement() {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();

        int direction = (this.getColor() == ColorChessboard.WHITE) ? 1 : -1;

        // Movimento in avanti di una casella
        Position forwardOne = new Position(row + direction, col);
        if (isPossiblePosition(forwardOne)) {
            possiblePositions.add(forwardOne);
            // Movimento in avanti di due caselle (primo movimento)
            if (isFirstMove()) {
                Position forwardTwo = new Position(row + 2 * direction, col);
                if(canForwardTwo(forwardTwo)){
                    if (isPossiblePosition(forwardTwo)) {
                        possiblePositions.add(forwardTwo);
                    }
                }
            }
        }

        // Movimento per mangiare una pedina
        int[] colsToEat = { col - 1, col + 1 };
        for (int c : colsToEat) {
            Position eatPosition = new Position(row + direction, c);
            if (getChessboard().isValidPosition(eatPosition.getRow(), eatPosition.getCol())) {
                Square targetSquare = getChessboard().getSquare(eatPosition.getRow(), eatPosition.getCol());
                if (!targetSquare.getPiece().isEmpty() && isEnemy(targetSquare.getPiece().get())) {
                    possiblePositions.add(eatPosition);
                }
            }
        }

        // Movimento en passant
        Position enPassantPosition = this.getChessboard().enPassant(this);
        if (enPassantPosition != null) {
            possiblePositions.add(new Position(row + direction, enPassantPosition.getCol()));
        }

        return possiblePositions;
    }

    private boolean isPossiblePosition(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return getChessboard().isValidPosition(row, col) &&
               (getChessboard().getSquare(row, col).getPiece().isEmpty());
    }
    
    private boolean canForwardTwo(Position position){
        int row = position.getRow();
        int col = position.getCol();
        int direction = (this.getColor() == ColorChessboard.WHITE) ? -1 : 1;
        
        return getChessboard().getSquare(row + direction, col).getPiece().isEmpty();
    }
}
