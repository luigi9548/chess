package pieces;

import java.util.ArrayList;
import model.enumerations.ColorChessboardEnum;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Rook;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RookTest {
    private Chessboard chessboard;
    private Rook rook;

    @Before
    public void setUp() {
        Position p = new Position(4,4);
        chessboard = Chessboard.getIstanceForTest();
        rook = new Rook(p, ColorChessboardEnum.WHITE, chessboard, 'r');
        chessboard.getSquare(p.getRow(), p.getCol()).setPiece(rook);
    }

    @Test
    public void testCalculateMovement() {
        ArrayList<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(new Position(4, 1));
        expectedPositions.add(new Position(4, 2));
        expectedPositions.add(new Position(4, 3));
        expectedPositions.add(new Position(4, 5));
        expectedPositions.add(new Position(4, 6));
        expectedPositions.add(new Position(4, 7));
        expectedPositions.add(new Position(4, 0));
        expectedPositions.add(new Position(0, 4));
        expectedPositions.add(new Position(1, 4));
        expectedPositions.add(new Position(2, 4));
        expectedPositions.add(new Position(3, 4));
        expectedPositions.add(new Position(5, 4));
        expectedPositions.add(new Position(6, 4));
        expectedPositions.add(new Position(7, 4));

        ArrayList<Position> calculatedPositions = rook.calculateMovement();
        
        assertEquals(expectedPositions.size(), calculatedPositions.size());
        
        for (Position pos : expectedPositions) {
            assertTrue(calculatedPositions.contains(pos));
        }
    }
}


