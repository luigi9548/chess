/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.King;
import static org.junit.Assert.*;

public class TestKing {
    private Chessboard chessboard = new Chessboard();
    private King king = new King("White", new Position(3,2), 0, chessboard);
    
    @org.junit.Test
    public void testCalculateMovement(){
        int i = 0;
        ArrayList<Position> possiblePositions = new ArrayList<>();
        ArrayList<Position> realPositions = new ArrayList<>();
        possiblePositions.add(new Position(4,2));
        possiblePositions.add(new Position(2,2));
        possiblePositions.add(new Position(3,3));
        possiblePositions.add(new Position(3,1));
        possiblePositions.add(new Position(4,3));
        possiblePositions.add(new Position(2,1));
        possiblePositions.add(new Position(2,3));
        possiblePositions.add(new Position(4,1));
        realPositions = king.calculateMovement(null);
        for(Position a : possiblePositions){
            assertTrue(a.compare(realPositions.get(i)));
            i++;
        }
    }
    
   /* @org.junit.Test
    public void testCheck(){
        assertTrue(king.check(king.getPosition()).isEmpty());
        
        king.getPosition().setPosition(5, 2);
        
        assertTrue(king.getPosition().compare(king.check(king.getPosition()).get()));
    }*/
}
