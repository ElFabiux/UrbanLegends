package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import legends.Cadejo;
import legends.Duende;
import legends.Legend;
import legends.Llorona;
import legends.PadreSinCabeza;
import legends.Segua;
import legends.Sombreron;

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

    // private final int COMPLETE_MAP_HEIGHT = 36;
    // private final int COMPLETE_MAP_WIDTH = 36;
    private final int MAP_HEIGHT = 10;
    private final int MAP_WIDTH = 10;

    private ArrayList<Npc> npcs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Legend> legends = new ArrayList<>();

    private static final Map<String, int[][]> legendAreas = new HashMap<>() {
        {
            put("6", new int[][]{{2, 5}, {10, 15}});
            put("7", new int[][]{{7, 8}, {12, 14}});
            put("5", new int[][]{{3, 1}, {6, 4}});
            put("9", new int[][]{{9, 0}, {11, 3}});
            put("8", new int[][]{{1, 6}, {4, 9}});
            put("0", new int[][]{{6, 6}, {9, 9}});
        }
    };
    private String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];
    private String[][] mapClone;

    private static Game instance;
    private static final Random random = new Random();

    /**
     * Contructor for game and initialize the map
     */
    private Game() {
        initializeMap();
    }

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
        return (x >= 0 && x < MAP_WIDTH && y >= 0 && y
                < MAP_HEIGHT);
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

        int[] adjustedRows = adjustRowLimits(startRow, endRow, rows);
        int[] adjustedCols = adjustColLimits(startCol, endCol, cols);
        startRow = adjustedRows[0];
        endRow = adjustedRows[1];
        startCol = adjustedCols[0];
        endCol = adjustedCols[1];

        String[][] submatrix = new String[endRow - startRow][endCol - startCol];
        fillSubmatrix(originalMatrix, submatrix, startRow,
                startCol, 0, 0,
                endRow - startRow, endCol - startCol, startRow,
                startCol);

        return submatrix;
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
     * Gets all the npcs
     *
     * @return a string with the npcs data
     */
    public String getNpcs() {
        String response = "";
        generateNpcString(this.npcs, response);
        return response;
    }

    /**
     * Gets a List with all Npcs
     *
     * @return npcs list
     */
    public ArrayList<Legend> getLegendList() {
        return legends;
    }

    /**
     * Gets a List with all Npcs
     *
     * @return npcs list
     */
    public ArrayList<Npc> getNpcsList() {
        return npcs;
    }

    /**
     * Gets all the players
     *
     * @return A list of players in game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Get a player by searching it by its name.
     *
     * @param name of the player to be search.
     * @return the player.
     */
    public String getPlayer(String name) {
        return getPlayerByName(name, Server.getGameInstance().players);
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
     * Print a map in console
     *
     * @return the print map
     */
    public String printMap() {
        return printMapHelper(0, 0, new StringBuilder());
    }

    /**
     * Help to print the map recursivily
     *
     * @param row current row
     * @param col current column
     * @param sb StringBuilder
     * @return the map
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
     * Add a legends to a list.
     */
    public void addLegends() {
        legends.add(new Llorona("6", 0, 0));
        legends.add(new Cadejo("9", 0, 0));
        legends.add(new Duende("7", 0, 0));
        legends.add(new PadreSinCabeza("5", 0,
                0));
        legends.add(new Segua("8", 0, 0));
        legends.add(new Sombreron("0", 0, 0));
    }

    /**
     * Add a legend to the map
     *
     * @param legend The legend to be added to the game map.
     */
    public void addLegendToMap(Legend legend) {
        if (isValidPosition(legend.getPositionX(), legend.getPositionY())) {
            map[legend.getPositionY()][legend.getPositionX()] = legend.getName();
            legends.add(legend);
        }
    }

    /**
     * Add a npc to the map
     *
     * @param npc npc that that will be added to the game map.
     */
    public void addNpcToMap(Npc npc) {

        if (isValidPosition(npc.getPositionX(), npc.getPositionY())) {
            map[npc.getPositionY()][npc.getPositionX()] = "N";
            npcs.add(npc);
        }
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
     ** This method examines each NPC in the list to determine if it is close
     * enough to the player. If an NPC is within range, it attempts to assign a
     * mission to the player. If a mission is available, a confirmation dialog
     * is shown, allowing the player to accept the mission.
     *
     * @param npcs The list of NPCs to check for proximity to the player.
     * @param player The player whose surroundings are being checked.
     * @param index The current index in the NPC list for recursive checking.
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
                    System.out.println("Mission accepted: " + mission
                            .getTitle());
                }
            }
        }
        checkNearbyNpc(npcs, player, index + 1);
    }

    /**
     * This method initiates a recursive search to find NPCs close to the
     * player's current position. If an NPC is found nearby, it may trigger
     * interactions such as assigning missions to the player.
     *
     * @param player The player whose surroundings will be checked for nearby
     * NPCs.
     */
    public void checkNearbyNpcs(Player player) {
        checkNearbyNpc(npcs, player, 0);
    }

    /**
     * Copy the original map to the clone
     *
     * @param original original map
     * @param clone clone map
     * @param row current row
     * @param col current column
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
     * Generate a string with the data of the npcs
     *
     * @param npcs arraylist of npcs
     * @param response the data
     */
    private void generateNpcString(ArrayList<Npc> npcs, String response) {
        if (npcs.isEmpty()) {
            return;
        }
        Npc npc = npcs.get(0);
        npcs.remove(0);
        response = ";" + response + npc.toString();
        generateNpcString(npcs, response);
    }

    /**
     * Initialize the map clone
     */
    private void initializeMap() {
        this.mapClone = new String[36][36];
        copyMapRecursively(GameMap.getMap(), mapClone, 0, 0);
    }

    /**
     * Initializes the spawning process for all legends in the game.
     *
     * This method creates a copy of the list of legends and initiates the
     * recursive placement of each legend on the map.
     */
    public void spawnLegends() {
        List<Legend> legendsCopy = new ArrayList<>(legends);
        spawnLegendsRecursively(legendsCopy);
    }

    /**
     * Recursively places each legend in a specified area on the map.
     *
     * The method checks the specified area for each legend, generates a random
     * position within that area, and places the legend on the map if the
     * position is valid.
     *
     * @param legendsList The list of legends to be placed on the map.
     */
    public void spawnLegendsRecursively(List<Legend> legendsList) {
        if (legendsList.isEmpty()) {
            return;
        }

        Legend legend = legendsList.get(0);
        int[][] area = legendAreas.get(legend.getName());

        if (area != null) {
            int xMin = area[0][0];
            int xMax = area[1][0];
            int yMin = area[0][1];
            int yMax = area[1][1];

            int x = random.nextInt(xMax - xMin + 1) + xMin;
            int y = random.nextInt(yMax - yMin + 1) + yMin;

            if (isValidPosition(x, y)) {
                legend.setPositionX(x);
                legend.setPositionY(y);
                map[y][x] = legend.getName();
                addLegendToMap(legend);
            }
        }

        spawnLegendsRecursively(legendsList.subList(1,
                legendsList.size()));
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
     * Print a matrix
     *
     * @param matrix matrix to be print
     * @param i current i
     * @param j current j
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
     * Spawns a specified number of NPCs on the game map, each with an assigned
     * mission. This method loads available missions and associates each NPC
     * with a unique mission.
     *
     * @param npcCount The number of NPCs to spawn with missions.
     */
    public void spawnNpcsWithMissions(int npcCount) {
        List<Mission> missions = Mission.loadMissions();

        if (missions.isEmpty()) {
            System.out.println("No missions loaded. Cannot spawn NPCs.");
            return;
        } else {
            System.out.println("Misiones cargadas" + missions.size());
        }
        spawnNpc(npcCount, missions, 0);
    }

    /**
     * Recursive method to spawn NPCs with missions.
     *
     * @param npcCount The number of NPCs to spawn.
     * @param missions The list of missions to assign to NPCs.
     * @param currentIndex The current index for NPC creation.
     */
    private void spawnNpc(int npcCount, List<Mission> missions,
            int currentIndex) {

        if (npcCount == 0 || currentIndex >= missions.size()) {
            return;
        }

        int x = PositionGenerator.getRandomPositionX();
        int y = PositionGenerator.getRandomPositionY();

        Mission mission = missions.get(currentIndex);
        Npc npc = new Npc("NPC " + (currentIndex + 1), x, y,
                mission);
        addNpcToMap(npc);

        spawnNpc(npcCount - 1, missions, currentIndex + 1);
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
                = player.getCharacter().getName().substring(0, 1)
                        .toLowerCase();

        this.map = extractSubmatrix(this.mapClone, player
                .getPositionY(), player.getPositionX(), 10);
    }
}
