package pieces;

import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Pawn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PawnTest {
    private Chessboard chessboard;
    private Pawn pawn;

    @Before
    public void setUp() {
        Position p = new Position(4,4);
        chessboard = new Chessboard(p);
        pawn = new Pawn(p, ColorChessboard.WHITE, chessboard, 'p');
        chessboard.getSquare(p.getRow(), p.getCol()).setPiece(pawn);
    }

    @Test
    public void testCalculateMovementFirstMove() {
        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(5, 4));
        expectedPositions.add(new Position(6, 4));

        ArrayList<Position> calculatedPositions = pawn.calculateMovement();

        assertEquals(expectedPositions.size(), calculatedPositions.size());

        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }

    @Test
    public void testCalculateMovementEnPassant() {
        // Simuliamo una situazione di en passant
        Position p = new Position(5, 5);
        Pawn enemyPawn = new Pawn(p, ColorChessboard.BLACK, chessboard, 'P');
        chessboard.getSquare(p.getRow(), p.getCol()).setPiece(enemyPawn);

        pawn.switchFirstMove();
        pawn.setEnPassant(true);

        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(5, 5));
        expectedPositions.add(new Position(5, 4));

        ArrayList<Position> calculatedPositions = pawn.calculateMovement();

        assertEquals(expectedPositions.size(), calculatedPositions.size());

        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }
}

