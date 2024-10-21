/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package characters.factory;

import playableCharacters.Character;
import playableCharacters.Researcher;

/**
 *
 * @author jorge
 */
public class ResearcherFactory implements CharacterFactory {

    @Override
    public Character createCharacter() {
        return new Researcher("Isma Pelon", 70, 100, 40);
    }
}