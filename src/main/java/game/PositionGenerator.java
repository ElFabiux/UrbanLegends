/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.Random;

/**
 * Generates random positions within a predefined map boundary.
 * This class provides methods to obtain random x and y coordinates 
 * for placing game elements on a grid or map.
 * 
 * @author joxan
 * @author melani
 * @author fabian
 * @author jorge
 * @author ismael
 */
public class PositionGenerator {

    private static final int MAP_BOUNDARY = 10;
    private static final Random random = new Random();

    /**
     * Generates a random x-coordinate within the map boundaries (0 to 10).
     * 
     * @return a random x-coordinate within 0 and MAP_BOUNDARY - 1
     */
    public static int getRandomPositionX() {
        return random.nextInt(MAP_BOUNDARY);
    }

    /**
     * Generates a random y-coordinate within the map boundaries (0 to 10).
     * 
     * @return a random y-coordinate within 0 and MAP_BOUNDARY - 1
     */
    public static int getRandomPositionY() {
        return random.nextInt(MAP_BOUNDARY);
    }
}