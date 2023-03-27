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
    private Pawn pawn = new Pawn("White", new Position(2,2), 0, chessboard);
    
    @org.junit.Test
    public void testCalculateMovement(){
        int i = 0;
        
        
        ArrayList<Position> possiblePositions = new ArrayList<>();
        
        ArrayList<Position> realPositions = new ArrayList<>();
        
        possiblePositions.add(new Position(2,3));
        possiblePositions.add(new Position(2,4));
        
        realPositions = pawn.calculateMovement(null);
       
        
        for(Position a : possiblePositions){
            System.out.println(realPositions.get(i));
            assertTrue(a.compare(realPositions.get(i)));
            i++;
        }
        
         realPositions = pawn.calculateMovement(null);
         if(realPositions.size()==1){
            assertTrue(possiblePositions.get(0).compare(realPositions.get(0)));
         }
        
        
    }

}