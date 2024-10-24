 
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
 *
 * @author joxan
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8000); 
            System.out.println("Server has started... waiting for players.");

            Game game = Game.getInstance();  

            while (true) {
                Socket clientSocket = serverSocket.accept();  
                DataInputStream input = new DataInputStream(
                        new BufferedInputStream(clientSocket.getInputStream()));

                
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
