/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playableCharacters;

/**
 * 
 * The Witch class represents a specific type of character within the game.
 * <p>
 * It inherits from the Character class and defines the unique properties 
 * of a Witch character, including custom energy, mental health, and superstition levels.
 * <p>
 *
 * @author Jorge Rojas
 * @author Ismael Marchena
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class Witch extends Character {

    /**
     * Constructs a witch character with specified attributes.
     * 
     * @param name the name of the witch.
     * @param energy the energy level of the witch.
     * @param mentalHealth the mental health level of the witch.
     * @param superstition the superstition level of the witch.
     */
    public Witch(String name, int energy, int mentalHealth, int superstition) {
        super(name, energy, mentalHealth, superstition);
    }

    /**
     * Constructs a witch character with default attributes.
     */
    public Witch() {
    }

    /**
     * Creates and returns a new Witch character with default attributes.
     * 
     * @return a new instance of Witch with preset attributes.
     */
    @Override
    public Character createCharacter() {
        return new Witch("Witch", 100, 100, 0);
    }

}
