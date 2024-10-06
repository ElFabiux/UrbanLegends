/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.actions;

import game.Game;
import characters.Character;
import game.Player;

/**
 *
 * @author joxan
 */
public class Move implements Command {

    private String direction;

    public Move(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String execute(Player player, Game game) {

        if (player.character.getEnergy() < 10) {
            return "Move failed. Not enough energy.";
        }

        player.character.modifyEnergy(-10);

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

        // Retornar un mensaje que indica éxito del movimiento
        return "Move successful. " + player.getPosition()
                + ". Current energy: " + player.character.getEnergy();
    }

}
