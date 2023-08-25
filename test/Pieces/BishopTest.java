package pieces;

import java.util.ArrayList;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Bishop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BishopTest {
    private Chessboard chessboard;
    private Bishop bishop;

    @Before
    public void setUp() {
        Position p = new Position(4,4);
        chessboard = new Chessboard(p);
        bishop = new Bishop(p, ColorChessboard.WHITE, chessboard, 'b');
        chessboard.getSquare(p.getRow(), p.getCol()).setPiece(bishop);
    }

    @Test
    public void testCalculateMovement() {
        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(3, 3));
        expectedPositions.add(new Position(2, 2));
        expectedPositions.add(new Position(1, 1));
        expectedPositions.add(new Position(0, 0));
        expectedPositions.add(new Position(3, 5));
        expectedPositions.add(new Position(2, 6));
        expectedPositions.add(new Position(7, 1));
        expectedPositions.add(new Position(5, 3));
        expectedPositions.add(new Position(6, 2));
        expectedPositions.add(new Position(7, 1));
        expectedPositions.add(new Position(5, 5));
        expectedPositions.add(new Position(6, 6));
        expectedPositions.add(new Position(7, 7));

        ArrayList<Position> calculatedPositions = bishop.calculateMovement();
        
        assertEquals(expectedPositions.size(), calculatedPositions.size());
        
        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }
}


