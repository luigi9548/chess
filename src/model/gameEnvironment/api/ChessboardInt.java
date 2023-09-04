package model.gameEnvironment.api;

import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Square;
import model.pieces.impl.Pawn;
import model.pieces.impl.Piece;

public interface ChessboardInt {

    /**
     * Checks if castling (king-side or queen-side) is possible for the player of the specified color.
     *
     * @param color The color of the player.
     * @return The position of the rook with which castling can occur, or null if not possible.
     */
    Position canCastling(ColorChessboard color);

    /**
     * Calculates the position of the pawn that can be captured using the en passant move.
     *
     * @param p The pawn making the en passant move.
     * @return The position of the opponent's pawn that can be captured with the en passant move,
     *         or null if there is no opponent's pawn available for en passant.
     */
    Position enPassant(Pawn p);

    /**
     * Retrieves a specific square on the chessboard.
     *
     * @param row The row index of the square.
     * @param col The column index of the square.
     * @return The Square object at the specified row and column.
     */
    Square getSquare(int row, int col);

    /**
     * Checks if the player of the given color is in check.
     *
     * @param color The color of the player to check for.
     * @return True if the player is in check, otherwise false.
     */
    boolean isCheck(ColorChessboard color);

    /**
     * Checks if the game is in a checkmate or stalemate state.
     *
     * @param color The color of the player to check for.
     * @return 0 if it's checkmate, 1 if it's stalemate, or 2 if neither.
     */
    int isCheckmateOrFlap(ColorChessboard color);

    /**
     * Checks if a given position is within the bounds of the chessboard.
     *
     * @param p The position to check.
     * @return True if the position is valid, otherwise false.
     */
    boolean isValidPosition(Position p);

    /**
     * Computes the legal movements for a given chess piece.
     *
     * @param p The chess piece for which legal movements are calculated.
     * @return An ArrayList of valid positions to which the piece can move.
     */
    ArrayList<Position> legalMovements(Piece p);
    
    /**
     * Configures a pawn piece, handling various actions such as en passant, first move, and en passant flag.
     *
     * @param pawn The pawn piece to configure.
     * @param row The row to which the pawn is moved.
     * @param col The column to which the pawn is moved.
     * @return A boolean indicating whether the en passant rule was applied during the configuration.
     */
    boolean configurePawn(Pawn pawn, int row, int col);

    /**
     * Checks if a pawn has reached the opposite end of the chessboard for promotion.
     *
     * @param p The pawn to check for promotion.
     * @return True if the pawn has reached the opposite end, false otherwise.
     */
    boolean promotion(Pawn p);

    /**
     * Switches the turn between players. If it's Player 1's turn, it changes to Player 2's turn, and vice versa.
     */
    void switchTurn();
}
