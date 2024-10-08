/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Legends;

import characters.Character;

/**
 *
 * @author joxan
 */
abstract public class Legend {// interfaz o  abstracta 
    private String name;
    private String location; 

    public Legend(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void interactWithCharacter(Character character) {
        if (character.getSuperstition() > 50) {
            character.modifyMentalHealth(-20);
            System.out.println(character.getName() + " was affected by " + name);
        } else {
            System.out.println(character.getName() + " escaped from " + name);
        }
    }
}

