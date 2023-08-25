package pieces;

import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Queen;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class QueenTest {
    private Chessboard chessboard;
    private Queen queen;

    @Before
    public void setUp() {
        Position p = new Position(4,4);
        chessboard = new Chessboard(p);
        queen = new Queen(p, ColorChessboard.WHITE, chessboard, 'q');
        chessboard.getSquare(p.getRow(), p.getCol()).setPiece(queen);
    }

    @Test
    public void testCalculateMovement() {
        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(3, 4));
        expectedPositions.add(new Position(2, 4));
        expectedPositions.add(new Position(1, 4));
        expectedPositions.add(new Position(0, 4));
        expectedPositions.add(new Position(5, 4));
        expectedPositions.add(new Position(6, 4));
        expectedPositions.add(new Position(7, 4));
        expectedPositions.add(new Position(4, 3));
        expectedPositions.add(new Position(4, 2));
        expectedPositions.add(new Position(4, 1));
        expectedPositions.add(new Position(4, 0));
        expectedPositions.add(new Position(4, 5));
        expectedPositions.add(new Position(4, 6));
        expectedPositions.add(new Position(4, 7));
        expectedPositions.add(new Position(3, 3));
        expectedPositions.add(new Position(2, 2));
        expectedPositions.add(new Position(1, 1));
        expectedPositions.add(new Position(0, 0));
        expectedPositions.add(new Position(3, 5));
        expectedPositions.add(new Position(2, 6));
        expectedPositions.add(new Position(1, 7));
        expectedPositions.add(new Position(5, 3));
        expectedPositions.add(new Position(6, 2));
        expectedPositions.add(new Position(7, 1));
        expectedPositions.add(new Position(5, 5));
        expectedPositions.add(new Position(6, 6));
        expectedPositions.add(new Position(7, 7));

        ArrayList<Position> calculatedPositions = queen.calculateMovement();
        
        assertEquals(expectedPositions.size(), calculatedPositions.size());
        
        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }
}
