package model.gameEnvironment;

import java.util.Optional;
import model.pieces.Piece;

public class Square {
    private Optional<Piece> piece;
    
    public Square(Piece piece){
        this.piece = Optional.ofNullable(piece);
    }
    
    public Optional<Piece> getPiece(){
        return this.piece;
    }
    
    public void setPiece(Piece piece){
        this.piece = Optional.ofNullable(piece);
    }
}
