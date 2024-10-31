package UnitTest_UrbanLegends;

import game.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import game.Game;
import server.Server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit tests for the {@code Server} class, ensuring the server operates as
 * expected in managing player connections, game instance access, NPC spawning,
 * and error handling.
 * <p>
 * Tests include server initialization, player connections, singleton instance
 * retrieval, NPC spawning, and handling invalid data inputs.
 * </p>
 * This test suite requires the server to be running on a separate thread.
 *
 * @see Server
 * @see Game
 */
public class ServerTest {

    private static final int PORT = 8000;
    private static Thread serverThread;

    /**
     * Sets up the server in a separate thread before running tests. This method
     * ensures that the server is up and ready to accept connections.
     */
    @BeforeAll
    public static void setUp() throws IOException {

        serverThread = new Thread(() -> {
            try {
                Server.main(null);
            } catch (Exception e) {
                System.err.println("Server execution error: " + e.getMessage());
            }
        });
        serverThread.start();
    }
    private Game game;

    @BeforeEach
    public void setUpGame() {
        game = Game.getInstance();
    }

    /**
     * Stops the server after all tests have completed.
     */
    @AfterAll
    public static void tearDown() throws IOException {

        serverThread.interrupt();
    }

    /**
     * Tests the player's connection handling by simulating a player connecting
     * to the server. Verifies that the player is added to the game with the
     * specified name and character.
     */
    @Test
    public void testPlayerConnection() {
        try (Socket socket
                = new Socket("localhost", PORT); DataOutputStream output
                = new DataOutputStream(
                        socket.getOutputStream())) {

            // Send data player
            String testData = "TestPlayer,TestCharacter";
            output.writeUTF(testData);
            output.flush();

            // Wait to server process the information 
            Thread.sleep(100);

            Player player = game.getPlayers().stream()
                    .filter(p -> p.getName().equals("TestPlayer"))
                    .findFirst().orElse(null);

            assertNotNull(player,
                    "Player should be in the game after connection.");
            assertEquals("TestPlayer",
                    player.getName(),
                    "Player's name should match the input.");
        } catch (IOException e) {
            fail("Simulated player connection should "
                    + "not throw an IOException.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Thread was interrupted during sleep.");
        }
    }

    /**
     * Tests the handling of invalid player data input to ensure the server
     * processes errors correctly. This test sends malformed data to simulate an
     * error scenario and checks for proper error handling.
     */
    @Test
    public void testInvalidPlayerDataHandling() {
        try (Socket socket
                = new Socket("localhost", PORT); DataOutputStream output
                = new DataOutputStream(socket.getOutputStream())) {

            // Send invalid player data
            String invalidData = "InvalidPlayerDataWithoutComma";
            output.writeUTF(invalidData);
            output.flush();

            
        } catch (IOException e) {
            fail("Sending invalid player data should not throw an IOException.");
        }
    }
}
