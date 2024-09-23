/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Character;

/**
 * is a mold of a character like witch, hunter or researcherm, will be methods 
 *
 * @author joxan
 */
abstract public class Character {

    private String name;
    private int energy;
    private int mentalHealth;
    private int superstition;



    public Character(String name, int energy, int mentalHealth,
            int superstition) {
        this.name = name;
        this.energy = energy;
        this.mentalHealth = mentalHealth;
        this.superstition = superstition;
    }

    public Character() {
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public void modifyEnergy(int value) {
        this.energy += value;
    }

    public int getMentalHealth() {
        return mentalHealth;
    }

    public void modifyMentalHealth(int value) {
        this.mentalHealth += value;
    }

    public int getSuperstition() {
        return superstition;
    }

    public void modifySuperstition(int value) {
        this.superstition += value;
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

    @Override
    public String toString() {
        return "Character{" + "name=" + name + ", energy=" + energy
                + ", mentalHealth=" + mentalHealth + ", superstition="
                + superstition + '}';
    }

}
