/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playableCharacters;

/**
 * Abstract base class representing a playable character with core attributes
 * such as energy, mental health, and superstition level. This class implements
 * the CharacterFactory interface, defining the essential structure and behavior
 * of characters in the game.
 * <p>
 * The Character class allows modification of its attributes through specific
 * methods and provides accessor and mutator methods for these attributes.
 * </p>
 *
 * @author Jorge Rojas
 * @author Ismael Marchena 
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 * 
 */
public abstract class Character implements CharacterFactory {

    private int energy;
    private int mentalHealth;
    private int superstition;
    private String name;

    /**
     * Constructor for creating a character with specific attribute values.
     * 
     * @param name the name of the character.
     * @param energy the initial energy level of the character.
     * @param mentalHealth the initial mental health of the character.
     * @param superstition the initial superstition level of the character.
     */
    public Character(String name, int energy, int mentalHealth,
            int superstition) {

        this.name = name;
        this.energy = energy;
        this.mentalHealth = mentalHealth;
        this.superstition = superstition;
    }

    /**
     * Default constructor for creating a character without setting initial 
     * values.
     */
    public Character() {
    }

    /**
     * Modifies the character's energy by a specified value. Energy cannot be 
     * less than 0.
     *
     * @param value value that modifies energy.
     */
    public void modifyEnergy(int value) {
        this.energy += value;
        if (this.energy < 0) this.energy = 0;
        
    }

    /**
     * Modifies the character's mental health by a specified value. Mental 
     * health cannot be less than 0.
     *
     * @param value value that modifies mental health.
     */
    public void modifyMentalHealth(int value) {
        this.mentalHealth += value;
        if (this.mentalHealth < 0) this.mentalHealth = 0;
        
    }

    /**
     * Modifies the character's superstition level by a specified value. 
     * Superstition level cannot be less than 0.
     *
     * @param value value that modifies supertition.
     */
    public void modifySuperstition(int value) {
        this.superstition += value;
        if (this.superstition < 0) this.superstition = 0;
    }
    
    /**
     * Returns the character's current energy level.
     * 
     * @return the current energy level.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Returns the character's current mental health.
     * 
     * @return the current mental health.
     */
    public int getMentalHealth() {
        return mentalHealth;
    }

    /**
     * Returns the character's current superstition level.
     * 
     * @return the current superstition level.
     */
    public int getSuperstition() {
        return superstition;
    }

    /**
     * Returns the character's name.
     * 
     * @return the name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the character's energy level.
     * 
     * @param energy the energy level to set.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Sets the character's mental health level.
     * 
     * @param mentalHealth the mental health level to set.
     */
    public void setMentalHealth(int mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    /**
     * Sets the character's superstition level.
     * 
     * @param superstition the superstition level to set.
     */
    public void setSuperstition(int superstition) {
        this.superstition = superstition;
    }

    /**
     * Sets the character's name.
     * 
     * @param name the name to set for the character.
     */
    public void setName(String name) {
        this.name = name;
    }

}
