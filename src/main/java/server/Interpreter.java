package server;

import server.actions.Attack;
import server.actions.Move;
import game.Game;
import playableCharacters.Character;
import game.Player;

/**
 * The {@code Interpreter} class is responsible for interpreting commands
 * sent by players and executing the corresponding actions.
 * 
 * This class processes commands and applies their effects on the game state,
 * acting as an intermediary between the player's input and the game logic.
 * 
 * @author joxan
 */
public class Interpreter {

    
    private Game game;

    /**
     * Constructs an {@code Interpreter} with the specified {@code Game} instance.
     * 
     * @param game the game instance that this interpreter will affect
     */
    public Interpreter(Game game) {
        this.game = game;
    }

    /**
     * Interprets and executes a command based on the input string. 
     * 
     * If the command is unrecognized, it returns a message indicating an 
     * unknown command.
     * 
     * @param command the action to be performed.
     * @param direction the direction of the action (used with "move" command)
     * @param player the player performing the action
     * @return a message indicating the result of the command, such as success
     * or failure
     */
    public String interpret(String command, String direction, Player player) {
        switch (command) {
            case "move":
                Move moveCommand = new Move(direction);
                return moveCommand.execute(player, game);

            case "attack":
                Attack attackCommand = new Attack();
                return attackCommand.execute(player, game);
                
            case "get":
                return player.getPosition();

            default:
                // Return message for unknown command
                return "Unknown command.";
        }
    }
}
