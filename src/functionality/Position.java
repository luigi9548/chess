package functionality;

public class Position {

    int row;
    int col;
    
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return this.row;
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
}
