/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters.factory;

import playableCharacters.Character;
import playableCharacters.Hunter;

/**
 *
 * @author jorge
 */
public class HunterFactory implements CharacterFactory {

      @Override
    public Character createCharacter() {
        return new Hunter("Fabian manco", 100, 80, 50);
    }


}