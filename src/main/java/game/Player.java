package game;

import playableCharacters.Character;
import characters.Character;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game with attributes such as name, position, 
 * and a list of active missions.
 * Provides methods for managing player movement and mission progress.
 * 
 * @author joxan
 */
public class Player {

    
    private int positionX;
    private int positionY;

   
    private List<Mission> activeMissions;
    private Character character;
    private String ip;
    private String name;

    /**
     * Constructs a Player with the specified details.
     * 
     * @param name      The name of the player.
     * @param ip        The IP address of the player.
     * @param positionX The x-coordinate of the player on the map.
     * @param positionY The y-coordinate of the player on the map.
     * @param character The character associated with the player.
     */
    public Player(String name, String ip, int positionX, int positionY, 
            Character character) {
        this.name = name;
        this.ip = ip;
        this.positionX = positionX;
        this.positionY = positionY;
        this.character = character;
        this.activeMissions = new ArrayList<>();
    }

    /**
     * Accepts a mission and adds it to the player's list of active missions.
     * 
     * @param mission The mission to be added.
     */
    public void acceptMission(Mission mission) {
        activeMissions.add(mission);
    }

    /**
     * Marks a specified mission as completed.
     * 
     * @param mission The mission to be marked as completed.
     */
    public void completeMission(Mission mission) {
        mission.markAsCompleted();
        System.out.println("Mission '" + mission.getTitle() + "' completed!");
    }

    /**
     * Gets the list of active missions for the player.
     * 
     * @return List of active missions.
     */
    public List<Mission> getActiveMissions() {
        return activeMissions;
    }

    /**
     * Gets the character associated with the player.
     * 
     * @return The character object associated with the player.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Gets the IP address of the player.
     * 
     * @return The player's IP address.
     */
    public String getIp() {
        return ip;
    }

    public void moveDown() {
        if(this.positionY > 10) return;
        this.positionY ++;
    }

    /**
     * Gets the x-coordinate of the player's position on the map.
     * 
     * @return The x-coordinate of the player's position.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Gets the y-coordinate of the player's position on the map.
     * 
     * @return The y-coordinate of the player's position.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Returns a formatted string of the player's current position.
     * 
     * @return The player's position as a formatted string.
     */
    public String getPosition() {
        return "(" + positionX + ", " + positionY + ")";
    }

    /**
     * Moves the player down by increasing the y-coordinate.
     */
    public void moveDown() {
        if (this.positionY > 10) {
            return;
        }
        this.positionY++;
    }

    /**
     * Moves the player left by decreasing the x-coordinate.
     */
    public void moveLeft() {
        if (this.positionX <= 0) {
            return;
        }
        this.positionX--;
    }

    /**
     * Moves the player right by increasing the x-coordinate.
     */
    public void moveRight() {
        if (this.positionX > 10) {
            return;
        }
        this.positionX++;
    }

    /**
     * Moves the player up by decreasing the y-coordinate.
     */
    public void moveUp() {
        if (this.positionY <= 0) {
            return;
        }
        this.positionY--;
    }

    /**
     * Sets the character associated with the player.
     * 
     * @param character The character to associate with the player.
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    
    public void setIp(String ip) {
        this.ip = ip;
    }

    
    public void setName(String name) {
        this.name = name;
    }

   
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

 
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
