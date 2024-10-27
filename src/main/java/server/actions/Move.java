/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.actions;

import game.Game;
import game.Player;
import game.MovementsLogic;


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
    private Game map;

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

        System.out.println("popopopooopopoo" + !moves.checkMovements(Game.getInstance().getMapClone(), player, direction));
        if (!moves.checkMovements(Game.getInstance().getMapClone(), player, direction)) {

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
        System.out.println("recibo posx" + player.getPositionX());
        System.out.println("recibo posy" + player.getPositionY());

        return "Move successful. " + player.getPosition()
                + ". Current energy: " + player.getCharacter().getEnergy();
    }
}
