package server;

import game.Game;
import game.Player;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Flow extends Thread {

    private Socket socket;
    private Player player;
    private Game game;
    private DataInputStream input;
    private DataOutputStream output;
    private Interpreter interpreter;

    public Flow(Socket socket, Player player, Game game) {
        this.socket = socket;
        this.player = player;
        this.game = game;
        this.interpreter = new Interpreter(game);
        try {
            input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                
                String received = input.readUTF();

                String[] parts = received.split(" ");

                String command = parts[0];  
                String direction = parts.length > 1 ? parts[1] : ""; 

             
                String response = interpreter.interpret(command, direction, 
                        player);

              
                game.updateMap(player);

                
                output.writeUTF(response);
                output.writeUTF("Map:\n" + game.printMap());
                System.out.println(game.printMap());
                output.flush();
            }
        } catch (IOException ioe) {
            System.out.println("Error in the flow: " + ioe);
        } finally {
            
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
}
