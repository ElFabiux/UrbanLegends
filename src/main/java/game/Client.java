package game;

import controllers.MapController;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import server.Server;

public class Client {

    private static final int MAP_WIDTH = 10;
    private static final int MAP_HEIGHT = 10;
    private String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];  // Matriz local del cliente
    private DataOutputStream output;
    private DataInputStream input;
    private Socket socket;
    private static String playerName;
    private static String character;

    public Client() {
        // Inicializar el mapa local del cliente
        initializeMap();
    }

    public static Client main(String[] args) {
        Client client = new Client();
        Client.playerName = args[0];
        Client.character = args[1];
        client.connectToServer("localhost", 8000);
        return client;
    }

    // Método para conectar al servidor
    private void connectToServer(String host, int port) {
        try {
            socket = new Socket(host, port);
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println("Connected to server.");
            output.writeUTF(playerName + "," + character);
            output.flush();

        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }

    public String[][] listenForCommands(String command) {
        try {
            sendMoveCommand(command);
            String response = input.readUTF();
            System.out.println(response);
            String mapState = input.readUTF();
            System.out.println("client_58: " + mapState);
            updateLocalMap(mapState);

        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
        return this.map;
    }

    public String getPosition(String command) {
        String response = "";
        try {
            sendMoveCommand(command);
            response = input.readUTF();
            System.out.println("reponsssseeeeee: +" + response);
        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
        return response;
    }
    
    private void deletePlayer(){
        try {
            sendMoveCommand("delete");
            String response = input.readUTF();
            System.out.println(response);
            String mapState = input.readUTF();
            System.out.println("client_58: " + mapState);
            updateLocalMap(mapState);

        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }

    public void closeResources() {
        deletePlayer();
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
            System.out.println("Error closing client resources: " + e.getMessage());
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
            case "position":
                output.writeUTF("get " + playerName);
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
                map[i][j] = ".";
            }
        }
    }

    private void updateLocalMap(String mapState) {
        System.out.println("Map state received: \n" + mapState);

        String[] lines = mapState.split("\n");
        System.out.println("client_135 lines: " + lines.length);
        if (lines.length < MAP_HEIGHT + 1) {
            System.out.println("Error: El mapa no tiene la altura esperada.");
            return;
        }

        processMapLines(lines, 1);
    }

    private void processMapLines(String[] lines, int currentLine) {
        if (currentLine > MAP_HEIGHT) {
            return;
        }

        String[] columns = lines[currentLine].split(" ");
        if (columns.length != MAP_WIDTH) {
            System.out.println("Error: La fila " + (currentLine - 1) + " no tiene la longitud esperada.");
            return;
        }

        fillMapColumns(currentLine, columns, 0);
        processMapLines(lines, currentLine + 1);
    }

    private void fillMapColumns(int currentLine, String[] columns, int currentColumn) {
        if (currentColumn >= MAP_WIDTH) {
            return;
        }

        map[currentLine - 1][currentColumn] = String.valueOf(columns[currentColumn].charAt(0));

        fillMapColumns(currentLine, columns, currentColumn + 1);
    }

    private void printLocalMap() {
        System.out.println("Local Map:");
        printRows(0);
    }

    private void printRows(int currentRow) {
        if (currentRow >= MAP_HEIGHT) {
            return;
        }
        printColumns(currentRow, 0);
        printRows(currentRow + 1);
    }

    private void printColumns(int currentRow, int currentColumn) {
        if (currentColumn >= MAP_WIDTH) {
            System.out.println();
            return;
        }
        System.out.print(map[currentRow][currentColumn] + " ");
        printColumns(currentRow, currentColumn + 1);
    }

    public String[][] getMap() {
        return this.map;
    }
}
