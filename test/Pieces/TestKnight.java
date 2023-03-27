/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pieces;

import java.util.ArrayList;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.Knight;
import static org.junit.Assert.*;

/**
 *
 * @author Elena
 */
public class TestKnight {
    
    @org.junit.Test
    public void testMovement(){
        Position position3 = new Position(0,0);
        Knight kn3 = new Knight("KNIGHT", position3 ,0,new Chessboard());
        ArrayList<Position> liExp3 = new ArrayList<>();
        liExp3.add(new Position(1,2));
        liExp3.add(new Position(2,1));
        
        ArrayList<Position> li3= new ArrayList<>();
        li3 = kn3.calculateMovement(position3);
        
        for(int i = 0; i < li3.size(); i++){
            assertTrue(liExp3.get(i).compare(li3.get(i)));   
        }
        
        // creo il knight nella sua posizione iniziale di partenza
        Position position = new Position(0,1);
        Knight kn = new Knight("KNIGHT",position,0, new Chessboard());
        
        ArrayList<Position> liExp = new ArrayList<>();
        liExp.add(new Position(1,3));
        liExp.add(new Position(2,2));
        liExp.add(new Position(2,0));
        
        ArrayList<Position> li = new ArrayList<>();
        li = kn.calculateMovement(position);
        
        for(int i = 0; i < li.size(); i++){
            assertTrue(liExp.get(i).compare(li.get(i)));   
        }
            
        // creo knight in posizione generale nella scacchiera
        Position position2 = new Position(4,3);
        Knight kn2 = new Knight("KNIGHT",position2,0, new Chessboard());
        
        ArrayList<Position> liExp2 = new ArrayList<>();
        liExp2.add(new Position(5,5));
        liExp2.add(new Position(5,1));
        liExp2.add(new Position(6,4));
        liExp2.add(new Position(6,2));
        liExp2.add(new Position(2,4));
        liExp2.add(new Position(2,2));
        liExp2.add(new Position(3,1));
        liExp2.add(new Position(3,5));
                
        ArrayList<Position> li2 = new ArrayList<>();
        li2 = kn2.calculateMovement(position2);
        
        for(int i = 0; i < li2.size(); i++){
            assertTrue(liExp2.get(i).compare(li2.get(i)));
            
        }
        
        
                 
    }
    
    
}