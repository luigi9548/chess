/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.functionality.api;

public interface PositionInt {
    
    /**
     * Converts the position to a string representation in the format "column + row".
     *
     * @return A string representing the position, e.g., "a1".
     */
    String getStringPosition();

    /**
     * Converts the column number to a corresponding letter using a substring.
     *
     * @return The letter corresponding to the column number.
     */
    char numToLetterBySubstr();
}
