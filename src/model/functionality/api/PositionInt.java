/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.functionality.api;

import model.functionality.impl.Position;

/**
 *
 * @author luigi
 */
public interface PositionInt {

    // metodo per comparare una posizione con un'altra
    boolean compare(Position p);

    // metodo per convertire la posizione in una stringa
    String getStringPosition();

    // metodo per convertire il numero di colonna in lettere
    char numToLetterBySubstr();
    
}
