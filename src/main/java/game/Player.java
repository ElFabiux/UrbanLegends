package game;

import java.util.ArrayList;
import java.util.List;

import playableCharacters.Character;
import playableCharacters.*;

/**
 * Represents a player in the game with attributes such as name, position, 
 * and a list of active missions.
 * Provides methods for managing player movement and mission progress.
 * 
 * @author joxan
 * @author melani
 * @author fabian
 * @author jorge
 * @author ismael
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
    
    /**
     * Gets the name of the player.
     * 
     * @return The player's name.
     */
    public String getName(){
        return  this.name;
    }
    
    /**
     * Returns a formatted string of the player's current position.
     * 
     * @return The player's position as a formatted string.
     */
    public String getPosition() {
        return "(" + positionX + "," + positionY + ")";
    }
    
    
    
    /**
     * Accepts a mission and adds it to the player's list of active missions.
     * 
     * @param mission The mission to be added.
     */
   public void acceptMission(Mission mission) {
        if (!activeMissions.contains(mission)) {
            activeMissions.add(mission);
            System.out.println("Mission accepted: " + mission.getTitle());
        }
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
     * Moves the player down by increasing the y-coordinate.
     */
    public void moveDown() {
        if (this.positionY > 35) {
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
        if (this.positionX > 35) {
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
   
    /**
     * Sets the IP address of the player.
     * 
     * @param ip The IP address to set.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    /**
     * Sets the name of the player.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the x-coordinate of the player's position.
     * 
     * @param positionX The x-coordinate to set.
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Sets the y-coordinate of the player's position.
     * 
     * @param positionY The y-coordinate to set.
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}