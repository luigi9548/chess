package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.functionality.impl.ColorChessboard;

public class King extends Piece {
    
    public King(Position position,final ColorChessboard color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
    }
    
    @Override
    public ArrayList<Position> calculateMovement() {
        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();
        int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

        // Utilizza Stream per iterare su tutti i delta e calcolare le nuove posizioni
        ArrayList<Position> possiblePositions =
                Stream.of(deltas)
                        .map(delta -> new Position(row + delta[0], col + delta[1]))
                        .filter(this::isPossiblePosition)
                        .collect(Collectors.toCollection(ArrayList::new));

        return possiblePositions;
    }

    private boolean isPossiblePosition(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return this.getChessboard().isValidPosition(new Position(row, col)) &&
               (this.getChessboard().getSquare(row, col).getPiece().isEmpty() || this.isEnemy(this.getChessboard().getSquare(row, col).getPiece().get()));
    }
}
