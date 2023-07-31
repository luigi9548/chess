package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;
import model.gameEnvironment.Square;

public class Pawn extends Piece {
    private boolean firstMove;
    private boolean enPassant;
    
    public Pawn(Position position,final int color, Chessboard chessboard, char pieceSign){
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

        int direction = (this.getColor() == 0) ? 1 : -1;

        // Movimento in avanti di una casella
        Position forwardOne = new Position(row + direction, col);
        if (isPossiblePosition(forwardOne)) {
            possiblePositions.add(forwardOne);
        }

        // Movimento in avanti di due caselle (primo movimento)
        if (isFirstMove()) {
            Position forwardTwo = new Position(row + 2 * direction, col);
            if (isPossiblePosition(forwardTwo)) {
                possiblePositions.add(forwardTwo);
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
        if (!getChessboard().isValidPosition(row, col)) {
            return false;
        }
        return getChessboard().getSquare(row, col).getPiece().isEmpty();
    }
}
