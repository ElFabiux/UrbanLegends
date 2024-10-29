package game;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static final int MAP_HEIGHT = 10;
    private static final int MAP_WIDTH = 10;
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;
    private static String character;
    private String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];
    private static String playerName;

    /**
     * The constructor that initialize the client map
     */
    public Client() {
        initializeMap();
    }

    /**
     * Close the resources that the client have been used
     */
    public void closeResources() {
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

    /**
     * Method to connect to the server
     *
     * @param host the ip of the server
     * @param port the port of the server
     */
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

    /**
     * Fill the map columns
     *
     * @param currentLine current line
     * @param columns the array of columns
     * @param currentColumn current column
     */
    private void fillMapColumns(int currentLine, String[] columns, int currentColumn) {
        if (currentColumn >= MAP_WIDTH) {
            return;
        }

        map[currentLine - 1][currentColumn] = String.valueOf(columns[currentColumn].charAt(0));

        fillMapColumns(currentLine, columns, currentColumn + 1);
    }

    /**
     * Get the client map
     *
     * @return the client map
     */
    public String[][] getMap() {
        return this.map;
    }

    /**
     * Ask to the server for the position of the player
     *
     * @param command "position"
     * @return
     */
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

    /**
     * Initialize the map of the client
     */
    private void initializeMap() {
        initializeMapRecursive(0, 0);
    }

    /**
     * Initialize the map
     *
     * @param row current row
     * @param col current col
     */
    private void initializeMapRecursive(int row, int col) {
        if (row >= MAP_HEIGHT) {
            return;
        }
        if (col >= MAP_WIDTH) {
            initializeMapRecursive(row + 1, 0);
            return;
        }

        map[row][col] = ".";
        initializeMapRecursive(row, col + 1);
    }

    /**
     * The principal comunication to the server for the move
     *
     * @param command "up", "down", "left", "right"
     * @return a new map with the new information
     */
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

    /**
     * Conect to the server
     *
     * @param args [0] player name [1] character
     * @return a new client
     */
    public static Client main(String[] args) {
        Client client = new Client();
        Client.playerName = args[0];
        Client.character = args[1];
        client.connectToServer("localhost", 8000);
        return client;
    }

    /**
     * Print the columns of the map
     *
     * @param currentRow current row
     * @param currentColumn current column
     */
    private void printColumns(int currentRow, int currentColumn) {
        if (currentColumn >= MAP_WIDTH) {
            System.out.println();
            return;
        }
        System.out.print(map[currentRow][currentColumn] + " ");
        printColumns(currentRow, currentColumn + 1);
    }

    /**
     * Print the map
     */
    private void printLocalMap() {
        System.out.println("Local Map:");
        printRows(0);
    }

    /**
     * Print the rows of the map
     *
     * @param currentRow current row
     */
    private void printRows(int currentRow) {
        if (currentRow >= MAP_HEIGHT) {
            return;
        }
        printColumns(currentRow, 0);
        printRows(currentRow + 1);
    }

    /**
     * Process the map lines
     *
     * @param lines the array of lines
     * @param currentLine current line
     */
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

    /**
     * Send the move command to the server
     *
     * @param command "up", "down", "left", "right", "position"
     * @throws IOException
     */
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

    /**
     * Update the local map
     *
     * @param mapState state of the updated map
     */
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

}
