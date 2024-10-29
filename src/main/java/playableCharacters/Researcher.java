/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playableCharacters;

/**
 * Represents a researcher character in the game.
 * <p>
 * The Researcher class extends Character and implements the
 * creation of a researcher character.
 * </p>
 *
 * @author Jorge Rojas
 * @author Ismael Marchena
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class Researcher extends Character {

    /**
     * Constructs a researcher character with specified attributes.
     * 
     * @param name the name of the researcher.
     * @param energy the energy level of the researcher.
     * @param mentalHealth the mental health level of the researcher.
     * @param superstition the superstition level of the researcher.
     */
    public Researcher(String name, int energy, int mentalHealth,
            int superstition) {
        super(name, energy, mentalHealth, superstition);
    }

    /**
     * Constructs a researcher character with default attributes.
     */
    public Researcher() {
    }

    /**
     * Creates and returns a new Researcher character with default attributes.
     * 
     * @return  a new instance of Researcher with preset attributes
     */
    @Override
    public Character createCharacter() {
        return new Researcher("Researcher", 70, 100, 40);
    }

}
