package Pieces;

import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Knight;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KnightTest {
    private Chessboard chessboard;
    private Knight knight;

    @Before
    public void setUp() {
        Position p = new Position(4,4);
        chessboard = new Chessboard(p);
        knight = new Knight(p, ColorChessboard.WHITE, chessboard, 'h');
        chessboard.getSquare(p.getRow(), p.getCol()).setPiece(knight);
    }

    @Test
    public void testCalculateMovement() {
        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(2, 3));
        expectedPositions.add(new Position(2, 5));
        expectedPositions.add(new Position(3, 2));
        expectedPositions.add(new Position(3, 6));
        expectedPositions.add(new Position(5, 2));
        expectedPositions.add(new Position(5, 6));
        expectedPositions.add(new Position(6, 3));
        expectedPositions.add(new Position(6, 5));

        ArrayList<Position> calculatedPositions = knight.calculateMovement();
        
        assertEquals(expectedPositions.size(), calculatedPositions.size());
        
        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }
}
