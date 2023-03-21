package model.gameEnvironment;

import model.functionality.Position;
import java.util.Optional;
import model.pieces.Piece;

public class Square {
    private Position p;
    private Optional<Piece> piece;
    
    public Square(Position p, Piece piece){
        this.p = p;
        this.piece = Optional.ofNullable(piece);
    }
    
    public void setPosition(int row, int col)
    {
        this.p.setPosition(row, col);
    }
    
    public Optional<Piece> getPiece(){
        return this.piece;
    }
}
