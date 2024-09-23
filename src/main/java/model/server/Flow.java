/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.server;

import model.Character.Character;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import model.game.Game;

/**
 *
 * @author joxan
 */
public class Flow extends Thread {
    private Socket socket;
    private Character character;
    private Game game;
    private DataInputStream input;
    private DataOutputStream output;

    public Flow(Socket socket, Character character, Game game) {
        this.socket = socket;
        this.character = character;
        this.game = game;
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
   
                String action = input.readUTF();
                if (action.equals("move")) {
                    
                    game.modifyCharacterStats(character.getName(), 
                            -10, 0, 0); 
                }
         
                output.writeUTF("Current energy: " + character.getEnergy());
            }
        } catch (IOException ioe) {
            System.out.println("Error in the flow: " + ioe);
        }
    }
}

