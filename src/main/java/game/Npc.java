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

    /**
     * Determines if the specified position is within a distance of one unit
     * from the NPC's current position.
     *
     * @param x The x-coordinate of the position to check.
     * @param y The y-coordinate of the position to check.
     * @return true if the specified position is near the NPC; false otherwise.
     */
    public boolean isNear(int x, int y) {
        return Math.abs(this.positionX - x) <= 1 && Math.abs(this.positionY - y)
                <= 1;
    }
}
