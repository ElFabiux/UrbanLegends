/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.server;

import model.Character.Witch;
import model.Character.Character;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import model.game.Game;
import model.game.Player;
import model.game.Player;

/**
 *
 * @author joxan
 */
public class Server extends Thread {

    public static Vector<Player> players = new Vector<>(3);

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server has started... waiting for players.");

            // Use the Singleton instance of Game
            Game game = Game.getInstance();

        } catch (IOException ioe) {
            System.out.println("Connection rejected: " + ioe);
            System.exit(1);
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                DataInputStream input = new DataInputStream(
                        new BufferedInputStream(clientSocket.getInputStream()));
                String name = input.readUTF();
                System.out.println("Connection accepted from: " + name);

                // Create the character with the received name
                Character character = new Witch(100, 0,
                        0);

                Player player = new Player("Jorge", 0, 0,
                        "192.168.0.1", character);
                players.add(player);

                // Get the singleton instance of Game
                Game game = Game.getInstance();

                // Create and start a new Flow thread
                Flow flow = new Flow(clientSocket, character, game);
                flow.start();

            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            }
        }
    }
}
