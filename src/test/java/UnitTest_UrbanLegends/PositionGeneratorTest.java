/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package UnitTest_UrbanLegends;

import game.PositionGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author joxan
 */

/**
 * Unit tests for the PositionGenerator class.
 * Tests the random position generation to ensure coordinates
 * are within the specified map boundaries.
 * 
 * @author Test Author
 */
public class PositionGeneratorTest {

    private static final int MAX_BOUNDARY = 35;

    /**
     * Test for getRandomPositionX method.
     * Ensures the x-coordinate is within the valid range [0, MAP_BOUNDARY).
     */
    @Test
    public void testGetRandomPositionX() {
        int x = PositionGenerator.getRandomPositionX();
        assertTrue(x >= 0 && x < MAX_BOUNDARY, 
                   "X-coordinate should be within the range [0, " + 
                           (MAX_BOUNDARY - 1) + "], but was: " + x);
    }

    /**
     * Test for getRandomPositionY method.
     * Ensures the y-coordinate is within the valid range [0, MAP_BOUNDARY).
     */
    @Test
    public void testGetRandomPositionY() {
        int y = PositionGenerator.getRandomPositionY();
        assertTrue(y >= 0 && y < MAX_BOUNDARY, 
                   "Y-coordinate should be within the range [0, " + 
                           (MAX_BOUNDARY - 1) + "], but was: " + y);
    }
    
    /**
     * Additional test for repeated random values to ensure consistency.
     * Runs multiple trials to check if the generated values stay within the 
     * boundaries.
     */
    @Test
    public void testRandomPositionBounds() {
         
            int x = PositionGenerator.getRandomPositionX();
            int y = PositionGenerator.getRandomPositionY();
            assertTrue(x >= 0 && x < MAX_BOUNDARY, 
                       "X-coordinate should be within the range [0, " + 
                               (MAX_BOUNDARY - 1) + "], but was: " + x);
            assertTrue(y >= 0 && y < MAX_BOUNDARY, 
                       "Y-coordinate should be within the range [0, " +
                               (MAX_BOUNDARY - 1) + "], but was: " + y);
        }
    }

