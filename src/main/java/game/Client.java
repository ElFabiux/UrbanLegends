/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author joxan
 */
public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Conectar al servidor (en este caso, localhost en el puerto 8000)
            socket = new Socket("localhost", 8000);
            System.out.println("Connected to the server.");

            // Crear flujos para enviar y recibir datos
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            // Enviar el nombre del jugador al servidor
            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();
            output.writeUTF(playerName);  // Enviar el nombre al servidor

            // Ciclo para enviar comandos al servidor
            String command;
            while (true) {
                System.out.print("Enter command (move [up/down/left/right] or attack): ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the game...");
                    break;  // Salir del ciclo si el jugador escribe "exit"
                }

                // Enviar comando al servidor
                output.writeUTF(command);

                // Leer la respuesta del servidor
                String response = input.readUTF();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            try {
                if (input != null) input.close();
                if (output != null) output.close();
                if (socket != null) socket.close();
                System.out.println("Connection closed.");
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
