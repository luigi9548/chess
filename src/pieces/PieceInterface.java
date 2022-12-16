package pieces;

import functionality.Position;
import java.util.List;

public interface PieceInterface {
    public List<Position> calculateMovement(Position position);
}
