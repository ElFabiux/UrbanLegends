/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters.factory;

import characters.Character;
import characters.Witch;

/**
 *
 * @author jorge
 */
public class WitchFactory implements CharacterFactory {

    @Override
    public Character createCharacter() {
        return new Witch("Matilda", 80, 90, 70, 1, 1);
    }
}