package game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Mission in the game with an ID, title, description, and reward.
 * Provides functionality to load missions from a file recursively.
 */
public class Mission {

    // Primitive variables in alphabetical order
    private boolean completed;
    private int id;

    // Other variables in alphabetical order
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

    // Primitive variable methods in alphabetical order
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /*
     * Marks the mission as completed.
     */
    public void markAsCompleted() {
        this.completed = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    @Override
    public String toString() {
        return "Mission: " + title + "\nDescription: " + description
                + "\nReward: " + reward;
    }

    
}
