/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playableCharacters;

/**
 * 
 * The Witch class represents a specific type of character within the game.
 * It inherits from the Character class and defines the unique properties 
 * of a Witch character, including custom energy, mental health, and superstition levels.
 *
 * @author joxan
 */
public class Witch extends Character {

    public Witch(String name, int energy, int mentalHealth, int superstition) {
        super(name, energy, mentalHealth, superstition);
    }

    public Witch() {
    }

    @Override
    public Character createCharacter() {
        return new Witch("Witch", 80, 90, 70);
    }

}
