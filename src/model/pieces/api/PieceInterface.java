package model.pieces.api;

import java.util.ArrayList;
import model.functionality.impl.Position;

public interface PieceInterface {
    
    // metodo per calcolare i movimenti del pezzo
    public ArrayList<Position> calculateMovement();
}
