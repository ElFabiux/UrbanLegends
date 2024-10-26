/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.actions;

import game.Game;
import game.Player;
import playableCharacters.Character;
import game.Player;
import game.MovementsLogic;
import game.GameMap;

/**
 * The {@code Move} class represents the action of moving a player in the game.
 * It implements the {@code Command} interface, allowing it to execute the move 
 * action based on the player's input and direction. The player's energy is 
 * reduced by 1 unit after a move action.
 * 
 * This command changes the player's position based on the specified direction.
 * Directions include "up", "down", "left", and "right".
 * 
 * If the player's energy is below 10, the move will fail.
 * 
 * @author joxan
 */
public class Move implements Command {

    private String direction;
    private final MovementsLogic moves = new MovementsLogic();
    private GameMap map;

// Este es el mapa real   map.getGameMap () 
//Esto esta de pruba, es el mapa que espero
    private static final String[][] finalMap = {
        {"R1", "G", "G", "G", "G", "G", "G", "G", "G", "T2", "G", "G", "G", "G", "G", "G", "G", "G", "R1", "G", "G", "T6", "G", "G", "T3", "G", "T7", "G", "G", "T2", "G", "G", "G", "G", "G", "T5"},
        {"R1", "T1", "T1", "G", "G", "G", "N1", "G", "T2", "G", "G", "G", "H6", "H4", "G", "G", "T3", "T3", "R1", "T1", "G", "G", "G", "G", "T4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "R1", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "G"},
        {"R1", "G", "L", "G", "G", "H5", "T", "H5", "G", "G", "T3", "T3", "T3", "T3", "T3", "T3", "T3", "G", "R1", "G", "G", "G", "G", "G", "G", "G", "T2", "G", "T1", "G", "G", "G", "T3", "G", "G", "G"},
        {"R1", "G", "L", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "R1", "G", "G", "G", "G", "T5", "G", "G", "G", "G", "G", "T1", "G", "G", "G", "T3", "T6", "G"},
        {"R1", "G", "L", "G", "G", "G", "T2", "G", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "R1", "G", "G", "G", "G", "G", "G", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "H3", "G", "G", "G", "G", "G", "G", "G", "G", "H5", "H5", "G", "G", "G", "G", "R1", "G", "G", "G", "G", "G", "G", "T3", "T3", "G", "G", "G", "G", "T7", "G", "G", "G", "G"},
        {"R1", "T2", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "G", "G", "R1", "G", "T2", "G", "G", "T6", "G", "G", "G", "T1", "G", "G", "G", "G", "T5", "G", "G", "G"},
        {"R1", "G", "L", "T3", "T3", "G", "G", "H4", "L", "H1", "G", "T3", "T3", "H2", "G", "H3", "G", "G", "R1", "G", "G", "T2", "G", "G", "G", "G", "G", "G", "G", "G", "T6", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "T3", "G", "G", "G", "T3", "L", "H5", "G", "T3", "T3", "G", "G", "G", "G", "G", "R1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "H1", "G", "G", "G", "H6", "L", "G", "G", "T3", "G", "G", "T1", "G", "G", "G", "R1", "G", "G", "G", "G", "T7", "G", "G", "T2", "G", "G", "G", "G", "G", "T3", "G", "T2", "G"},
        {"R1", "G", "L", "T1", "G", "G", "G", "T3", "L", "T3", "G", "G", "T2", "G", "G", "G", "G", "G", "R1", "G", "T4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T1", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "G", "G", "G", "G", "G", "L", "H6", "G", "G", "G", "G", "G", "T1", "G", "G", "R1", "T3", "T3", "G", "G", "G", "T7", "G", "G", "G", "G", "G", "G", "T3", "G", "G", "G", "G"},
        {"R1", "G", "L", "H4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "R1", "T3", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T5", "G", "G", "G"},
        {"R5", "R3", "L", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R1", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "T3", "T3", "T3", "G", "G", "G", "G", "G"},
        {"R4", "R2", "L", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R1", "T5", "G", "G", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "G", "G", "T2", "G", "G"},
        {"R1", "G", "L", "T", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "R1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T6", "G"},
        {"R1", "G", "L", "G", "G", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "R1", "G", "G", "T7", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "T5", "G", "G", "G", "G"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "T3", "T3", "T3", "W", "W", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "G", "G", "G", "G", "G", "O5", "G", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "W"},
        {"O5", "G", "O5", "G", "G", "G", "G", "T3", "C", "T3", "G", "G", "G", "G", "G", "G", "G", "T3", "F", "F", "F", "F", "F", "F", "F", "F", "O", "O", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "T3", "G", "G", "G", "G", "G", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "F", "F", "F", "W", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "W"},
        {"O5", "T3", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O2", "G", "T3", "G", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"G", "G", "G", "G", "O5", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "T3", "G", "O2", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "W", "F", "F", "F", "F", "F", "F", "F"},
        {"G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O2", "G", "O1", "G", "G", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "T3", "T3", "G", "G", "G", "T3", "G", "O1", "G", "G", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "W", "F", "F", "F", "F", "F"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "T3", "G", "O3", "G", "G", "G", "O3", "G", "T3", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"O5", "G", "G", "G", "G", "G", "O5", "G", "G", "O3", "G", "G", "G", "T3", "G", "G", "G", "G", "W", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "G", "G", "G", "O3", "G", "G", "G", "G", "G", "G", "G", "G", "O3", "G", "O5", "G", "O1", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"O5", "O5", "G", "G", "G", "G", "G", "O5", "G", "O2", "G", "O1", "G", "G", "G", "G", "G", "O3", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "G", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "O3", "G", "G", "G", "O1", "G", "O1", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T3", "O5", "G", "O2", "G", "G", "G", "O5", "F", "W", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"O5", "G", "T3", "T3", "G", "T3", "G", "G", "O1", "G", "G", "G", "G", "O3", "G", "O2", "O5", "O5", "W", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "W"},};
    

    /**
     * Constructs a new {@code Move} command with the specified direction.
     * 
     * @param direction the direction in which the player will move
     */
    public Move(String direction) {
        this.direction = direction;
    }

    /**
     * Gets the current direction of movement.
     * 
     * @return the direction in which the player will move
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets a new direction for the movement.
     * 
     * @param direction the new direction for the player to move
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Executes the move command, modifying the player's position within the game
     * based on the provided direction. If the player's energy is lower than 10, 
     * the move will fail. Otherwise, the player's energy is reduced by 1 for 
     * each move.
     * @param player the player who is performing the move action
     * @param game the game instance where the action takes place
     * @return a string message indicating whether the move was successful or
     * failed
     */
    @Override
    public String execute(Player player, Game game) {

        
        if (player.getCharacter().getEnergy() < 10) {
            return "Move failed. Not enough energy.";
        }
        System.out.println("recibo posx" + player.getPositionX());
        System.out.println("recibo posy" + player.getPositionY());

        System.out.println("popopopooopopoo" + !moves.checkMovements(finalMap, player, direction));
        if (!moves.checkMovements(finalMap, player, direction)) {

            player.getCharacter().modifyEnergy(-1);

            switch (direction) {
                case "up":
                    player.moveUp();
                    break;
                case "down":
                    player.moveDown();
                    break;
                case "left":
                    player.moveLeft();
                    break;
                case "right":
                    player.moveRight();
                    break;
                default:
                    return "Invalid direction. Use up, down, left, or right.";
            }

        }

        return "Move successful. " + player.getPosition()
                + ". Current energy: " + player.getCharacter().getEnergy();
    }
}
