/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import game.Client;
import game.GameMap;
import game.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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
 * @author igmml
 */
public class MapController implements Initializable {

    @FXML
    private AnchorPane playerView;
    private static int SIZE = 10;
    private static int WIDTH = 30;
    private static int HEIGHT = 30;
    @FXML
    private Button btn;

    private Tile[][] tileMatrix = new Tile[SIZE][SIZE];
    @FXML
    private static GridPane sceneGrid = new GridPane();
    private static String[][] map;
    private static String[][] oldMap;
    private static Client client;
    private static MapController instance;

    GameMap gameMap;
    private ImageView playerIcon;
    private static String character;
    private int playerRow;
    private int playerCol;

    /**
     * Initializes the controller class and add focus to the map for the player
     * inputs.
     *
     * @param url for indicate the sourche searching 
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.playerView.getChildren().add(sceneGrid);
        MapController.sceneGrid.setFocusTraversable(true);
        MapController.sceneGrid.addEventHandler(KeyEvent.KEY_PRESSED, this::movePlayer);

        playerIcon = new ImageView(getCharacterRoute());
        updatePlayerPosition();
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

    
    private void updatePlayerPosition() {
        sceneGrid.getChildren().remove(playerIcon);
        playerIcon = new ImageView(getCharacterRoute());
        sceneGrid.add(playerIcon, instance.playerCol, instance.playerRow);
        playerIcon.toFront();
    }

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
    }

    public static void setClient(Client client, String character) {
        MapController.client = client;
        MapController.character = character;
        initializeConstanst();
        startMap();

    }

    private static void startMap() {
        MapController.map = client.getMap();
        if (instance == null) {
            instance = new MapController();
        }
        instance.tileMatrix
                = instance.gameMap.createTileMatrix(MapController.map,
                        instance.tileMatrix);
        instance.loadMap(instance.tileMatrix);
    }

    public static void requestRender(String[][] newMap) {
        MapController.oldMap = MapController.map;
        MapController.map = newMap;
        instance.searchrPlayerLocation(0, 0, MapController.map);
        System.out.println("row: " + instance.playerRow);
        System.out.println("col: " + instance.playerCol);
        instance.render(0, 0, MapController.map);
        instance.tileMatrix
                = instance.gameMap.createTileMatrix(MapController.map,
                        instance.tileMatrix);
        instance.loadMapRecursively(instance.tileMatrix, 0, 0);

    }

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

    private void searchrPlayerLocation(int row, int col, String[][] newMap) {
        if (col >= SIZE) {
            return;
        }
        if (newMap[row][col].equals("r") || newMap[row][col].equals("w") || 
                newMap[row][col].equals("h")) {
            renderPlayers(newMap[row][col], row, col);
        }
        if (row < SIZE - 1) {
            searchrPlayerLocation(row + 1, col, newMap);
        } else {
            searchrPlayerLocation(0, col + 1, newMap);
        }
    }

    private void render(int row, int col, String[][] newMap) {
        //1. Recorrer el nuevo mapa
        //2. Verificar si hay algo diferente del mapa anterior
        //3. Cambiar lo que haya cambiado
        //4. Renderizar el nuevo mapa
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
     * Handles the action of the render button. Generates a random 2D array for
     * the player view and calls the render method.
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
        ImageView image = (ImageView) getNodeFromGridPane(MapController.sceneGrid, row, col);
        if (image == null || !image.equals(tileMatrix[row][col].getImageView())) {
            sceneGrid.add(tileMatrix[row][col].getImageView(), col, row);
        }
        loadMapRecursively(tileMatrix, row, col + 1);
    }
}
