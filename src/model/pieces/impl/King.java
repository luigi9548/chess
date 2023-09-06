package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.enumerations.ColorChessboardEnum;

public class King extends Piece {
    
    public King(Position position,final ColorChessboardEnum color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
    }
    
    @Override
    public ArrayList<Position> calculateMovement() {
        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();
        
        // Define deltas for each direction King can move
        int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

        // Use Stream to iterate over all the deltas and calculate new positions
        ArrayList<Position> possiblePositions =
                Stream.of(deltas)
                        .map(delta -> new Position(row + delta[0], col + delta[1]))
                        .filter(this::isPossiblePosition)
                        .collect(Collectors.toCollection(ArrayList::new));

        return possiblePositions;
    }

    /**
    * Checks if a given position is a valid and empty square or occupied by an enemy piece.
    *
    * @param position The position to check.
    * @return True if the position is valid and empty or occupied by an enemy piece, false otherwise.
    */
    private boolean isPossiblePosition(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return this.getChessboard().isValidPosition(new Position(row, col)) &&
               (this.getChessboard().getSquare(row, col).getPiece().isEmpty() || this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()));
    }
}
