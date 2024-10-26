package game;

import java.util.ArrayList;

import server.Server;

public class Game {

    private static Game instance;

    private ArrayList<Player> players = new ArrayList<>();
    private final int MAP_WIDTH = 10;
    private final int MAP_HEIGHT = 10;
    private String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];
    private Game() {
        initializeMap();
    }

    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Inicializar el mapa
    private void initializeMap() {
        this.map = GameMap.getMap();
    }

    public void addPlayer(Player player, int x, int y) {
        System.out.println("instance: " + Game.instance);
        if (this.players.size() < 3) {
            this.players.add(player);
            player.setPositionX(x);
            player.setPositionY(y);
            GameMap.getMap()[y][x] = player.getCharacter().getName().substring(0, 1);
        }
        System.out.println("players: " + this.players.toString());
    }
    
    

  
    // Validar si una posición es válida en el mapa
    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT);
    }

    public void updateMap(Player player) {
        this.map = extractSubmatrix(GameMap.getMap(), player.getPositionX(), player.getPositionY(), 10);
    }
    
    private void createStringForPlayerPositions(ArrayList<Player> players, Player head, String positions){
        positions = positions + ";" + head.getPosition();
        players.remove(head);
        if(players.isEmpty()) return;
        head = players.get(0);
        createStringForPlayerPositions(players, head, positions);
    }
    
    public String getPlayersPosition(){
        String positions="";
        ArrayList<Player> playersCopy =(ArrayList<Player>) Game.instance.players.clone();
        createStringForPlayerPositions(playersCopy, playersCopy.get(0), positions);
        return positions;
    }

    // Método para obtener un mapa pequeño basado en la posición del jugador
    private String[][] getMiniMap(int playerX, int playerY) {
        String[][] miniMap = new String[MAP_HEIGHT][MAP_WIDTH];
        fillMiniMap(playerX, playerY, miniMap, 0, 0);

        return miniMap;
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static String[][] extractSubmatrix(String[][] originalMatrix, int targetRow, int targetCol, int size) {
        int rows = originalMatrix.length;
        int cols = originalMatrix[0].length;

        int startRow = Math.max(targetRow - size / 2, 0);
        int startCol = Math.max(targetCol - size / 2, 0);
        int endRow = Math.min(startRow + size, rows);
        int endCol = Math.min(startCol + size, cols);
        String[][] submatrix = new String[endRow - startRow][endCol - startCol];

        fillSubmatrix(originalMatrix, submatrix, startRow, startCol, 0, 0,
                endRow - startRow, endCol - startCol, startRow, startCol);
        return submatrix;
    }
    //agregar validadción para cuando cambie de row o col se reinicie los de la matriz original tambien

    private static void fillSubmatrix(String[][] original, String[][] submatrix,
            int origRow, int origCol, int subRow, int subCol, int numRows,
            int numCols, int auxOrigRow, int auxOrigCol) {

        if (subRow >= numRows) {
            return;
        }

        if (subCol >= numCols) {
            fillSubmatrix(original, submatrix, origRow + 1, auxOrigCol,
                    subRow + 1, 0, numRows, numCols, auxOrigRow, auxOrigCol);
            return;
        }
        //aqui se puede agregar el jugador: original[origRow][origCol]+ "/"+(CARACTER DEL JUGADOR)
        submatrix[subRow][subCol] = original[origRow][origCol];
        fillSubmatrix(original, submatrix, origRow, origCol + 1, subRow,
                subCol + 1, numRows, numCols, auxOrigRow, auxOrigCol);
    }

    private void fillMiniMap(int playerX, int playerY, String[][] miniMap, int row, int col) {
        if (row >= MAP_HEIGHT) {
            return;
        }
        if (col >= MAP_WIDTH) {
            fillMiniMap(playerX, playerY, miniMap, row + 1, 0);
            return;
        }

        int mapX = playerX - 5 + col;
        int mapY = playerY - 5 + row;

        if (mapX < 0) {
            mapX = 0;
        } else if (mapX > 35) {
            mapX = 35;
        }

        if (mapY < 0) {
            mapY = 0;
        } else if (mapY > 35) {
            mapY = 35;
        }

        miniMap[row][col] = GameMap.getMap()[mapY][mapX];
        fillMiniMap(playerX, playerY, miniMap, row, col + 1);
    }

    private String getPlayerByName(String name, ArrayList<Player> players) {
        if (players.isEmpty()) {
            return null;
        }
        Player head = players.get(0);

        if (head == null) {
            return null;
        }
        if (head.getName().equals(name)) {
            return head.getPosition();
        }
        ArrayList<Player> newPlayers = new ArrayList(Server.getGameInstance().players);
        newPlayers.remove(head);
        return getPlayerByName(name, newPlayers);
    }

    public String getPlayer(String name) {
        return getPlayerByName(name, Server.getGameInstance().players);
    }

    public String printMap() {
        return printMapHelper(0, 0, new StringBuilder());
    }

    private String printMapHelper(int row, int col, StringBuilder sb) {
        if (row >= map.length) {
            return sb.toString();
        }
        if (col >= map[0].length) {
            sb.append('\n');
            return printMapHelper(row + 1, 0, sb);
        }
        sb.append(map[row][col]).append(' ');
        return printMapHelper(row, col + 1, sb);
    }
}
