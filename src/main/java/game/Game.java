package game;

import java.util.Vector;

import game.MovementsLogic;

public class Game {

    private static Game instance;

    private Vector<Player> players = new Vector<>(3);
    private final int MAP_WIDTH = 10;
    private final int MAP_HEIGHT = 10;
    public char[][] map = new char[MAP_WIDTH][MAP_HEIGHT];

    private MovementsLogic movementsLogic;

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
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i][j] = '.';
            }
        }
    }

    public void addPlayer(Player player, int x, int y) {
        if (players.size() < 3 && isValidPosition(x, y)) {
            players.add(player);
            player.setPositionX(x);
            player.setPositionY(y);
            map[y][x] = 'P';
        }
    }

    // Validar si una posición es válida en el mapa
    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT);
    }

    public void updateMap(Player player, int oldX, int oldY) {

        if (isValidPosition(oldX, oldY) && isValidPosition(
                player.getPositionX(), player.getPositionY())) {
            //  checkMovements(player, oldX, oldY);
          //  movementsLogic.checkMovements(map, player, oldX, oldY, 'P');

        } else {
            System.out.println("Error: La nueva posición está fuera del mapa.");
        }
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
