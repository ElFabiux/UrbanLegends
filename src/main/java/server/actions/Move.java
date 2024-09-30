/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.actions;

import game.Game;
import characters.Character;

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
    public String execute(Character character, Game game) {

        if (character.getEnergy() < 10) {
            return "Move failed. Not enough energy.";
        }

        character.modifyEnergy(-10);

        switch (direction) {
            case "up":
                character.moveUp();
                break;
            case "down":
                character.moveDown();
                break;
            case "left":
                character.moveLeft();
                break;
            case "right":
                character.moveRight();
                break;
            default:
                return "Invalid direction. Use up, down, left, or right.";
        }

        // Retornar un mensaje que indica éxito del movimiento
        return "Move successful. " + character.getPosition()
                + ". Current energy: " + character.getEnergy();
    }

}
