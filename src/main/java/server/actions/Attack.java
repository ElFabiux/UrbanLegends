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
        if (player.getCharacter().getEnergy() < 5) {
            return "Attack failed. Not enough energy.";
        }

        player.getCharacter().modifyEnergy(-5);
        player.getCharacter().modifyMentalHealth(-10);
        player.getCharacter().modifySuperstition(5);

        return "Attack successful. Current stats: Energy: "
                + player.getCharacter().getEnergy()
                + ", Mental Health: " + player.getCharacter().getMentalHealth()
                + ", Superstition: " + player.getCharacter().getSuperstition();
    }
}
