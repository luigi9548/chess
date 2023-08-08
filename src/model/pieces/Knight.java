package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.functionality.ColorChessboard;

public class Knight extends Piece {
    public Knight(Position position,final ColorChessboard color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
    }

    @Override
    public ArrayList<Position> calculateMovement() {
        int row = getPosition().getRow();
        int col = getPosition().getCol();
        int[][] deltas = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {-1, -2}, {-1, 2}};

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
        return getChessboard().isValidPosition(row, col) &&
               (getChessboard().getSquare(row, col).getPiece().isEmpty() || isEnemy(getChessboard().getSquare(row, col).getPiece().get()));
    }
}
