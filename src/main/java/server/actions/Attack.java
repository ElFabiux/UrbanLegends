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
import game.Player;

public class Attack implements Command {

    @Override
    public String execute(Player player, Game game) {
        if (player.character.getEnergy() < 5) {
            return "Attack failed. Not enough energy.";
        }

        player.character.modifyEnergy(-5);
        player.character.modifyMentalHealth(-10);
        player.character.modifySuperstition(5);

        return "Attack successful. Current stats: Energy: "
                + player.character.getEnergy()
                + ", Mental Health: " + player.character.getMentalHealth()
                + ", Superstition: " + player.character.getSuperstition();
    }
}
