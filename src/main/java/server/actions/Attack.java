/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.actions;

import game.Game;
import playableCharacters.Character;
import game.Player;

/**
 * The {@code Attack} class represents an action command where a player performs 
 * an attack.
 * It implements the {@code Command} interface, allowing the attack action to be
 * executed.
 * 
 * When an attack is executed, the player's energy is reduced by 5, mental
 * health by 10,
 * and superstition increases by 5 points.
 * 
 * If the player's energy is below 5, the attack will fail.
 * 
 * @author joxan
 */
public class Attack implements Command {

    /**
     * Executes the attack command, which modifies the player's stats:
     * - Reduces energy by 5 points.
     * - Reduces mental health by 10 points.
     * - Increases superstition by 5 points.
     * 
     * If the player's energy is less than 5, the attack fails.
     * 
     * @param player the player who performs the attack
     * @param game the game instance where the action is performed
     * @return a message indicating whether the attack was successful or failed,
     * along with the player's updated stats
     */
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
