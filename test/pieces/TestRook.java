/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.pieces.impl.Rook;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Elena
 */
public class TestRook {
    private Chessboard chessboard = new Chessboard();
    private Rook rook = new Rook(new Position(3,3),0,chessboard, 'r');
    
    @org.junit.Test
    public void testCalculateMovement(){
        ArrayList<Position> liExp = new ArrayList<>();
        
        //aggiungo posizioni aspettate
        liExp.add(new Position(4,3));
        liExp.add(new Position(5,3));
        liExp.add(new Position(6,3));
        liExp.add(new Position(2,3));
        liExp.add(new Position(3,2));
        liExp.add(new Position(3,1));
        liExp.add(new Position(3,0));
        liExp.add(new Position(3,4));
        liExp.add(new Position(3,5));
        liExp.add(new Position(3,6));
        liExp.add(new Position(3,7));
        
        // metodo calcuteMovemement
        ArrayList<Position> li = new ArrayList<>();
        li = rook.calculateMovement();
        
        //confronto
        for(int i = 0; i < li.size(); i++){
            assertTrue(liExp.get(i).compare(li.get(i)));  
        }
        
    }
    
    
}
