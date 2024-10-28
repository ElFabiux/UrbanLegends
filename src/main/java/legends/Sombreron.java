/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends;

import playableCharacters.Character;

/**
 *
 * @author jorge
 */
public class Sombreron extends Legend {

    public Sombreron(String name, int positionX, int positionY) {
        super(name, positionX, positionY);
    }

  
    @Override
    public void attack(Character character) {
       
    }

    @Override
    public Legend createLegend() {
        return new Sombreron("Ajua", 8,1);
    }

}
