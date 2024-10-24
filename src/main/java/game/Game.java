package game;

import controllers.MapController;
import java.util.ArrayList;
import java.util.Vector;
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
        }
        System.out.println("players: " + this.players.toString());
    }

    // Validar si una posición es válida en el mapa
    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT);
    }

    public void updateMap(Player player, int oldX, int oldY) {
        this.map = extractSubmatrix(GameMap.getMap(), player.getPositionX(), player.getPositionY(), 10);
        System.out.println("x: "+player.getPosition());
//        for (Player player1 : players) {
//            String[][] miniMap = getMiniMap(player1.getPositionX(), player1.getPositionY());
//              hacer una petición tipo el inputUTF("render")
//            MapController.requestRender(miniMap);
//        }
    }

    // Método para obtener un mapa pequeño basado en la posición del jugador
    private String[][] getMiniMap(int playerX, int playerY) {
        String[][] miniMap = new String[MAP_HEIGHT][MAP_WIDTH];
        // Llenar el mini-mapa recursivamente
        fillMiniMap(playerX, playerY, miniMap, 0, 0);

        return miniMap;
    }

     public static String[][] extractSubmatrix(String[][] originalMatrix, int targetRow, int targetCol, int size) {
        int rows = originalMatrix.length;
        int cols = originalMatrix[0].length;

        int startRow = Math.max(targetRow - size / 2, 0);
        int startCol = Math.max(targetCol - size / 2, 0);
        int endRow = Math.min(startRow + size, rows);
        int endCol = Math.min(startCol + size, cols);
        
         System.out.println("rows: "+rows);
         System.out.println("cols: "+cols);
         System.out.println("startRow: "+startRow);
         System.out.println("startCol: "+startCol);
         System.out.println("endRow: "+endRow);
         System.out.println("endCol: "+endCol);

        String[][] submatrix = new String[endRow - startRow][endCol - startCol];

        fillSubmatrix(originalMatrix, submatrix, startRow, startCol, 0, 0, 
                endRow - startRow, endCol - startCol, startRow, startCol);
        return submatrix;
    }
     //agregar validadción para cuando cambie de row o col se reinicie los de la matriz original tambien
    private static void fillSubmatrix(String[][] original, String[][] submatrix,
            int origRow, int origCol, int subRow, int subCol, int numRows,
            int numCols, int auxOrigRow, int auxOrigCol) {
        System.out.println("------------------------------------");
        System.out.println("oR: "+origRow);
        System.out.println("oC: "+origCol);
        System.out.println("sR: "+subRow);
        System.out.println("sc: "+subCol);
        System.out.println("nR: "+numRows);
        System.out.println("nC: "+numCols);
        if (subRow >= numRows) {
            return;
        }

        if (subCol >= numCols) {
            fillSubmatrix(original, submatrix, origRow + 1, auxOrigCol, 
                    subRow + 1, 0, numRows, numCols, auxOrigRow, auxOrigCol);
            return;
        }

        submatrix[subRow][subCol] = original[origRow][origCol];
        fillSubmatrix(original, submatrix, origRow, origCol + 1, subRow, 
                subCol + 1, numRows, numCols, auxOrigRow, auxOrigCol);
    }
    
    //ver porqué no saca más partes del mapa grande
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
        System.out.println("player: " + name);
        System.out.println("players: " + Server.getGameInstance().players);
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

        if (row == MAP_HEIGHT) {
            return sb.toString();
        }

        if (col == MAP_WIDTH) {
            sb.append('\n');
            return printMapHelper(row + 1, 0, sb);
        }

        sb.append(map[row][col]).append(' ');

        return printMapHelper(row, col + 1, sb);
    }

    //FABIUX
}
