/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Bishop;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author calci
 */
public class TestBishop {

    private Chessboard chessboard = new Chessboard();
    private Bishop bishop = new Bishop(new Position(1,3), 0, chessboard, 'b');
    
    @org.junit.Test
    public void testCalculateMovement(){
        int i = 0;
   
        ArrayList<Position> possiblePositions = new ArrayList<>();     
        ArrayList<Position> realPositions = new ArrayList<>();
        
        
        possiblePositions.add(new Position(2,4));
        possiblePositions.add(new Position(3,5));
        possiblePositions.add(new Position(4,6));
        possiblePositions.add(new Position(5,7));
        possiblePositions.add(new Position(2,2));
        possiblePositions.add(new Position(3,1));
        possiblePositions.add(new Position(4,0));
        
        
        realPositions = bishop.calculateMovement();
        
        for(Position a : possiblePositions){
            assertTrue(a.compare(realPositions.get(i)));
            i++;
        }
    }  
 }

