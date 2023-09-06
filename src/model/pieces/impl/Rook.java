package model.pieces.impl;

import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import java.util.ArrayList;
import model.enumerations.ColorChessboardEnum;
import model.enumerations.IconEnum;
import model.gameEnvironment.impl.Square;

public class Rook extends Piece {
    public Rook(Position position,final ColorChessboardEnum color, Chessboard chessboard, char pieceSign){
        super(position, color, chessboard, pieceSign);
        if(color == ColorChessboardEnum.WHITE)
            this.setIcon(IconEnum.WROOK_ICON.getIcon());
        else
            this.setIcon(IconEnum.BROOK_ICON.getIcon());
    }
    
    @Override
    public ArrayList<Position> calculateMovement() {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();
        Chessboard chessboard = this.getChessboard();
        
        // Move upwards
        for (int r = row + 1; r <= Chessboard.ROW_UPPER_LIMIT; r++) {
            Square square = chessboard.getSquare(r, col);
            if (square.getPiece().isEmpty()) {
                possiblePositions.add(new Position(r, col));
            } else if (isEnemy(square.getPiece().get())) {
                possiblePositions.add(new Position(r, col));
                break;
            } else {
                break;
            }
        }

        // Move downwards
        for (int r = row - 1; r >= Chessboard.ROW_LOWER_LIMIT; r--) {
            Square square = chessboard.getSquare(r, col);
            if (square.getPiece().isEmpty()) {
                possiblePositions.add(new Position(r, col));
            } else if (isEnemy(square.getPiece().get())) {
                possiblePositions.add(new Position(r, col));
                break;
            } else {
                break;
            }
        }

        // Move leftwards
        for (int c = col - 1; c >= Chessboard.COL_LOWER_LIMIT; c--) {
            Square square = chessboard.getSquare(row, c);
            if (square.getPiece().isEmpty()) {
                possiblePositions.add(new Position(row, c));
            } else if (isEnemy(square.getPiece().get())) {
                possiblePositions.add(new Position(row, c));
                break;
            } else {
                break;
            }
        }

        // Move rightwards
        for (int c = col + 1; c <= Chessboard.COL_UPPER_LIMIT; c++) {
            Square square = chessboard.getSquare(row, c);
            if (square.getPiece().isEmpty()) {
                possiblePositions.add(new Position(row, c));
            } else if (isEnemy(square.getPiece().get())) {
                possiblePositions.add(new Position(row, c));
                break;
            } else {
                break;
            }
        }

        return possiblePositions;
    }
}
