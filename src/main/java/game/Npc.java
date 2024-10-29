package game;

/**
 * Represents an NPC (Non-Player Character) in the game with an assigned
 * mission. NPCs assign missions to players who interact with them.
 *
 * @author joxan
 */
public class Npc {

    
    private int positionX;
    private int positionY;

  
    private Mission mission;
    private String name;

    /**
     * Constructs an Npc with the specified name, position, and mission.
     *
     * @param name The name of the NPC.
     * @param positionX The x-coordinate of the NPC's position on the map.
     * @param positionY The y-coordinate of the NPC's position on the map.
     * @param mission The mission assigned to this NPC.
     */
    public Npc(String name, int positionX, int positionY, Mission mission) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.mission = mission;
    }

    /**
     * Returns the mission assigned to the NPC, which the NPC can offer to the
     * player.
     *
     * @return The mission assigned to this NPC.
     */
    public Mission assignMission() {
        return mission;
    }

    /**
     * Gets the name of the NPC.
     *
     * @return The name of the NPC.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the x-coordinate of the NPC's position on the map.
     *
     * @return The x-coordinate of the NPC.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Gets the y-coordinate of the NPC's position on the map.
     *
     * @return The y-coordinate of the NPC.
     */
    public int getPositionY() {
        return positionY;
    }

    @Override
    public String toString() {
        return "Npc{" + "positionX=" + positionX + ", positionY=" + positionY 
                + ", mission=" + mission.toString() + ", name=" + name + '}';
    }
    
    

      
}
