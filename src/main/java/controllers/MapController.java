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
import java.util.Arrays;
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
    private ImageView playerIcon;
    private static String character;
    private int playerRow = 0; // Fila inicial del jugador
    private int playerCol = 0; // Columna inicial del jugador

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
        sceneGrid.add(playerIcon, playerCol, playerRow);
    }

    /**
     * Render the map
     *
     * @param row current row
     * @param col current col
     * @param playerView the new playerView
     */
    public static void setClient(Client client, String character) {
        MapController.client = client;
        MapController.character = character;
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
        instance.tileMatrix = instance.gameMap.createTileMatrix(MapController.map, instance.tileMatrix);
        instance.loadMap(instance.tileMatrix);
    }

    public static void requestRender(String[][] newMap) {
        MapController.oldMap = MapController.map;
        MapController.map = newMap;

        instance.render(0, 0, MapController.map);
        instance.tileMatrix = instance.gameMap.createTileMatrix(MapController.map, instance.tileMatrix);
        instance.loadMapRecursively(instance.tileMatrix, 0, 0);
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
            boolean moved = false;

            switch (event.getCode()) {
                case UP:
                    if (playerRow > 0) {
                        playerRow--;
                        newMap = MapController.client.listenForCommands("left");
                        moved = true;
                    }
                    break;
                case DOWN:
                    if (playerRow < SIZE - 1) {
                        playerRow++;
                        newMap = MapController.client.listenForCommands("right");
                        moved = true;
                    }
                    break;
                case LEFT:
                    if (playerCol > 0) {
                        playerCol--;
                        newMap = MapController.client.listenForCommands("up");
                        moved = true;
                    }
                    break;
                case RIGHT:
                    if (playerCol < SIZE - 1) {
                        playerCol++;
                        newMap =  MapController.client.listenForCommands("down");
                        moved = true;
                    }
                    break;
                default:
                    break;
            }
            if (moved && newMap != null) {
                requestRender(newMap);
                getPosition();
            }
            updatePlayerPosition();
        }
    }

    private void getPosition() {
        String position = MapController.client.getPosition("position");
        System.out.println("posssssssss🐢"+ position);
        String[] xy = position.split(",");
        System.out.println("xy: " + Arrays.toString(xy));
        String row = xy[1].substring(0, 1);
        String col = xy[0].substring(1, 2);
        this.playerRow = Integer.valueOf(row);
        this.playerCol = Integer.valueOf(col);
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
