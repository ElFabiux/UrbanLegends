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
import game.ClientPlayer;
import game.GameMap;
import game.Npc;
import game.Tile;
import game.Time;
import game.TimeObserver;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class MapController implements Initializable, TimeObserver {

    private static int HEIGHT = 30;
    private int playerCol;
    private int playerRow;
    private static int SIZE = 10;
    private static int WIDTH = 30;

    private ArrayList<ClientPlayer> nearPlayers = new ArrayList<>();
    private static String[][] map;
    private static String[][] oldMap;
    private Tile[][] tileMatrix = new Tile[SIZE][SIZE];

    @FXML
    private AnchorPane playerView;
    @FXML
    private AnchorPane howToPlay;
    private static Client client;
    private GameMap gameMap;
    @FXML
    private static GridPane sceneGrid = new GridPane();
    private ImageView playerIcon;
    private static MapController instance;
    private static String character;
    Time time;
    

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
     * Gets the route of the image of the corresponding character
     *
     * @param character the character to be search
     * @return url of the image of the character
     */
    public String getCharacterRoute(String character) {
        switch (character) {
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
     * Adds all the near player to an arrayList
     *
     * @param character the character of each player
     * @param row current row
     * @param col current col
     */
    private void addNearPlayer(String character, int row, int col) {
        String playerCharacter = character.substring(0, 1)
                .toLowerCase();
        ImageView icon = new ImageView(getCharacterRoute(character));
        ClientPlayer clientPlayer = new ClientPlayer(col, row, icon);
        instance.nearPlayers.add(clientPlayer);
    }

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
            MapController.instance.gameMap.loadTileMatrix();
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
        ImageView image
                = (ImageView) getNodeFromGridPane(MapController.sceneGrid,
                        row, col);
        if (image == null || !image.equals(tileMatrix[row][col]
                .getImageView())) {
            sceneGrid.add(tileMatrix[row][col].getImageView(), col,
                    row);
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
                    newMap = MapController.client.listenForCommands(
                            "down");
                    break;
                case LEFT:
                case A:
                    newMap = MapController.client.listenForCommands(
                            "left");
                    break;
                case RIGHT:
                case D:
                    newMap = MapController.client.listenForCommands(
                            "right");
                    break;
                default:
                    break;
            }
            if (newMap != null) {
                requestRender(newMap);
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
                addNearPlayer("Researcher", row, col);
                break;
            case "w":
                addNearPlayer("Witch", row, col);
                break;
            case "h":
                addNearPlayer("Hunter", row, col);
                break;
            default:
                break;
        }
    }

    /**
     * Render all the near players
     *
     * @param nearPlayers arrayList of near players
     * @param head the current near player
     */
    private void renderPlayers(ArrayList<ClientPlayer> nearPlayers) {
        if (nearPlayers.isEmpty()) {
            return;
        }
        ClientPlayer currentPlayer = nearPlayers.get(0);
        updatePlayerPosition(currentPlayer);
        nearPlayers.remove(0);
        renderPlayers(nearPlayers);
    }

    /**
     * Render the visible players
     *
     * @param map current map
     */
    private void renderVisiblePlayers(String[][] map) {
        instance.nearPlayers.clear();
        searchVisiblePlayers(0, 0, MapController.map);
        renderPlayers(instance.nearPlayers);
    }

    /**
     * Request a render for the client
     *
     * @param newMap updated map
     */
    public static void requestRender(String[][] newMap) {
        MapController.oldMap = MapController.map;
        MapController.map = newMap;
        instance.render(0, 0, MapController.map);
        instance.tileMatrix
                = instance.gameMap.createTileMatrix(MapController.map,
                        instance.tileMatrix);
        instance.loadMapRecursively(instance.tileMatrix, 0, 0);
        instance.renderVisiblePlayers(MapController.map);
    }

    /**
     * Search the ubication of the player in the view map
     *
     * @param row current row
     * @param col current column
     * @param newMap updated map
     */
    private void searchVisiblePlayers(int row, int col, String[][] newMap) {
        if (col >= SIZE) {
            return;
        }
        if (newMap[row][col].equals("r") || newMap[row][col].equals(
                "w")
                || newMap[row][col].equals("h")) {
            renderPlayers(newMap[row][col], row, col);
        }
        if (row < SIZE - 1) {
            searchVisiblePlayers(row + 1, col, newMap);
        } else {
            searchVisiblePlayers(0, col + 1, newMap);
        }
    }

    /**
     * Save the current client to be use and the identification for the
     * character
     *......................................................................
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
    private void updatePlayerPosition(ClientPlayer clientPlayer) {
        if (sceneGrid.getChildren().contains(clientPlayer.getIcon())) {
            sceneGrid.getChildren().remove(clientPlayer.getIcon());
        }
        sceneGrid.add(clientPlayer.getIcon(), clientPlayer.getCol(),
                clientPlayer.getRow());
        clientPlayer.getIcon().toFront();
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
    }
    
    /**
     * Update the the game map.
     * 
     * @param isDaytime the current time.
     */
    @Override
    public void update(boolean isDaytime) {
        Platform.runLater(() -> {
            loadMap(gameMap.getCemeteryMap());
        });
    }
}