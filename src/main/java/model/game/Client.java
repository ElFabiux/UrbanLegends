/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.game;

/**
 *
 * @author joxan
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;

import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "localhost"; // Dirección del servidor (localhost para pruebas locales)
        int serverPort = 8000;              // Puerto del servidor

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to the server.");

            // Streams para la comunicación
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            // Scanner para entrada de datos por el usuario
            Scanner scanner = new Scanner(System.in);

            // Enviar el nombre del jugador al servidor
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            output.writeUTF(playerName); // Enviar el nombre al servidor

            
            Thread listenThread = new Thread(() -> {
                try {
                    while (true) {
                        String serverMessage = input.readUTF();
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            listenThread.start();

           
            while (true) {
                System.out.print("Enter a message to send to the server (or 'exit' to quit): ");
                String message = scanner.nextLine();
                
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

             
                output.writeUTF(message);
            }

            // Cerrar la conexión y recursos
            socket.close();
            scanner.close();
            System.out.println("Client disconnected.");

        } catch (IOException e) {
            System.out.println("Unable to connect to server: " + e.getMessage());
        }
    }
    
    
}

