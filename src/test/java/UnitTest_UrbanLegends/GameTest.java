package UnitTest_UrbanLegends;

import game.Game;
import game.Npc;
import game.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import playableCharacters.*;
import playableCharacters.Character;

/**
 * Unit tests for the Game class.
 */
public class GameTest {

    private Game game;
    Character witch = new Witch();

    @BeforeEach
    public void setUp() {
        game = Game.getInstance();
    }

    /**
     * Test of getInstance method, of class Game.
     */
    @Test
    public void testGetInstance() {
        assertNotNull(game, "Game instance should not be null");
        if (game != null) {

            System.out.println("There is an instance game ");
        }
    }

    /**
     * Test of addPlayer method, of class Game.
     */
    @Test
    public void testAddPlayer() {

        Player player = new Player("TestPlayer", "127.0.0.1", 0,
                0, witch.createCharacter());

        int initialPlayerCount = game.getPlayers().size();
        game.addPlayer(player, 5, 5);

        assertEquals(initialPlayerCount + 1, game.getPlayers().size(),
                "The number of players should increase by one.");
        assertEquals(5, player.getPositionX(),
                "Player's X position should be 5.");
        assertEquals(5, player.getPositionY(),
                "Player's Y position should be 5.");
    }

    /**
     * Test of isValidPosition method, of class Game.
     */
    @Test
    public void testIsValidPosition() {
        assertTrue(game.isValidPosition(5, 5),
                "Position (5,5) should be valid.");
        assertFalse(game.isValidPosition(-1, 5),
                "Negative X position should be invalid.");
        assertFalse(game.isValidPosition(5, -1),
                "Negative Y position should be invalid.");
        assertFalse(game.isValidPosition(36, 5),
                "Out of bounds X position should be invalid.");
        assertFalse(game.isValidPosition(5, 36),
                "Out of bounds Y position should be invalid.");
    }

    /**
     * Test of spawnNpc method, of class Game.
     */
    @Test
    public void testSpawnNpcsWithMissions() {
        int npcCount = 5;
        game.spawnNpcsWithMissions(npcCount);

        List<Npc> npcs = game.getNpcsList();

        assertEquals(npcCount, game.getNpcsList().size(),
                "The number of NPCs should match npcCount.");

        for (int i = 0; i < npcs.size(); i++) {
            Npc npc = npcs.get(i);

            assertTrue(game.isValidPosition(npc.getPositionX(),
                    npc.getPositionY()),
                    "NPC position should be within map bounds.");

            assertNotNull(npc.getMission(),
                    "Each NPC should have a mission assigned.");

        }

    }
}
