
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
import java.util.Random;

/**
 *
 * @author joxan
 */
public class Server {
    
    public static Game getGameInstance(){
        return Game.getInstance();
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Random random = new Random();
        int MAX = 9;
        int MIN = 0;
        try {
            serverSocket = new ServerSocket(8000); 
            System.out.println("Server has started... waiting for players.");


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
                        0,0, character);
                
                int x = random.nextInt((MAX - MIN) + 1) + MIN;
                int y = random.nextInt((MAX - MIN) + 1) + MIN;
                Game.getInstance().addPlayer(player, x, y);

                Flow flow = new Flow(clientSocket, player, Game.getInstance());
                flow.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
