package model.pieces.api;

import java.util.ArrayList;
import model.functionality.impl.Position;

public interface PieceInterface {
    
    /**
    * Calculates the possible movements for the piece.
    *
    * @return An ArrayList of Position objects representing possible move destinations.
    */
    public ArrayList<Position> calculateMovement();
}
