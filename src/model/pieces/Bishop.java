package model.pieces;

import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import java.util.*;
import java.util.stream.IntStream;
import model.gameEnvironment.Square;

public class Bishop extends Piece {
    public Bishop(Position position,final int color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
    }

    @Override
    public ArrayList<Position> calculateMovement() {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        Chessboard chessboard = this.getChessboard();

        // Definiamo un elenco di delta per ciascuna direzione in cui ci possiamo muovere
         int[][] deltas = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

        // Utilizziamo IntStream per iterare su tutte le possibili direzioni
        IntStream.range(0, deltas.length).forEach(i -> {
            int newRow = row;
            int newCol = col;

            while (chessboard.isValidPosition(newRow += deltas[i][0], newCol += deltas[i][1])) {
                Square square = chessboard.getSquare(newRow, newCol);
                if (square.getPiece().isEmpty()) {
                    possiblePositions.add(new Position(newRow, newCol));
                } else if (isEnemy(square.getPiece().get())) {
                    possiblePositions.add(new Position(newRow, newCol));
                    break;
                } else {
                    break;
                }
            }
        });

        return possiblePositions;
    }
} 

