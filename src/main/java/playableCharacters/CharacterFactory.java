/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package playableCharacters;

/**
 * Factory interface for creating instances of characters.
 * <p>
 * The CharacterFactory interface defines a contract for classes that 
 * will implement character creation functionality, providing a method 
 * to create and return instances of Character.
 * </p>
 * 
 * @see Character
 * @author Jorge Rojas
 * @author Ismael Marchena 
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public interface CharacterFactory {
    
    /**
     * Creates and returns a new instance of a Character.
     * 
     * @return a new instance of Character.
     */
    Character createCharacter();
}