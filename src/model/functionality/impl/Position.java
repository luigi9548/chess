package model.functionality.impl;

import java.util.Objects;
import model.functionality.api.PositionInt;

public class Position implements PositionInt {
    
    int row;
    int col;
    
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return this.row;
    }
    
    @Override
    public char numToLetterBySubstr() {
        String LETTERS = "abcdefgh";
        return LETTERS.charAt(this.col);
    }
    
    @Override
    public String getStringPosition(){
        return this.numToLetterBySubstr() + Integer.toString(row);
    }
    
    public int getCol() {
        return this.col;
    }
    
    public Position getPosition() {
       return this;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
