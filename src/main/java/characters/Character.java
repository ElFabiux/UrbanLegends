/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters;

import legends.Legend;

/**
 *
 * @author jorge
 */
public abstract class Character {

    private String name;
    private int energy;
    private int mentalHealth;
    private int superstition;
    private int positionX;
    private int positionY;

    public Character(String name, int energy, int mentalHealth, 
            int superstition, int positionX, int positionY) {
        this.name = name;
        this.energy = energy;
        this.mentalHealth = mentalHealth;
        this.superstition = superstition;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Character() {
    }

    /**
     * Modifies the player energy
     *
     * @param value value that modifies energy
     */
    public void modifyEnergy(int value) {
        this.energy += value;
        if (this.energy < 0) this.energy = 0;
    }

    /**
     * Modifies the player mental health
     *
     * @param value value that modifies mental health
     */
    public void modifyMentalHealth(int value) {
        this.mentalHealth += value;
        if (this.mentalHealth < 0) this.mentalHealth = 0;
    }

    /**
     * Modifies the player superstition
     *
     * @param value value that modifies supertition
     */
    public void modifySuperstition(int value) {
        this.superstition += value;
        if (this.superstition < 0) this.superstition = 0;
    }

    public boolean encounterWithLegend(Legend legend) {
        return this.positionX == legend.getPositionX() 
                && this.positionY == legend.getPositionY();
    }

    //Movements //No deberian estas en el jugador?
    /**
     * Move player up
     */
    public void moveUp() {
        this.positionY += 1;
    }

    /**
     * Mode player down
     */
    public void moveDown() {
        this.positionY -= 1;
    }

    /**
     * Move player left
     */
    public void moveLeft() {
        this.positionX -= 1;
    }

    /**
     * Move player right
     */
    public void moveRight() {
        this.positionX += 1;
    }

    // Verify the position
    /**
     * Check the player's position
     *
     * @param x x axis position
     * @param y y axis position
     * @return
     */
    public boolean isAtPosition(int x, int y) {
        return this.positionX == x && this.positionY == y;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMentalHealth() {
        return mentalHealth;
    }

    public int getSuperstition() {
        return superstition;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public String getPosition() {
        return "(" + positionX + ", " + positionY + ")";
    }

    @Override
    public String toString() {
        return "Character{" + "name=" + name + ", energy=" + energy
                + ", mentalHealth=" + mentalHealth + ", superstition=" + 
                superstition
                + ", position=" + getPosition() + '}';
    }
}
