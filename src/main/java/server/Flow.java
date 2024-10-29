package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import game.Game;
import game.Player;

/**
 * The {@code Flow} class handles the interaction between a single player and
 * the server. It reads commands from the player, interprets them, and updates
 * the game state accordingly. The {@code Flow} class operates as a thread to
 * manage communication independently for each player.
 *
 * It utilizes an {@code Interpreter} instance to process commands, execute
 * actions, and communicate with the game to update the player's position and
 * actions on the map.
 *
 * @author joxan
 */
public class Flow extends Thread {

    private Game game;
    private DataInputStream input;
    private DataOutputStream output;
    private Player player;
    private Socket socket;

    private Interpreter interpreter;

    /**
     * Constructs a {@code Flow} instance for a player, initializing the
     * necessary input and output streams, and an {@code Interpreter} to process
     * player commands.
     *
     * @param socket the socket for communicating with the player
     * @param player the player instance interacting with the server
     * @param game the game instance to update the game state based on player
     * actions
     */
    public Flow(Socket socket, Player player, Game game) {
        this.socket = socket;
        this.player = player;
        this.game = game;
        this.interpreter = new Interpreter(game);
        try {
            input = new DataInputStream(new BufferedInputStream(
                    socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe);
        }
    }

    /**
     * Starts the flow thread for this player, continuously listening for player
     * commands. Commands are interpreted and executed, and the game map is
     * updated to reflect changes in the player's position.
     */
    @Override
    public void run() {
        try {
            while (true) {

                String received = input.readUTF();

                String[] parts = received.split(" ");

                String command = parts[0];
                String direction = parts.length > 1 ? parts[1] : "";

                int oldRow = player.getPositionY();
                int oldCol = player.getPositionX();
                
                String response = interpreter.interpret(command, direction,
                        player);
                
                
                game.updateMap(player, oldRow, oldCol);
                if (command.equals("get")) {
                    output.writeUTF(response);
                } else {
                    output.writeUTF(response);
                    output.writeUTF("Map:\n" + game.printMap());
                    System.out.println(game.printMap());
                }

                output.flush();
            }
        } catch (IOException ioe) {
            System.out.println("Error in the flow: " + ioe);
        } finally {
            closeResources();
        }
    }

    /**
     * Closes the input, output streams, and socket resources to prevent
     * resource leaks.
     */
    private void closeResources() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e);
        }
    }
}
