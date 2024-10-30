package game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Mission in the game with an ID, title, description, and reward.
 * Provides functionality to load missions from a file recursively.
 *
 * @author jorge
 * @author joxan
 * @author melani
 * @author fabian
 * @author ismael
 */
public class Mission {

    private boolean completed;
    private int id;

    private String description;
    private String reward;
    private String title;

    /**
     * Constructs a Mission with the specified details.
     *
     * @param id Unique ID of the mission.
     * @param title Title of the mission.
     * @param description Description of the mission.
     * @param reward Reward for completing the mission.
     */
    public Mission(int id, String title, String description, String reward) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.reward = reward;
        this.completed = false;
    }

    /**
     * Checks if the mission is completed.
     *
     * @return true if the mission is completed, otherwise false.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Gets the unique ID of the mission.
     *
     * @return Mission ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Loads missions from the specified file path recursively.
     *
     * @return List of Mission objects.
     */
    public static List<Mission> loadMissions() {
        List<Mission> missions = new ArrayList<>();
        try (InputStream inputStream = Mission.class.getResourceAsStream(
                "/Missions.txt")) {
            if (inputStream == null) {
                System.out.println(
                        "Error: Missions.txt file not found in resources.");
                return missions;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream));
            readMissions(reader, missions, 1);
        } catch (Exception e) {
            System.out.println("Error loading missions: " + e.getMessage());
        }
        return missions;
    }

    /**
     * Gets the title of the mission.
     *
     * @return The mission's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description of the mission.
     *
     * @return The mission's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the reward for completing the mission.
     *
     * @return The mission's reward.
     */
    public String getReward() {
        return reward;
    }

    /**
     * Sets the unique ID for the mission.
     *
     * @param id Mission ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Marks the mission as completed.
     */
    public void markAsCompleted() {
        this.completed = true;
    }

    /**
     * Sets the mission description.
     *
     * @param description The mission's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the reward for the mission.
     *
     * @param reward The mission's reward.
     */
    public void setReward(String reward) {
        this.reward = reward;
    }

    /**
     * Sets the title of the mission.
     *
     * @param title The mission's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Reads each line from the BufferedReader and adds a mission to the list.
     *
     * @param reader BufferedReader to read mission lines.
     * @param missions List to store the missions.
     * @param idCounter Counter for assigning mission IDs.
     * @throws Exception If there is an error reading from the file.
     */
    private static void readMissions(BufferedReader reader, List<Mission> missions,
            int idCounter) throws Exception {
        String line = reader.readLine();
        if (line == null) {
            return;
        }

        String[] parts = line.split("\\|");
        if (parts.length == 4) {
            try {
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String description = parts[2].trim();
                String reward = parts[3].trim();

                missions.add(new Mission(id, title, description, reward));
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format in line: " + line);
            }
        } else {
            System.out.println("Invalid line format: " + line);
        }

        readMissions(reader, missions, idCounter + 1);
    }

    /**
     * Returns a string representation of the mission with title, description,
     * and reward.
     *
     * @return String representation of the mission.
     */
    @Override
    public String toString() {
        return "Mission: " + title + "\nDescription: " + description
                + "\nReward: " + reward;
    }
}
