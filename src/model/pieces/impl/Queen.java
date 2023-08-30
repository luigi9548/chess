package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.functionality.impl.ColorChessboard;

public class Queen extends Piece {
    public Queen(Position position,final ColorChessboard color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
    }

    @Override
    public ArrayList<Position> calculateMovement() {
        List<Position> possiblePositions = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();

        // Definiamo le direzioni di movimento: diagonali, righe e colonne
        List<int[]> directions = Arrays.asList(
                new int[]{1, 1}, new int[]{1, -1}, new int[]{-1, 1}, new int[]{-1, -1}, // diagonali
                new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{0, -1}    // righe e colonne
        );

        // Utilizziamo Stream e lambda expressions per ottenere tutte le posizioni valide
        directions.stream()
                .flatMap(d -> getPositionsInDirection(row, col, d[0], d[1]).stream())
                .forEach(possiblePositions::add);

        return new ArrayList<>(possiblePositions);
    }

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
