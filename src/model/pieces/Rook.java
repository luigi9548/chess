package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Position position,final int color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
    }
    
    @Override
    public ArrayList<Position> calculateMovement() {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();

        // Movimento verso l'alto
        for (int r = row + 1; r <= Chessboard.ROW_UPPER_LIMIT; r++) {
            if (isPossiblePosition(r, col)) {
                possiblePositions.add(new Position(r, col));
            } else {
                break;
            }
        }

        // Movimento verso il basso
        for (int r = row - 1; r >= Chessboard.ROW_LOWER_LIMIT; r--) {
            if (isPossiblePosition(r, col)) {
                possiblePositions.add(new Position(r, col));
            } else {
                break;
            }
        }

        // Movimento verso sinistra
        for (int c = col - 1; c >= Chessboard.COL_LOWER_LIMIT; c--) {
            if (isPossiblePosition(row, c)) {
                possiblePositions.add(new Position(row, c));
            } else {
                break;
            }
        }

        // Movimento verso destra
        for (int c = col + 1; c <= Chessboard.COL_UPPER_LIMIT; c++) {
            if (isPossiblePosition(row, c)) {
                possiblePositions.add(new Position(row, c));
            } else {
                break;
            }
        }

        return possiblePositions;
    }

    private boolean isPossiblePosition(int row, int col) {
        if (!getChessboard().isValidPosition(row, col)) {
            return false;
        }
        return getChessboard().getSquare(row, col).getPiece().isEmpty()
                || isEnemy(getChessboard().getSquare(row, col).getPiece().get());
    }
}
