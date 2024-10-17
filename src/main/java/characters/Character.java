/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters;

/**
 *
 * @author jorge
 */
public abstract class Character {

    private String name;
    private int energy;
    private int mentalHealth;
    private int superstition;

    public Character(String name, int energy, int mentalHealth, int superstition) {

        this.name = name;
        this.energy = energy;
        this.mentalHealth = mentalHealth;
        this.superstition = superstition;

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
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    /**
     * Modifies the player mental health
     *
     * @param value value that modifies mental health
     */
    public void modifyMentalHealth(int value) {
        this.mentalHealth += value;
        if (this.mentalHealth < 0) {
            this.mentalHealth = 0;
        }
    }

    /**
     * Modifies the player superstition
     *
     * @param value value that modifies supertition
     */
    public void modifySuperstition(int value) {
        this.superstition += value;
        if (this.superstition < 0) {
            this.superstition = 0;
        }
    }

//    public boolean encounterWithLegend(Legend legend) {
//        return this.positionX == legend.getPositionX()
//                && this.positionY == legend.getPositionY();
//    }
//
//    // Verify the position
//    /**
//     * Check the player's position
//     *
//     * @param x x axis position
//     * @param y y axis position
//     * @return
//     */
//    public boolean isAtPosition(int x, int y) {
//        return this.positionX == x && this.positionY == y;
//    }
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setMentalHealth(int mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    public void setSuperstition(int superstition) {
        this.superstition = superstition;
    }


}
