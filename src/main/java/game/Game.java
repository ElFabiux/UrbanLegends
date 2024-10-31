package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import server.Server;

/**
 * The class that manage the Game logic
 *
 * @author Ismael Marchena
 * @author Jorge Rojas
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class Game {

    private final int MAP_HEIGHT = 10;
    private final int MAP_WIDTH = 10;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Npc> npcs = new ArrayList<>();
    private static Game instance;
    private String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];
    private String[][] mapClone;

    /**
     * Contructor for game and initialize the map
     */
    private Game() {
        initializeMap();
    }

    /**
     * Get the map clone
     *
     * @return mapClone
     */
    public String[][] getMapClone() {
        return mapClone;
    }

    /**
     * Get the instance of the game
     *
     * @return an instance game
     */
    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Add a player to the array list of player
     *
     * @param player the player to be add
     * @param x the x position of the player
     * @param y the y position of the player
     */
    public void addPlayer(Player player, int x, int y) {
        if (this.players.size() < 3) {
            this.players.add(player);
            player.setPositionX(x);
            player.setPositionY(y);
        }
    }

    /**
     * Spawn npcs with missions.
     *
     * @param npcCount how many npcs will be spawn.
     */
    public void spawnNpcsWithMissions(int npcCount) {
        List<Mission> missions = Mission.loadMissions();
        spawnNpcsWithMissionsHelper(npcCount, missions, 0);
    }
