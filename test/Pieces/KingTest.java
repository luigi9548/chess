package pieces;

import java.util.ArrayList;
import model.enumerations.ColorChessboardEnum;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.King;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KingTest {
    private Chessboard chessboard;
    private King king;

    @Before
    public void setUp() {
        Position p = new Position(4,4);
        chessboard = Chessboard.getIstanceForTest();;
        king = new King(p, ColorChessboardEnum.WHITE, chessboard, 'k');
        chessboard.getSquare(p.getRow(), p.getCol()).setPiece(king);
    }

    @Test
    public void testCalculateMovement() {
        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(3, 3));
        expectedPositions.add(new Position(3, 4));
        expectedPositions.add(new Position(3, 5));
        expectedPositions.add(new Position(4, 3));
        expectedPositions.add(new Position(4, 5));
        expectedPositions.add(new Position(5, 3));
        expectedPositions.add(new Position(5, 4));
        expectedPositions.add(new Position(5, 5));

        ArrayList<Position> calculatedPositions = king.calculateMovement();
        
        assertEquals(expectedPositions.size(), calculatedPositions.size());
        
        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }
}
