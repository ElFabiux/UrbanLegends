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
import javafx.scene.control.Label;
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

    /**
     * Initializes the controller class and add focus to the map for the player
     * inputs.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.playerView.getChildren().add(sceneGrid);
        MapController.sceneGrid.setFocusTraversable(true);
        MapController.sceneGrid.addEventHandler(KeyEvent.KEY_PRESSED, this::movePlayer);
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
     * Render the map
     *
     * @param row current row
     * @param col current col
     * @param playerView the new playerView
     */
    public static void setClient(Client client) {
        MapController.client = client;
        if (MapController.sceneGrid == null) {
            MapController.sceneGrid = new GridPane();
            MapController.instance = new MapController();
        }
        startMap();

    }

    private static void startMap() {
        MapController.map = client.getMap();
        if (instance == null) {
            instance = new MapController();
        }
        instance.gameMap = new GameMap();
        instance.tileMatrix = instance.gameMap.createTileMatrix(MapController.map);
        System.out.println("tile: "+instance.tileMatrix[9][9]);
        instance.loadMap(instance.tileMatrix);
    }

    public static void requestRender(String[][] newMap) {
        MapController.oldMap = MapController.map;
        MapController.map = newMap;

        instance.render(0, 0, MapController.map);
        instance.tileMatrix = instance.gameMap.createTileMatrix(MapController.map);
        instance.loadMapRecursively(instance.tileMatrix, 0, 0);
    }

    private void render(int row, int col, String[][] newMap) {
        //1. Recorrer el nuevo mapa
        //2. Verificar si hay algo diferente del mapa anterior
        //3. Cambiar lo que haya cambiado
        //4. Renderizar el nuevo mapa
        if (row >= SIZE) {
            return;
        }
        if (col >= SIZE) {
            render(row + 1, 0, newMap);
            return;
        }
        if (!newMap[row][col].equals(MapController.oldMap[row][col])) {
            tileMatrix[row][col] = gameMap.createTile(newMap[row][col]);
            MapController.oldMap[row][col] = newMap[row][col];
        }

        render(row, col + 1, newMap);
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
                    newMap = MapController.client.listenForCommands("up");
                    break;
                case DOWN:
                    newMap = MapController.client.listenForCommands("down");
                    break;
                case LEFT:
                    newMap = MapController.client.listenForCommands("left");
                    break;
                case RIGHT:
                    newMap = MapController.client.listenForCommands("right");
                    break;
                default:
                    break;
            }
            if (newMap != null) {
                requestRender(newMap);
            }
        }
    }

    private String getPosition() {
        return MapController.client.getPosition("position");
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

    /**
     * Switches the current map to the village map.
     */
    @FXML
    private void loadVillageMap() {
        loadMap(gameMap.getVillageMap());
    }

    /**
     * Switches the current map to the forest map.
     */
    @FXML
    private void loadForestMap() {
        loadMap(gameMap.getForestMap());
    }

    /**
     * Switches the current map to the cemetery map.
     */
    @FXML
    private void loadCemeteryMap() {
        loadMap(gameMap.getCemeteryMap());
    }

    /**
     * Switches the current map to the church map.
     */
    @FXML
    private void loadChurchMap() {
        loadMap(gameMap.getChurchMap());
    }
}
