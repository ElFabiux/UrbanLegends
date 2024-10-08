package game;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int MAP_WIDTH = 10;
    private static final int MAP_HEIGHT = 10;
    private char[][] map = new char[MAP_HEIGHT][MAP_WIDTH];  // Matriz local del cliente
    private DataOutputStream output;
    private DataInputStream input;
    private Socket socket;

    public Client() {
        // Inicializar el mapa local del cliente
        initializeMap();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer("localhost", 8000);  // Conectar al servidor en localhost, puerto 8000
        client.listenForCommands();  // Escuchar comandos de flechas
    }

    // Método para conectar al servidor
    private void connectToServer(String host, int port) {
        try {
            socket = new Socket(host, port);
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println("Connected to server.");

            // Enviar el nombre del jugador al servidor
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();
            output.writeUTF(playerName);
            output.flush();

        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }

    // Método para escuchar comandos y enviar movimientos al servidor
    private void listenForCommands() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("Use arrow keys to move (up, down, left, right): ");
                String command = scanner.nextLine();
                sendMoveCommand(command);
                String response = input.readUTF();  // Recibir respuesta del servidor
                System.out.println(response);
                String mapState = input.readUTF();  // Estado actualizado del mapa
                System.out.println(mapState);
                updateLocalMap(mapState);  // Actualizar el mapa local
            }
        } catch (IOException e) {
            System.out.println("Connection closed.");
        } finally {
            // Cerrar los recursos
            try {
                if (input != null) input.close();
                if (output != null) output.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Error closing client resources: " + e.getMessage());
            }
        }
    }

    // Enviar el comando de movimiento al servidor
    private void sendMoveCommand(String command) throws IOException {
        switch (command) {
            case "up":
                output.writeUTF("move up");
                break;
            case "down":
                output.writeUTF("move down");
                break;
            case "left":
                output.writeUTF("move left");
                break;
            case "right":
                output.writeUTF("move right");
                break;
            default:
                System.out.println("Invalid command. Use 'up', 'down', 'left', 'right'.");
        }
        output.flush();
    }

    // Inicializar el mapa local del cliente
    private void initializeMap() {
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i][j] = '.';
            }
        }
    }

    // Actualizar el mapa local del cliente
    private void updateLocalMap(String mapState) {
        System.out.println("Map state received: \n" + mapState);

        // Ignorar la primera línea que contiene el encabezado "Map:"
        String[] lines = mapState.split("\n");
        if (lines.length < MAP_HEIGHT + 1) {  // Asegúrate de que haya suficientes filas, incluido el encabezado
            System.out.println("Error: El mapa no tiene la altura esperada.");
            return;
        }

        // Procesar solo las líneas que contienen el mapa (omitir la primera línea)
        for (int i = 1; i <= MAP_HEIGHT; i++) {
            String[] columns = lines[i].split(" ");
            if (columns.length != MAP_WIDTH) {
                System.out.println("Error: La fila " + (i - 1) + " no tiene la longitud esperada.");
                return;
            }
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i - 1][j] = columns[j].charAt(0);  // Actualiza el mapa local
            }
        }

        printLocalMap();  // Imprimir el mapa actualizado
    }

    // Mostrar el mapa local del cliente
    private void printLocalMap() {
        System.out.println("Local Map:");
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
