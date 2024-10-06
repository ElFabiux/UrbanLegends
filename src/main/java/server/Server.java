/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import characters.Witch;
import characters.Character;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import game.Game;
import game.Player;

/**
 *
 * @author joxan
 */
public class Server extends Thread {

    public static Vector<Player> players = new Vector<>(3);

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Game game = Game.getInstance();
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server has started... waiting for players.");

        } catch (IOException ioe) {
            System.out.println("Connection rejected: " + ioe);
            System.exit(1);
        }

        while (true) {
            Socket clientSocket = null;
            try {

                clientSocket = serverSocket.accept();
                DataInputStream input = new DataInputStream(
                        new BufferedInputStream(
                                clientSocket.getInputStream()));

                String name = input.readUTF();
                System.out.println("Connection accepted from: " + name);

                Character character = new Witch(name, 100, 0,
                        0);

               
                Player player = new Player(name, clientSocket.getInetAddress()
                        .getHostAddress(), 0,0,character);
                
                
                
                players.add(player);  

           
                Flow flow = new Flow(clientSocket, player, game);
                flow.start();

            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            } finally {
                
                if (clientSocket != null && clientSocket.isClosed()) {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        System.out.println("Failed to close client socket: " + e);
                    }
                }
            }
        }
    }
}
