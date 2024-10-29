/*
 * This interface defines the structure for all command actions
 * that can be executed by a player in the game. Each specific command,
 * such as move or attack, will implement this interface and provide 
 * its own behavior.
 */
package server.actions;

import game.Game;
import game.Player;

/**
 * Command interface for executing player actions in the game.
 * 
 * Any class that implements this interface will define specific
 * actions to be performed by the player on the game, such as 
 * movement or attack.
 * 
 */
public interface Command {

    /**
     * Executes a command based on the player's input.
     * 
     * @param player the player performing the action.
     * @param game the game instance where the action takes place.
     * @return a string message indicating the result of the command.
     */
    String execute(Player player, Game game);
}