/**
 * Spawn the npcs with mission.
 * 
 * @param npcCount amount of npcs.
 * @param missions list of missions.
 * @param index index.
 */
    private void spawnNpcsWithMissionsHelper(int npcCount, 
            List<Mission> missions, int index) {
        if (index >= npcCount || index >= missions.size()) {
            return; 
        }

        int x = PositionGenerator.getRandomPositionX();
        int y = PositionGenerator.getRandomPositionY();
        Mission mission = missions.get(index);
        Npc npc = new Npc("NPC " + (index + 1), x, y, mission);

        addNpcToMap(npc);

        spawnNpcsWithMissionsHelper(npcCount, missions, index + 1);
    }

    /**
     * Add the npcs to the map.
     *
     * @param npc the npc to be add.
     */
    public void addNpcToMap(Npc npc) {
        if (isValidPosition(npc.getPositionX(), npc.getPositionY())) {
            map[npc.getPositionY()][npc.getPositionX()] = "n";
            npcs.add(npc);
        }
    }

    /**
     * Check if theres a npc near the player.
     *
     * @param player player to check.
     */
    public void checkNearbyNpcs(Player player) {
        checkNearbyNpc(npcs, player, 0);
    }

    /**
     * Check the near npcs to the player.
     *
     * @param npcs list of t
     * @param player
     * @param index
     */
    private void checkNearbyNpc(List<Npc> npcs, Player player, int index) {
        if (index >= npcs.size()) {
            return;
        }

        Npc npc = npcs.get(index);
        if (isNear(player.getPositionX(), player.getPositionY(),
                npc.getPositionX(), npc.getPositionY())) {
            Mission mission = npc.assignMission();
            if (mission != null) {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Mission Assignment");
                alert.setHeaderText("Mission found: " + mission.getTitle());
                alert.setContentText(mission.getDescription() + "\nReward: "
                        + mission.getReward()
                        + "\n\nDo you want to accept this mission?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    player.acceptMission(mission);
                    System.out.println("Mission accepted: " + mission.getTitle());
                }
            }
        }
        checkNearbyNpc(npcs, player, index + 1);
    }

    /**
     * Check if is near.
     * 
     * @param x1 x1.
     * @param y1 y1.
     * @param x2 x2.
     * @param y2 y2.
     * @return 
     */
    private boolean isNear(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
    }

    /**
     * Verified if its a valid position
     *
     * @param x current x of the player
     * @param y current y of the player
     * @return if its valid or not
     */
    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT);
    }

    /**
     * Update the map
     *
     * @param player the current player
     * @param oldRow the old row position of the player
     * @param oldCol the old col position of the player
     */
    public void updateMap(Player player, int oldRow, int oldCol) {
        oldRow = oldRow >= 36 ? 35 : oldRow;
        oldCol = oldCol >= 36 ? 35 : oldCol;
        this.mapClone[oldRow][oldCol] = GameMap.getMap()[oldRow][oldCol];
        int playerRow = player.getPositionY() >= 36 ? 35
                : player.getPositionY();
        int playerCol = player.getPositionX() >= 35 ? 35
                : player.getPositionX();
        this.mapClone[playerRow][playerCol]
                = player.getCharacter().getName().substring(0, 1).toLowerCase();

        this.map = extractSubmatrix(this.mapClone, player.getPositionY(),
                player.getPositionX(), 10);
    }

    /**
     * Creates a string for the players postitions
     *
     * @param players the list of players
     * @param head the current player
     * @param positions the string with the positions
     */
    private void createStringForPlayerPositions(ArrayList<Player> players,
            Player head, String positions) {
        positions = positions + ";" + head.getPosition();
        players.remove(head);
        if (players.isEmpty()) {
            return;
        }
        head = players.get(0);
        createStringForPlayerPositions(players, head, positions);
    }

    /**
     * Gets the players positions
     *
     * @return a String with the position of all the players
     */
    public String getPlayersPosition() {
        String positions = "";
        ArrayList<Player> playersCopy
                = (ArrayList<Player>) Game.instance.players.clone();
        createStringForPlayerPositions(playersCopy,
                playersCopy.get(0), positions);
        return positions;
    }

    /**
     * Gets a mini map of the original map
     *
     * @param playerX player position x
     * @param playerY player position y
     * @return the mini map
     */
    private String[][] getMiniMap(int playerX, int playerY) {
        String[][] miniMap = new String[MAP_HEIGHT][MAP_WIDTH];
        fillMiniMap(playerX, playerY, miniMap, 0, 0);

        return miniMap;
    }

    /**
     * Print a matrix
     *
     * @param matrix to be print
     */
    private static void printMatrix(String[][] matrix) {
        printMatrix(matrix, 0, 0);
    }

    /**
     * Print a matrix.
     * @param matrix matrix.
     * @param i current i.
     * @param j current j.
     */
    private static void printMatrix(String[][] matrix, int i, int j) {
        if (i >= matrix.length) {
            return;
        }
        System.out.print(matrix[i][j] + " ");

        if (j < matrix[i].length - 1) {
            printMatrix(matrix, i, j + 1);
        } else {
            System.out.println();
            printMatrix(matrix, i + 1, 0);
        }
    }

    /**
     * Adjust the rows for always maintain a 10x10 matrix.
     *
     * @param startRow start row.
     * @param endRow end row.
     * @param rows amount of rows.
     * @return the new start row and the end row.
     */
    private static int[] adjustRowLimits(int startRow, int endRow, int rows) {
        if (endRow - startRow < 10) {
            if (endRow == rows) {
                startRow = Math.max(0, endRow - 10);
            } else {
                endRow = Math.min(rows, startRow + 10);
            }
        }
        return new int[]{startRow, endRow};
    }

    /**
     * Adjust the columns for always maintain a 10x10 matrix.
     *
     * @param startCol start column.
     * @param endCol end column.
     * @param cols amount of columns.
     * @return the new start column and the end column.
     */
    private static int[] adjustColLimits(int startCol, int endCol, int cols) {
        if (endCol - startCol < 10) {
            if (endCol == cols) {
                startCol = Math.max(0, endCol - 10);
            } else {
                endCol = Math.min(cols, startCol + 10);
            }
        }
        return new int[]{startCol, endCol};
    }

    /**
     * Extract a submatrix from the map clone
     *
     * @param originalMatrix original matrix
     * @param targetRow target row
     * @param targetCol target column
     * @param size the size of the submatrix
     * @return the submatrix
     */
    public static String[][] extractSubmatrix(String[][] originalMatrix,
            int targetRow, int targetCol, int size) {
        int rows = originalMatrix.length;
        int cols = originalMatrix[0].length;

        size = Math.max(size, 10);

        int startRow = Math.max(0, targetRow - size / 2);
        int startCol = Math.max(0, targetCol - size / 2);
        int endRow = Math.min(startRow + size, rows);
        int endCol = Math.min(startCol + size, cols);

        if (endRow - startRow < 10) {
            if (endRow == rows) {
                startRow = Math.max(0, endRow - 10);
            } else {
                endRow = Math.min(rows, startRow + 10);
            }
        }
        if (endCol - startCol < 10) {
            if (endCol == cols) {
                startCol = Math.max(0, endCol - 10);
            } else {
                endCol = Math.min(cols, startCol + 10);
            }
        }

        int[] adjustedRows = adjustRowLimits(startRow, endRow, rows);
        int[] adjustedCols = adjustColLimits(startCol, endCol, cols);
        startRow = adjustedRows[0];
        endRow = adjustedRows[1];
        startCol = adjustedCols[0];
        endCol = adjustedCols[1];

        String[][] submatrix = new String[endRow - startRow][endCol - startCol];
        fillSubmatrix(originalMatrix, submatrix, startRow,
                startCol, 0, 0, endRow - startRow,
                endCol - startCol, startRow, startCol);
        fillSubmatrix(originalMatrix, submatrix, startRow, startCol, 0, 0,
                endRow - startRow, endCol - startCol, startRow, startCol);

        return submatrix;
    }

    /**
     * Fill the submatrix with the values from the original matrix
     *
     * @param original original matrix
     * @param submatrix sub matrix
     * @param origRow original row
     * @param origCol original colum
     * @param subRow sub matrix row
     * @param subCol sub matrix colum
     * @param numRows number of rows
     * @param numCols number of columns
     * @param auxOrigRow maintain the original row
     * @param auxOrigCol maintain the original column
     */
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
        submatrix[subRow][subCol] = original[origRow][origCol];
        fillSubmatrix(original, submatrix, origRow, origCol + 1, subRow,
                subCol + 1, numRows, numCols, auxOrigRow, auxOrigCol);
    }

    /**
     * Fill the mini map.
     *
     * @param playerX player x position.
     * @param playerY player y position.
     * @param miniMap the minimap.
     * @param row current row.
     * @param col current column.
     */
    private void fillMiniMap(int playerX, int playerY, String[][] miniMap,
            int row, int col) {
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

    /**
     * Get a player by its name.
     *
     * @param name the player name.
     * @param players the list of players.
     * @return the search player.
     */
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
        ArrayList<Player> newPlayers = new ArrayList(
                Server.getGameInstance().players);
        newPlayers.remove(head);
        return getPlayerByName(name, newPlayers);
    }

    /**
     * Ge a player by searching it by its name.
     *
     * @param name of the player to be search.
     * @return the player.
     */
    public String getPlayer(String name) {
        return getPlayerByName(name, Server.getGameInstance().players);
    }

    /**
     * Print a map in console
     *
     * @return the print map
     */
    public String printMap() {
        return printMapHelper(0, 0, new StringBuilder());
    }

    /**
     * Print each cell by it self.
     *
     * @param row current row.
     * @param col current column.
     * @param sb StringBuilder.
     * @return a string with the map.
     */
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

    /**
     * Initialize the map.
     */
    private void initializeMap() {
        this.mapClone = new String[36][36];
        copyMapRecursively(GameMap.getMap(), mapClone, 0, 0);
    }

    /**
     * Creates a copy of the original map.
     *
     * @param original original map.
     * @param clone clone map.
     * @param row current row.
     * @param col current column.
     */
    private void copyMapRecursively(String[][] original, String[][] clone,
            int row, int col) {
        if (row >= original.length) {
            return;
        }

        if (col >= original.length) {
            copyMapRecursively(original, clone, row + 1, 0);
        } else {
            clone[row][col] = original[row][col];
            copyMapRecursively(original, clone, row, col + 1);
        }
    }

    /**
     * Gets the npcs list.
     *
     * @return the npcs.
     */
    public ArrayList<Npc> getNpcsList() {
        if (!this.npcs.isEmpty()) {
            return this.npcs;
        }
        return null;
    }

    /**
     * Creates a string with the npcs info.
     *
     * @return a string with the data.
     */
    public String getNpcs() {
        String response = "";
        getNpcs(this.npcs, response);
        return response;
    }

    /**
     * Creates a string with the npcs data.
     *
     * @param npcs arraylist of npcs.
     * @param response response.
     */
    public void getNpcs(ArrayList<Npc> npcs, String response) {
        if (npcs.isEmpty()) {
            return;
        }
        Npc npc = npcs.get(0);
        response = response + npc.toString();
        npcs.remove(0);
        getNpcs(npcs, response);
    }

}
