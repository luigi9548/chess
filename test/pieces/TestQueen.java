/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.Queen;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Elena
 */
public class TestQueen {
    private Chessboard chessboard = new Chessboard();
    private Queen queen = new Queen("Queen", new Position(2,3),0,chessboard, 'q');
    
    @org.junit.Test
    public void testCalculateMovement(){
                
        ArrayList<Position> possiblePositions = new ArrayList<>();
        ArrayList<Position> realPositions = new ArrayList<>();
        
        possiblePositions.add(new Position(3,4));
        possiblePositions.add(new Position(4,5));
        possiblePositions.add(new Position(5,6));
        possiblePositions.add(new Position(6,7));
        possiblePositions.add(new Position(3,2));
        possiblePositions.add(new Position(4,1));
        possiblePositions.add(new Position(5,0));
        possiblePositions.add(new Position(3,3));
        possiblePositions.add(new Position(4,3));
        possiblePositions.add(new Position(5,3));
        possiblePositions.add(new Position(6,3));
        possiblePositions.add(new Position(2,2));
        possiblePositions.add(new Position(2,1));
        possiblePositions.add(new Position(2,0));
        possiblePositions.add(new Position(2,4));
        possiblePositions.add(new Position(2,5));
        possiblePositions.add(new Position(2,6));
        possiblePositions.add(new Position(2,7));
        
        realPositions = queen.calculateMovement(null);
        
        for(int i = 0; i < realPositions.size(); i++){
            assertTrue(possiblePositions.get(i).compare(realPositions.get(i)));  
        }
    }
    
}
