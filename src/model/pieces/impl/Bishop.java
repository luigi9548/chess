package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.*;
import java.util.stream.IntStream;
import model.enumerations.ColorChessboardEnum;
import model.enumerations.IconEnum;
import model.gameEnvironment.impl.Square;

public class Bishop extends Piece {
    public Bishop(Position position,final ColorChessboardEnum color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
        if(color == ColorChessboardEnum.WHITE)
            this.setIcon(IconEnum.WBISHOP_ICON.getIcon());
        else
            this.setIcon(IconEnum.BBISHOP_ICON.getIcon());
    }

    @Override
    public ArrayList<Position> calculateMovement() {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow(), col = this.getPosition().getCol();
        Chessboard chessboard = this.getChessboard();

        // Define deltas for each direction the Bishop can move
        int[][] deltas = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

        // Iterate through all possible directions using IntStream
        IntStream.range(0, deltas.length).forEach(i -> {
            int newRow = row;
            int newCol = col;

            while (chessboard.isValidPosition(new Position(newRow += deltas[i][0], newCol += deltas[i][1]))) {
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

