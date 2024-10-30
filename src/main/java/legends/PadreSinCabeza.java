/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends;

import playableCharacters.Character;

/**
 * Legend class representing the "Padre Sin Cabeza" character.
 * 
 * @author jorge
 * @author fabian
 * @author joxan
 * @author melani
 * @author ismael
 */
public class PadreSinCabeza extends Legend {

    /**
     * Constructor for creating a Padre Sin Cabeza instance with specified 
     * attributes.
     * 
     * @param name The name of the legend.
     * @param positionX The X position on the map.
     * @param positionY The Y position on the map.
     */
    public PadreSinCabeza(String name, int positionX, int positionY) {
        super(name, positionX, positionY);
    }

    /**
     * Creates a new instance of the Padre Sin Cabeza legend with predefined 
     * attributes.
     * 
     * @return A new instance of Padre Sin Cabeza.
     */
    @Override
    public Legend createLegend() {
        return new PadreSinCabeza("Father", 8,3);
    }

    /**
     * Performs an attack on a specified character.
     * 
     * @param character The character to be attacked.
     */
    @Override
    public void attack(Character character) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}