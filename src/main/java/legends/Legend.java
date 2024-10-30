/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends;

import playableCharacters.Character;

/**
 * Abstract class representing a Legend with specific position and name 
 * attributes.
 * 
 * @author jorge
 * @author fabian
 * @author joxan
 * @author melani
 * @author ismael
 */
public abstract class Legend implements LegendFactory{

    private int positionX;
    private int positionY;
    private String name;
    
    /**
     * Constructor to initialize a Legend with a name and coordinates.
     * 
     * @param name The name of the legend.
     * @param positionX The X position of the legend.
     * @param positionY The Y position of the legend.
     */
    public Legend(String name, int positionX, int positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Gets the X position of the legend.
     * 
     * @return the X position.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Gets the Y position of the legend.
     * 
     * @return the Y position.
     */
    public int getPositionY() {
        return positionY;
    }
    
    /**
     * Gets the name of the legend.
     * 
     * @return The name of the legend.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the legend.
     * 
     * @param name Name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the X position of the legend.
     * 
     * @param positionX The X position to set.
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Sets the Y position of the legend.
     * 
     * @param positionY The Y position to set.
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Abstract method for attacking another character.
     * 
     * @param character The character to be attacked.
     */
    public abstract void attack(Character character);
}