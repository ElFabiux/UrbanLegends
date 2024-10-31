package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import game.Game;
import game.Player;
import playableCharacters.Character;
import playableCharacters.Hunter;
import playableCharacters.Researcher;
import playableCharacters.Witch;

/**
 * The Server class initializes the game server, allowing clients (players) to
 * connect, create their character, and join the game.
 *
 * The server listens for incoming player connections on a specified port
 * (8000). For each new connection:
 * <ul>
 * <li>It reads the player's name from the input stream.</li>
 * <li>Initializes a Player instance with the character and adds it to the
 * game.</li>
 * <li>Creates a new Flow thread to handle the player's actions.</li>
 * </ul>
 *
 * The server runs indefinitely, accepting new connections and processing
 * actions for each player.
 *
 * @author Jorge Rojas
 * @author Ismael Marchena
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class Server {

    private static final int PORT = 8000;

    /**
     * Returns the instance of the Game class.
     *
     * @return the singleton instance of Game.
     */
    public static Game getGameInstance() {
        return Game.getInstance();
    }

    /**
     * Main method to start the game server.
     * <p>
     * This method creates a ServerSocket to listen for player connections on
     * port 8000. For each connection, it processes player data, initializes
     * their character, and starts a new Flow thread for handling player
     * actions.
     * </p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server has started... waiting for players.");
            Game.getInstance().spawnNpcsWithMissions(10);
            int npcs = Game.getInstance().getNpcsList().size();
            System.out.println("cantidad npcs" + npcs);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                DataInputStream input = new DataInputStream(
                        new BufferedInputStream(clientSocket.
                                getInputStream()));

                String response = input.readUTF();
                String[] data = response.split(",");
                String playerName = data[0];
                String characterName = data[1];
                System.out.println("Connection accepted from: " + playerName);

                Character character = setPlayerCharacter(characterName);

                Player player = new Player(playerName,
                        clientSocket.getInetAddress().getHostAddress(),
                        0, 0, character);

                Game.getInstance().addPlayer(player, 4, 4);

                Flow flow = new Flow(clientSocket, player,
                        Game.getInstance());
                flow.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    /**
     * Sets the player character based on the provided character name. his
     * method creates and returns a new character instance based on the first
     * letter of the provided character name. The supported character types are:
     * - Witch: If the character name starts with 'W'. - Hunter: If the
     * character name starts with 'H'. - Researcher: If the character name
     * starts with 'R'.
     *
     * @param characterName characterName the name of the character to be
     * created.
     * @returna Character object representing the player's selected character.
     * @throws AssertionError if the character name does not correspond to a
     * valid character type.
     */
    public static Character setPlayerCharacter(String characterName) throws AssertionError {
        Character character;
        switch (characterName.charAt(0)) {
            case 'W':
                character = new Witch(characterName, 100,
                        100, 0);
                break;
            case 'H':
                character = new Hunter(characterName, 100,
                        100, 0);
                break;
            case 'R':
                character = new Researcher(characterName, 100,
                        100, 0);
                break;
            default:
                throw new AssertionError();
        }
        return character;
    }
}
