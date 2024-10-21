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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * Manages the map, player view, and grid display for the game.
 * It is responsible for rendering the tiles, updating the map, 
 * and managing user interactions with the interface.
 * 
 * Implements Initializable interface to handle the initialization process.
 * 
 * @author igmml
 */
public class MapController implements Initializable {

    @FXML
    private AnchorPane playerView;
    private static GridPane view;
    private static int SIZE = 10;
    private static int WIDTH = 30;
    private static int HEIGHT = 30;
    @FXML
    private Button btn;
    
    private Tile[][] tileMatrix = new Tile[18][18];
    @FXML
    private GridPane sceneGrid = new GridPane();
    private static char[][] map;
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
        this.playerView.getChildren().add(view);
        MapController.view.setFocusTraversable(true);
        MapController.view.addEventHandler(KeyEvent.KEY_PRESSED, this::movePlayer);
        
        gameMap = new GameMap();
        gameMap.loadTileMatrix();
        loadMap(gameMap.getCemeteryMap());
    }

    /**
     * Creates the grid layout for the player view.
     * This is a recursive method that generates a grid structure.
     * Fill the view of the player with 10 rows and 10 cols
     *
     * @param row The current row index.
     * @param col The current column index.
     * @param grid The GridPane that holds the grid layout.
     * @param cell The node (Label) that represents each grid cell.
     * @return The generated GridPane with the complete grid layout.
     */
    private static void createGrid(int row, int col, Label cell) {
        if (col == SIZE) {
            return;
        }
        cell = new Label();
        cell.setMinSize(WIDTH, HEIGHT);
        cell.setStyle("-fx-border-color: black;");
        cell.setText(String.valueOf(map[row][col]));
        MapController.view.add(cell, row, col);


        if (row < SIZE - 1) {
            createGrid(row + 1, col, cell);
        } else {
            createGrid(0, col + 1, cell);
        }
    }

    /**
     * Retrieves a Node from the GridPane by its row and column index.
     * This method finds the corresponding Node (e.g., Label) in the grid.
     *
     * @param gridPane The GridPane from which to retrieve the node.
     * @param row The row index.
     * @param col The column index.
     * @return The Node at the specified row and column, or null if not found.

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
     * Renders the player view based on the new layout of the grid.
     * Updates each cell in the grid with the corresponding value.
     * This is a recursive method that traverses the entire grid.
     *
     * @param row The current row being rendered.
     * @param col The current column being rendered.
     * @param playerView The 2D array representing the player's current view.
     * Render the map
     *
     * @param row current row
     * @param col current col
     * @param playerView the new playerView
     */
    public void render(int row, int col, char[][] playerView) {
        if (col == SIZE) {
            return;
        }
        Label cell = (Label) getNodeFromGridPane(view, row, col);

        if (cell != null) {
            if (!String.valueOf(playerView[row][col]).equals(cell.getText())) {
                cell.setText(String.valueOf(playerView[row][col]));
                //change the image
            }
        
        if (row < SIZE - 1) {
            render(row + 1, col, playerView);
        } else {
            render(0, col + 1, playerView);
        }
        }
    }

    public static void setClient(Client client) {
        MapController.client = client;
        if (MapController.view == null) {
            MapController.view = new GridPane();
            MapController.instance = new MapController();
        }
        startMap();

    }

    private static void startMap() {
        MapController.map = client.getMap();
        createGrid(0, 0, new Label());
        MapController mp = new MapController();
        mp.render(0, 0, map);
    }
    
    public static void requestRender(char[][] newMap){
        MapController.map = newMap;
        instance.render(0, 0, map);
    }

    /**
     * Handles the action of the render button.
     * Generates a random 2D array for the player view and calls the render method.
     *
     * @param event The ActionEvent triggered by pressing the button.
     */
    @FXML
    private void movePlayer(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {

            char[][] newMap = null;
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
    
    /**
     * Loads a map from the given 2D matrix of tiles into the scene grid.
     * Clears the current grid and adds tiles from the provided tile matrix.
     *
     * @param tileMatrix The 2D array representing the map.
     */
    private void loadMap(Tile[][] tileMatrix) {
        sceneGrid.getChildren().clear();
        loadMapRecursively(tileMatrix, 0, 0);
    }

    /**
     * Recursively loads the map into the scene grid.
     * Traverses the map matrix row by row and adds corresponding tiles.
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

        Tile tile = tileMatrix[row][col];
        if (tile != null) {
            sceneGrid.add(tile.getImageView(), col, row);
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
