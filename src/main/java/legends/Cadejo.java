/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends;

import playableCharacters.Character;

/**
 * Legend class representing the "Cadejo" character.
 * 
 * @author jorge
 * @author fabian
 * @author joxan
 * @author melani
 * @author ismael
 */
public class Cadejo extends Legend {
    
    /**
     * Constructor for creating a Cadejo instance with specified attributes.
     * 
     * @param name The name of the legend.
     * @param positionX The X position on the map.
     * @param positionY The Y position on the map.
     */
    public Cadejo(String name, int positionX, int positionY) {
        super(name, positionX, positionY);
    }
    
    /**
     * Creates a new instance of the Cadejo legend with predefined attributes.
     * 
     * @return A new instance of Cadejo.
     */
    @Override
    public Legend createLegend() {
         return new Cadejo("Cadejin", 3,6);
    }

    /**
     * Performs an attack on a specified character.
     * 
     * @param character The character to be attacked.
     */
    @Override
    public void attack(Character character) {
        System.out.println("Cadejo Attack");

        if (character.getName() == "Hunter") {
           character.modifyMentalHealth(
                   character.getSuperstition() > 75 ? -30 : +30);
        }else{
            character.modifyMentalHealth(-30);
        }
 
    }
}