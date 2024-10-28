package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import server.Server;

public class Game {

    private static Game instance;

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Npc> npcs = new ArrayList<>();
    private final int MAP_WIDTH = 10;
    private final int MAP_HEIGHT = 10;
    private String[][] map = new String[MAP_HEIGHT][MAP_WIDTH];
    private String[][] mapClone;

    private Game() {
        initializeMap();
        System.out.println("mapclone: ");
        printMatrix(mapClone);
    }

    /**
     *
     * @return
     */
    public String[][] getMapClone() {
        return mapClone;
    }

    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void addPlayer(Player player, int x, int y) {
        if (this.players.size() < 3) {
            this.players.add(player);
            player.setPositionX(x);
            player.setPositionY(y);
        }
    }

    public void spawnNpcsWithMissions(int npcCount) {
        List<Mission> missions = Mission.loadMissions();
        Random random = new Random();

        for (int i = 0; i < npcCount && i < missions.size(); i++) {
            int x = PositionGenerator.getRandomPositionX();
            int y = PositionGenerator.getRandomPositionY();

            Mission mission = missions.get(i);
            Npc npc = new Npc("NPC " + (i + 1), x, y, mission);
            addNpcToMap(npc);
        }
    }

    public void addNpcToMap(Npc npc) {
        if (isValidPosition(npc.getPositionX(), npc.getPositionY())) {
            map[npc.getPositionY()][npc.getPositionX()] = "N";
            npcs.add(npc);
        }
    }

    public void checkNearbyNpcs(Player player) {
        checkNearbyNpc(npcs, player, 0);
    }

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

    private boolean isNear(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
    }

    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT);
    }

    public void updateMap(Player player, int oldRow, int oldCol) {
        oldRow = oldRow >= 36 ? 35 : oldRow;
        oldCol = oldCol >= 36 ? 35 : oldCol;
        this.mapClone[oldRow][oldCol] = GameMap.getMap()[oldRow][oldCol];
        int playerRow = player.getPositionY() >= 36 ? 35 : player.getPositionY();
        int playerCol = player.getPositionX() >= 35 ? 35 : player.getPositionX();
        this.mapClone[playerRow][playerCol]
                = player.getCharacter().getName().substring(0, 1).toLowerCase();

        this.map = extractSubmatrix(this.mapClone, player.getPositionY(),
                player.getPositionX(), 10);
        System.out.println("postions: " + player.getPosition());

    }

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

    public String getPlayersPosition() {
        String positions = "";
        ArrayList<Player> playersCopy = 
                (ArrayList<Player>) Game.instance.players.clone();
        createStringForPlayerPositions(playersCopy,
                playersCopy.get(0), positions);
        return positions;
    }

    private String[][] getMiniMap(int playerX, int playerY) {
        String[][] miniMap = new String[MAP_HEIGHT][MAP_WIDTH];
        fillMiniMap(playerX, playerY, miniMap, 0, 0);

        return miniMap;
    }

    private static void printMatrix(String[][] matrix) {
        printMatrix(matrix, 0, 0);
    }

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

    private static void adjustRowLimits(int endRow, int startRow, int rows) {
        if (endRow - startRow < 10) {
            if (endRow == rows) {
                startRow = Math.max(0, endRow - 10);
            } else {
                endRow = Math.min(rows, startRow + 10);
            }
        }
    }

    private static void adjustColLimits(int endCol, int startCol, int cols) {
        if (endCol - startCol < 10) {
            if (endCol == cols) {
                startCol = Math.max(0, endCol - 10);
            } else {
                endCol = Math.min(cols, startCol + 10);
            }
        }
    }

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

        String[][] submatrix = new String[endRow - startRow][endCol - startCol];
        fillSubmatrix(originalMatrix, submatrix, startRow,
                startCol, 0, 0, endRow - startRow,
                endCol - startCol, startRow, startCol);

        return submatrix;
    }

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

    private void initializeMap() {
        this.mapClone = new String[36][36];
        copyMapRecursively(GameMap.getMap(), mapClone, 0, 0);
    }

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

}
