 
package server;

import game.Game;
import game.Player;
import playableCharacters.Witch;
import playableCharacters.Character;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The {@code Server} class initializes the game server, allowing clients
 * (players) to connect, create their character, and join the game.
 *
 * The server listens for incoming player connections on a specified port
 * (8000). For each new connection:
 * <ul>
 * <li>It reads the player's name from the input stream.</li>
 * <li>Initializes a {@code Player} instance with the character and adds it to
 * the game.</li>
 * <li>Creates a new {@code Flow} thread to handle the player's actions.</li>
 * </ul>
 *
 * The server runs indefinitely, accepting new connections and processing
 * actions for each player.
 *
 * @author joxan
 */
public class Server {

    /**
     * Main method to start the game server. Listens on port 8000 for incoming
     * player connections. For each connected player, a new player added to the
     * game, and a {@code Flow} thread is started to manage player actions.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server has started... waiting for players.");

            Game game = Game.getInstance();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                DataInputStream input = new DataInputStream(
                        new BufferedInputStream(clientSocket.
                                getInputStream()));

                String playerName = input.readUTF();
                System.out.println("Connection accepted from: " + playerName);

                Character character = new Witch(playerName, 100,
                        100, 0);
                Player player = new Player(playerName,
                        clientSocket.getInetAddress().getHostAddress(),
                        0,1, character);

             
                game.addPlayer(player, 1, 0);

                Flow flow = new Flow(clientSocket, player, game);
                flow.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
