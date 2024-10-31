/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playableCharacters;

/**
 * Represents a hunter character in the game.
 * <p>
 * The Hunter class extends Character and provides a concrete
 * implementation of the character creation specific to a hunter.
 *
 * @author Jorge Rojas
 * @author Ismael Marchena
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class Hunter extends Character {

    /**
     * Constructs a hunter character with specified attributes.
     *
     * @param name the name of the hunter.
     * @param energy the energy the energy level of the hunter.
     * @param mentalHealth the mental health level of the hunter.
     * @param superstition the superstition level of the hunter.
     */
    public Hunter(String name, int energy, int mentalHealth, int superstition) {
        super(name, energy, mentalHealth, superstition);
    }

    /**
     * Constructs a hunter character with default attributes.
     */
    public Hunter() {
    }

    /**
     * Creates and returns a new Hunter character with default attributes.
     *
     * @return a new instance of Hunter with preset name, energy, mental health,
     * and superstition values
     */
    @Override
    public Character createCharacter() {
        return new Hunter("Hunter", 100, 100, 0);
    }

}
