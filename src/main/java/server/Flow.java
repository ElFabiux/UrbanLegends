package server;

import game.Game;
import characters.Character;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Flow extends Thread {
    private Socket socket;
    private Character character;
    private Game game;
    private Interpreter interpreter;
    private DataInputStream input;
    private DataOutputStream output;

    public Flow(Socket socket, Character character, Game game) {
        this.socket = socket;
        this.character = character;
        this.game = game;
        this.interpreter = new Interpreter(game);
        try {
            input = new DataInputStream(socket.getInputStream());
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
                String secondParameter = parts.length > 1 ? parts[1] : ""; 

                
                String response = interpreter.interpret(command, 
                        secondParameter, character);

                
                output.writeUTF(response);
            }
        } catch (IOException ioe) {
            System.out.println("Error in the flow: " + ioe);
        }
    }
}
