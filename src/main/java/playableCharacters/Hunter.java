/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package playableCharacters;

/**
 *
 * @author joxan
 */
public class Hunter extends Character {

    public Hunter(String name, int energy, int mentalHealth, int superstition) {
        super(name, energy, mentalHealth, superstition);
    }

    public Hunter() {
    }

    @Override
    public Character createCharacter() {
        return new Hunter("Hunter", 100, 80, 50);
    }

}
