/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.actions;

/**
 *
 * @author joxan
 */
import game.Game;
import characters.Character;

public class Attack implements Command {

    @Override
    public String execute(Character character, Game game) {
        if (character.getEnergy() < 5) {
            return "Attack failed. Not enough energy.";
        }

        character.modifyEnergy(-5);
        character.modifyMentalHealth(-10);
        character.modifySuperstition(5);

        return "Attack successful. Current stats: Energy: "
                + character.getEnergy()
                + ", Mental Health: " + character.getMentalHealth()
                + ", Superstition: " + character.getSuperstition();
    }
}
