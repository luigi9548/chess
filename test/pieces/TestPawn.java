/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.Pawn;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author calci
 */
public class TestPawn {
    
    private Chessboard chessboard = new Chessboard();
    private Pawn pawn = new Pawn("White", new Position(2,6), 0, chessboard, 'p');
    
    @org.junit.Test
    public void testCalculateMovement(){
        int i = 0;
        
        ArrayList<Position> possiblePositions = new ArrayList<>();
        ArrayList<Position> realPositions = new ArrayList<>();
        
        possiblePositions.add(new Position(3,6));
        possiblePositions.add(new Position(4,6));
        
        realPositions = pawn.calculateMovement(new Position(2,6));
     
        
        for(Position a : possiblePositions){
            assertTrue(a.compare(realPositions.get(i)));
            i++;
        }
        
        realPositions = pawn.calculateMovement(null);
         if(realPositions.size()==1){
            assertTrue(possiblePositions.get(0).compare(realPositions.get(0)));
         }        
    }

    @org.junit.Test
    public void testEat(){
        int i = 0;
        Pawn pawn1 = new Pawn("White", new Position(5,6), 0, chessboard, 'p'); 
        
        ArrayList<Position> possiblePositions = new ArrayList<>();
        ArrayList<Position> realPositions = new ArrayList<>();
        
        possiblePositions.add(new Position(6,5));
        possiblePositions.add(new Position(6,7));
        
        //realPositions = pawn
     
        
        for(Position a : possiblePositions){
            assertTrue(a.compare(realPositions.get(i)));
            i++;
        }

        
        
    }
}
