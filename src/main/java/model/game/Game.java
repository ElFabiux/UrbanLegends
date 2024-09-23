/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.game;

import model.Character.Character;
import java.util.Vector;

/**
 *
 * @author joxan
 */
public class Game {

   
    private static Game instance;

    private Vector<Character> characters = new Vector<>(3);

   
    private Game() {
    }

    
    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Methods for game logic
    public void addCharacter(Character c) {
        if (characters.size() < 3) {
            characters.add(c);
        }
    }

    public void modifyCharacterStats(String name, int energy, int mentalHealth, 
            int superstition) {
        for (Character c : characters) {
            if (c.getName().equals(name)) {
                c.modifyEnergy(energy);
                c.modifyMentalHealth(mentalHealth);
                c.modifySuperstition(superstition);
            }
        }
    }

//    public void dayNightCycle() {
//  
//    }

//    public void manageLegends() {
//       
//    }
}