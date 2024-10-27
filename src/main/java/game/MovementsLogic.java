/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.Arrays;

/**
 *  MovementsLogic handles the logic for player movement and interaction 
 *  with the game environment. It includes methods to validate movement
 *  directions, check proximity to NPCs, and detect blocked paths on the map.
 * 
 * @author jorge
 */
public class MovementsLogic {

    private final char characterNPC = 'N';
    private final char[] blockedGrids = {'R', 'T', 'H', 'O', 'C', 'S', 'W', 'P', 'N', characterNPC};
    private boolean isCloseToNpc = false;

    /**
     * Constructor for MovementsLogic class.
     */
    public MovementsLogic() {
    }

    /**
     * Gets the array of blocked grid characters.
     * 
     * @return An array of characters representing blocked grid elements
     */
    public char[] getBlockedGrids() {
        return blockedGrids;
    }

    /**
     * Checks if the player is close to an NPC
     * 
     * @return true if the player is near an NPC, false otherwise.
     */
    public boolean isIsCloseToNpc() {
        return isCloseToNpc;
    }

    /**
     * Sets the proximity status to an NPC.
     * 
     * @param isCloseToNpc true if the player is near an NPC, false otherwise
     */
    public void setIsCloseToNpc(boolean isCloseToNpc) {
        this.isCloseToNpc = isCloseToNpc;
    }

    /**
     * This method checks if the player's movement in the specified direction is
     * valid based on the current map and player's position
     *
     * @param map A 2D array representing the game map
     * @param player The player object with current position data
     * @param direction The direction in which the player wants to move ("up",
     * "down", "right", "left").
     * @return true if the movement is valid, false otherwise
     */
    public boolean checkMovements(String[][] map, Player player, String direction) {
        System.out.println("direction: " + direction);
     
        int oldX = player.getPositionX();
        int oldY = player.getPositionY();
        int[] newPos = getNewPosition(oldX, oldY, direction);
        int posX = newPos[0];
        int posY = newPos[1];

        if (isOutOfBounds(map, posX, posY)) {
            System.out.println("out");
            return true;
        }

        isCloseToNpc = playerCloseToNpc(map, posX, posY);

        System.out.println("newPos:" + posX);
        System.out.println("newPosY:" + posY);

        if (isBlocked(map[posY][oldX], 0) || isBlocked(map[oldY][posX], 0)) {
            System.out.println("lalala" + map[posY][oldX]);
            System.out.println("block");
            return true;
        }

        return false;
    }

    /**
     * Checks if the player is close to an NPC in any of the adjacent tiles It
     * inspects the tiles around the player's current position (up, down, left,
     * right) o determine if any of them contain the NPC character
     *
     * @param mapA 2D array representing the game map.
     * @param posX The player's current X-coordinate on the map
     * @param posY The player's current Y-coordinate on the map
     * @return true if the player is near an NPC, false otherwise.
     */
    private boolean playerCloseToNpc(String[][] map, int posX, int posY) {

        if (!isOutOfBounds(map, posX + 1, posY) && map[posY][posX + 1].charAt(0) == characterNPC
                || !isOutOfBounds(map, posX - 1, posY) && map[posY][posX - 1].charAt(0) == characterNPC
                || !isOutOfBounds(map, posX, posY + 1) && map[posY + 1][posX].charAt(0) == characterNPC
                || !isOutOfBounds(map, posX, posY - 1) && map[posY - 1][posX].charAt(0) == characterNPC) {
            System.out.println("ESTOY CERCAR");
            return true;
        }
        System.out.println("stoy lejos");
        return false;
    }

    /**
     * This method checks if the new position is out of the map bounds
     *
     * @param map The game map
     * @param posX The new X-coordinate of the player
     * @param posY The new Y-coordinate of the player
     * @return true if the position is out of bounds, false otherwise
     */
    private boolean isOutOfBounds(String[][] map, int posX, int posY) {
        if (posX >= map.length || posX < 0 || posY >= map[0].length || posY < 0) {
            return true;
        }
        return false;
    }

    /**
     * This method calculates the new position based on the current position and
     * the direction of movement
     *
     * @param posX The current X-coordinate of the player
     * @param posY current Y-coordinate of the player
     * @param direction The direction of movement ("up", "down", "right",
     * "left")
     * @return An array containing the new X and Y coordinates
     */
    private int[] getNewPosition(int posX, int posY, String direction) {
        switch (direction) {
            case "up":
                posY--;
                break;
            case "down":
                posY++;
                break;
            case "right":
                posX++;
                break;
            case "left":
                posX--;
                break;
        }
        return new int[]{posX, posY};
    }

    /**
     * This method checks if a grid contains a blocked character
     *
     * @param grid The grid element to check
     * @param index The current index in the blockedGrids array
     * @return true if the grid is blocked, false otherwise
     */
    private boolean isBlocked(String grid, int index) {
        if (index >= blockedGrids.length) {
            return false;
        }

        if (grid.charAt(0) == blockedGrids[index]) {
            return true;
        }

        return isBlocked(grid, index + 1);
    }
}
