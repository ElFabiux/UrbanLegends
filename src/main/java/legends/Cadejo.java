/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends;

import characters.Character;

/**
 *
 * @author jorge
 */
public class Cadejo extends Legend {

    public Cadejo(String name, int positionX, int positionY) {
        super(name, positionX, positionY);
    }

    @Override
    public void attack(Character character) {
        System.out.println("Cadejo Attack");
        //   Un Cazador que explora un cementerio durante la noche se topa con "El Cadejos". 
        //Dependiendo de su nivel de superstición, puede recibir ayuda del espíritu protector o ser atacado. 

        if (character.getName() == "Hunter") {
           character.modifyMentalHealth(
                   character.getSuperstition() > 75 ? -30 : +30);
        }else{
            character.modifyMentalHealth(-30);
        }
 
    }

}
