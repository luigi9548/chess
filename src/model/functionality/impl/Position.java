package model.functionality.impl;

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
    public boolean compare(Position p){
        return this.getRow() == p.getRow() && this.getCol() == p.getCol();
    }
}
