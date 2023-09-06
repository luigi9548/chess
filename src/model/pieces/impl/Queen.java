package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.enumerations.ColorChessboardEnum;

public class Queen extends Piece {
    public Queen(Position position,final ColorChessboardEnum color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
    }

    @Override
    public ArrayList<Position> calculateMovement() {
        List<Position> possiblePositions = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();

        // Define movement directions: diagonals, rows, and columns
        List<int[]> directions = Arrays.asList(
                new int[]{1, 1}, new int[]{1, -1}, new int[]{-1, 1}, new int[]{-1, -1}, // diagonali
                new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{0, -1}    // righe e colonne
        );

        // Use Stream and lambda expressions to get all valid positions
        directions.stream()
                .flatMap(d -> getPositionsInDirection(row, col, d[0], d[1]).stream())
                .forEach(possiblePositions::add);

        return new ArrayList<>(possiblePositions);
    }

    /**
    * Get positions in a specified direction until an obstacle is encountered.
    *
    * @param row          The starting row.
    * @param col          The starting column.
    * @param rowIncrement The increment in the row direction.
    * @param colIncrement The increment in the column direction.
    * @return An ArrayList of valid positions in the specified direction.
    */
    private ArrayList<Position> getPositionsInDirection(int row, int col, int rowIncrement, int colIncrement) {
        ArrayList<Position> positions = new ArrayList<>();

        while (getChessboard().isValidPosition(new Position(row + rowIncrement, col + colIncrement))) {
            row += rowIncrement;
            col += colIncrement;
            if (getChessboard().getSquare(row, col).getPiece().isEmpty()) {
                positions.add(new Position(row, col));
            } else if (isEnemy(getChessboard().getSquare(row, col).getPiece().get())) {
                positions.add(new Position(row, col));
                break;
            } else {
                break;
            }
        }

        return positions;
    }
}
