/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.King;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Elena
 */
public class TestKing {
    
    private Chessboard chessboard = new Chessboard();
    private King king = new King("King", new Position(3,5),0,chessboard, 'k');
    
    @org.junit.Test
    public void testCalculateMovement(){
                
        ArrayList<Position> possiblePositions = new ArrayList<>();
        ArrayList<Position> realPositions = new ArrayList<>();
        
        possiblePositions.add(new Position(4,5));
        possiblePositions.add(new Position(2,5));
        possiblePositions.add(new Position(3,6));
        possiblePositions.add(new Position(3,4));
        possiblePositions.add(new Position(4,6));
        possiblePositions.add(new Position(2,6));
        possiblePositions.add(new Position(4,4));
        possiblePositions.add(new Position(2,4));
        
        realPositions = king.calculateMovement(null);
        
        for(int i = 0; i < realPositions.size(); i++){
            assertTrue(possiblePositions.get(i).compare(realPositions.get(i)));  
        }
        
        
    }
    
    
    
}
