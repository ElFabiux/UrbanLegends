/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java 
 * to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import game.Client;
import game.GameMap;
import game.Tile;
import game.Time;
import game.TimeObserver;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.UP;
import static javafx.scene.input.KeyCode.W;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * Manages the map, player view, and grid display for the game. It is
 * responsible for rendering the tiles, updating the map, and managing user
 * interactions with the interface.
 *
 * Implements Initializable interface to handle the initialization process.
 *
 *
 * @author Ismael Marchena
 * @author Jorge Rojas
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class MapController implements Initializable, TimeObserver  {

    private static int HEIGHT = 30;
    private static int SIZE = 10;
    private static int WIDTH = 30;
    private int playerCol;
    private int playerRow;
    private static String character;
    private static String[][] map;
    private static String[][] oldMap;
    @FXML
    private AnchorPane playerView;
    @FXML
    private Button btn;
    @FXML
    private static GridPane sceneGrid = new GridPane();
    private static Client client;
    GameMap gameMap;
    private boolean isDaytime = true;
    Time time;
    private ImageView playerIcon;
    private static MapController instance;
    private Tile[][] tileMatrix = new Tile[SIZE][SIZE];

    @Override
    public void update(boolean isDaytime) {
        Platform.runLater(() -> {
            loadMap(gameMap.getCemeteryMap());
        });
    }

    public String getCharacterRoute() {
        switch (MapController.character) {
            case "Researcher":
                return "/Characters/7_1.png";
            case "Hunter":
                return "/Characters/7_2.png";
            case "Witch":
                return "/Characters/7_3.png";
            default:
                throw new AssertionError();
        }
    }
    
    /**
     * Obtains the node from the gridPane
     *
     * @param gridPane the view of the player
     * @param row current row
     * @param col current col
     * @return the corresponding node
     */
    public Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        int index = (row * gridPane.getColumnCount()) + col;

        if (index < gridPane.getChildren().size()) {
            return gridPane.getChildren().get(index);
        }
        return null;
    }
    
    /**
     * Initializes the controller class and add focus to the map for the player
     * inputs.
     *
     * @param url Indicates the search source
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.playerView.getChildren().add(sceneGrid);
        MapController.sceneGrid.setFocusTraversable(true);
        MapController.sceneGrid.addEventHandler(KeyEvent.KEY_PRESSED,
                this::movePlayer);
        playerIcon = new ImageView(getCharacterRoute());
        updatePlayerPosition();
    }
    //    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        this.playerView.getChildren().add(sceneGrid);
//        MapController.sceneGrid.setFocusTraversable(true);
//        MapController.sceneGrid.addEventHandler(KeyEvent.KEY_PRESSED, this::movePlayer);
//        
//        time = new Time();
//        gameMap = new GameMap(time);
//        gameMap.loadTileMatrix();
//        time.addObserver(this);
//        time.startTime();
//        loadMap(gameMap.getCemeteryMap());
//    }
    
    /**
     * Initialize all the constanst
     */
    private static void initializeConstanst() {
        if (MapController.sceneGrid == null) {
            MapController.sceneGrid = new GridPane();
        }
        if (MapController.instance == null) {
            MapController.instance = new MapController();
        }
        if (MapController.instance.gameMap == null) {
            MapController.instance.gameMap = new GameMap();
        }
        if (instance == null) {
            instance = new MapController();
        }
    }

    /**
     * Loads a map from the given 2D matrix of tiles into the scene grid. Clears
     * the current grid and adds tiles from the provided tile matrix.
     *
     * @param tileMatrix The 2D array representing the map.
     */
    private void loadMap(Tile[][] tileMatrix) {
        sceneGrid.getChildren().clear();
        loadMapRecursively(tileMatrix, 0, 0);
    }

    /**
     * Recursively loads the map into the scene grid. Traverses the map matrix
     * row by row and adds corresponding tiles.
     *
     * @param tileMatrix The 2D array representing the map.
     * @param row The current row being processed.
     * @param col The current column being processed.
     */
    private void loadMapRecursively(Tile[][] tileMatrix, int row, int col) {
        if (row >= tileMatrix.length) {
            return;
        }

        if (col >= tileMatrix[row].length) {
            loadMapRecursively(tileMatrix, row + 1, 0);
            return;
        }
        ImageView image = 
                (ImageView) getNodeFromGridPane(MapController.sceneGrid, 
                        row, col);
        if (image == null || !image.equals(tileMatrix[row][col].getImageView()))
        {
            sceneGrid.add(tileMatrix[row][col].getImageView(), col, row);
        }
        loadMapRecursively(tileMatrix, row, col + 1);
    }

     /**
     * Handles the action of the render button. Generates a random 2D array for
     * the player view and calls the request render method.
     *
     * @param event The ActionEvent triggered by pressing the button.
     */
    @FXML
    private void movePlayer(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            String[][] newMap = null;
            switch (event.getCode()) {
                case UP:
                case W:
                    newMap = MapController.client.listenForCommands("up");
                    break;
                case DOWN:
                case S:
                    newMap = MapController.client.listenForCommands("down");
                    break;
                case LEFT:
                case A:
                    newMap = MapController.client.listenForCommands("left");
                    break;
                case RIGHT:
                case D:
                    newMap = MapController.client.listenForCommands("right");
                    break;
                default:
                    break;
            }
            if (newMap != null) {
                requestRender(newMap);
                updatePlayerPosition();
            }

        }
    }
    
    /**
     * Update the old map of the client
     *
     * @param row current row
     * @param col current col
     * @param newMap updated map
     */
    private void render(int row, int col, String[][] newMap) {
        if (col >= SIZE) {
            return;
        }
        if (!newMap[row][col].equals(MapController.oldMap[row][col])) {
            tileMatrix[row][col] = gameMap.createTile(newMap[row][col]);
            MapController.oldMap[row][col] = newMap[row][col];
        }
        if (row < SIZE - 1) {
            render(row + 1, col, newMap);
        } else {
            render(0, col + 1, newMap);
        }
    }
    
    /**
     * Render the corresponding character in the view depending of the
     * MapController.character
     *
     * @param character the char that identifies the character
     * @param row row of the character
     * @param col column of the character
     */
    private void renderPlayers(String character, int row, int col) {
        switch (character) {
            case "r":
                MapController.character = "Researcher";
                break;
            case "w":
                MapController.character = "Witch";
                break;
            case "h":
                MapController.character = "Hunter";
                break;
            default:
                break;
        }
        instance.playerRow = row;
        instance.playerCol = col;
        updatePlayerPosition();
    }
    
    /**
     * Request a render for the client
     *
     * @param newMap updated map
     */
    public static void requestRender(String[][] newMap) {
        MapController.oldMap = MapController.map;
        MapController.map = newMap;
        instance.searchPlayerLocation(0, 0, MapController.map);
        System.out.println("row: " + instance.playerRow);
        System.out.println("col: " + instance.playerCol);
        instance.render(0, 0, MapController.map);
        instance.tileMatrix
                = instance.gameMap.createTileMatrix(MapController.map,
                        instance.tileMatrix);
        instance.loadMapRecursively(instance.tileMatrix, 0, 0);

    }
    
    /**
     * Search the ubication of the player in the view map
     *
     * @param row current row
     * @param col current column
     * @param newMap updated map
     */
    private void searchPlayerLocation(int row, int col, String[][] newMap) {
        if (col >= SIZE) {
            return;
        }
        if (newMap[row][col].equals("r") || newMap[row][col].equals("w")
                || newMap[row][col].equals("h")) {
            renderPlayers(newMap[row][col], row, col);
        }
        if (row < SIZE - 1) {
            searchPlayerLocation(row + 1, col, newMap);
        } else {
            searchPlayerLocation(0, col + 1, newMap);
        }
    }
    
    /**
     * Save the current client to be use and the identification for the
     * character
     *
     * @param client client fromt the server
     * @param character char that identificates the player in the map
     */
    public static void setClient(Client client, String character) {
        MapController.client = client;
        MapController.character = character;
        initializeConstanst();
        startMap();

    }
    
    /**
     * Obtains the map from the server and load the map
     */
    private static void startMap() {
        MapController.map = client.getMap();
        instance.tileMatrix
                = instance.gameMap.createTileMatrix(MapController.map,
                        instance.tileMatrix);
        instance.loadMap(instance.tileMatrix);
    }

    /**
     * Update the view of the player
     */
    private void updatePlayerPosition() {
        sceneGrid.getChildren().remove(playerIcon);
        playerIcon = new ImageView(getCharacterRoute());
        sceneGrid.add(playerIcon, instance.playerCol, instance.playerRow);
        playerIcon.toFront();
    }   
}
