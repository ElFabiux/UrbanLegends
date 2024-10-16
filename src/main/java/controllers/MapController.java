/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import game.Tile;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private static int SIZE = 20;
    private static int WIDTH = 30;
    private static int HEIGHT = 30;
    @FXML
    private Button btn;
    
    private Tile[][] tileMatrix = new Tile[18][18];
    @FXML
    private GridPane sceneGrid = new GridPane();

    /**
     * Initializes the controller class and loads the initial map.
     * Called when the FXML file is loaded.
     *
     * @param url The location used to resolve relative paths for the root object, or null if not applicable.
     * @param rb The resources used to localize the root object, or null if not applicable.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMap(villageMap);
    }
    
    /**
     * Creates the grid layout for the player view.
     * This is a recursive method that generates a grid structure.
     *
     * @param row The current row index.
     * @param col The current column index.
     * @param grid The GridPane that holds the grid layout.
     * @param cell The node (Label) that represents each grid cell.
     * @return The generated GridPane with the complete grid layout.
     */
    private GridPane createGrid(int row, int col, GridPane grid, Label cell) {
        if (col == SIZE) {
            return grid;
        }
        cell = new Label();
        cell.setMinSize(WIDTH, HEIGHT);
        cell.setStyle("-fx-border-color: black;");
        cell.setText("0");
        view.add(cell, col, row);

        if (row < SIZE - 1) {
            return createGrid(row + 1, col, grid, cell);
        } else {
            return createGrid(0, col + 1, grid, cell);
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
     */
    public void render(int row, int col, String[][] playerView) {
        if (col == SIZE) {
            return;
        }
        Label cell = (Label) getNodeFromGridPane(view, row, col);
        if (!playerView[row][col].equals(cell.getText())) {
            cell.setText(playerView[row][col]);
            // Update the image or any additional properties as needed
        }
        if (row < SIZE - 1) {
            render(row + 1, col, playerView);
        } else {
            render(0, col + 1, playerView);
        }
    }

    /**
     * Handles the action of the render button.
     * Generates a random 2D array for the player view and calls the render method.
     *
     * @param event The ActionEvent triggered by pressing the button.
     */
    @FXML
    private void render(ActionEvent event) {
        String[][] newPlayerView = new String[SIZE][SIZE];
        Random random = new Random();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                newPlayerView[row][col] = String.valueOf(random.nextInt(11));
            }
        }
        render(0, 0, newPlayerView);
    }

        private static final String[][] villageMap = {
        {"R1", "G", "G", "G", "G", "G", "G", "G", "G", "T2", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "T1", "T1", "G", "G", "G", "G", "G", "T2", "G", "G", "G", "H6", "H4", "G", "G", "T3", "T3"},
        {"R1", "G", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L"},
        {"R1", "G", "L", "G", "G", "H5", "T", "H5", "G", "G", "T3", "T3", "T3", "T3", "T3", "T3", "T3", "G"},
        {"R1", "G", "L", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "G", "G", "G", "T2", "G", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "H3", "G", "G", "G", "G", "G", "G", "G", "G", "H5", "H5", "G", "G", "G", "G"},
        {"R1", "T2", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "G", "G"},
        {"R1", "G", "L", "T3", "T3", "G", "G", "H4", "L", "H1", "G", "T3", "T3", "H2", "G", "H3", "G", "G"},
        {"R1", "G", "L", "T3", "G", "G", "G", "T3", "L", "H5", "G", "T3", "T3", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "H1", "G", "G", "G", "H6", "L", "G", "G", "T3", "G", "G", "T1", "G", "G", "G"},
        {"R1", "G", "L", "T1", "G", "G", "G", "T3", "L", "T3", "G", "G", "T2", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "G", "G", "G", "G", "G", "L", "H6", "G", "G", "G", "G", "G", "T1", "G", "G"},
        {"R1", "G", "L", "H4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R5", "R3", "L", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3"},
        {"R4", "R2", "L", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2"},
        {"R1", "G", "L", "T", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "L", "G", "G", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"}
    };
    
    private static final String[][] forestMap = {
        {"R1", "G", "G", "T6", "G", "G", "T3", "G", "T7", "G", "G", "T2", "G", "G", "G", "G", "G", "T5"},
        {"R1", "T1", "G", "G", "G", "G", "T4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "G", "G", "G", "T2", "G", "T1", "G", "G", "G", "T3", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "T5", "G", "G", "G", "G", "G", "T1", "G", "G", "G", "T3", "T6", "G"},
        {"R1", "G", "G", "G", "G", "G", "G", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "G", "G", "T3", "T3", "G", "G", "G", "G", "T7", "G", "G", "G", "G"},
        {"R1", "G", "T2", "G", "G", "T6", "G", "G", "G", "T1", "G", "G", "G", "G", "T5", "G", "G", "G"},
        {"R1", "G", "G", "T2", "G", "G", "G", "G", "G", "G", "G", "G", "T6", "G", "G", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "T7", "G", "G", "T2", "G", "G", "G", "G", "G", "T3", "G", "T2", "G"},
        {"R1", "G", "T4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T1", "G", "G", "G", "G", "G"},
        {"R1", "T3", "T3", "G", "G", "G", "T7", "G", "G", "G", "G", "G", "G", "T3", "G", "G", "G", "G"},
        {"R1", "T3", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T5", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "T3", "T3", "T3", "G", "G", "G", "G", "G"},
        {"R1", "T5", "G", "G", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "G", "G", "T2", "G", "G"},
        {"R1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T6", "G"},
        {"R1", "G", "G", "T7", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "T5", "G", "G", "G", "G"}
    };

    private static final String[][] cemeteryMap = {
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "T3", "T3", "T3"},
        {"O5", "G", "G", "G", "G", "G", "O5", "G", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"O5", "G", "O5", "G", "G", "G", "G", "T3", "C", "T3", "G", "G", "G", "G", "G", "G", "G", "T3"},
        {"O5", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "G"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5"},
        {"O5", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "T3", "G", "G", "G", "G", "G", "G", "G"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G"},
        {"O5", "T3", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O2", "G", "T3", "G", "G", "G"},
        {"G", "G", "G", "G", "O5", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "T3", "G", "O2"},
        {"G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O2", "G", "O1", "G", "G", "G", "G"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "T3", "T3", "G", "G", "G", "T3", "G", "O1", "G", "G"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "T3", "G", "O3", "G", "G", "G", "O3", "G", "T3"},
        {"O5", "G", "G", "G", "G", "G", "O5", "G", "G", "O3", "G", "G", "G", "T3", "G", "G", "G", "G"},
        {"O5", "G", "G", "G", "O3", "G", "G", "G", "G", "G", "G", "G", "G", "O3", "G", "O5", "G", "O1"},
        {"O5", "O5", "G", "G", "G", "G", "G", "O5", "G", "O2", "G", "O1", "G", "G", "G", "G", "G", "O3"},
        {"O5", "G", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "O3", "G", "G", "G", "O1", "G", "O1"},
        {"O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T3", "O5", "G", "O2", "G", "G", "G", "O5"},
        {"O5", "G", "T3", "T3", "G", "T3", "G", "G", "O1", "G", "G", "G", "G", "O3", "G", "O2", "O5", "O5"}
    };
    
    private static final String[][] churchMap = {
        {"W", "W", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "W"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "O", "O", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"F", "F", "F", "W", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "W"},
        {"F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "W", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "W", "F", "F", "F", "F", "F"},
        {"F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"W", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"F", "W", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"W", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "W"}
    };
    
    /**
     * Loads a map from the given 2D matrix into the scene grid.
     * Clears the current grid and recursively adds tiles from the map.
     *
     * @param mapMatrix The 2D array representing the map.
     */
    private void loadMap(String[][] mapMatrix) {
        sceneGrid.getChildren().clear();
        loadMapRecursively(mapMatrix, 0, 0);
    }

    /**
     * Recursively loads the map into the scene grid.
     * Traverses the map matrix row by row and adds corresponding tiles.
     *
     * @param mapMatrix The 2D array representing the map.
     * @param row The current row being processed.
     * @param col The current column being processed.
     */
    private void loadMapRecursively(String[][] mapMatrix, int row, int col) {
        if (row >= mapMatrix.length) {
            return;
        }

        if (col >= mapMatrix[row].length) {
            loadMapRecursively(mapMatrix, row + 1, 0);
            return;
        }

        String tileChar = mapMatrix[row][col];
        Tile tile = createTile(tileChar);
        tileMatrix[row][col] = tile;
        sceneGrid.add(tile.getImageView(), col, row);

        loadMapRecursively(mapMatrix, row, col + 1);
    }

    /**
     * Creates a Tile object based on the character representing the tile type.
     *
     * @param tileChar The character representing the tile type (e.g., 'G' for grass).
     * @return The Tile object corresponding to the tile character.
     */
    private Tile createTile(String tileChar) {
        String tileType = getTileType(tileChar);
        String imagePath = getTileImagePath(tileType);
        return new Tile(tileType, imagePath);
    }

    /**
     * Maps a tile character to the corresponding tile type.
     *
     * @param tileChar The character representing the tile type.
     * @return A string representing the tile type, mapped to its corresponding image.
     */
    private String getTileType(String tileChar) {
        switch (tileChar) {
            case "G": return "1_1";
            case "L": return "1_2";
            case "T": return "1_3";
            case "H1": return "2_1";
            case "H2": return "2_2";
            case "H3": return "2_3";
            case "H4": return "2_4";
            case "H5": return "2_5";
            case "H6": return "2_6";
            case "R1": return "3_1";
            case "R2": return "3_2";
            case "R3": return "3_3";
            case "R4": return "3_4";
            case "R5": return "3_5";
            case "T1": return "4_1";
            case "T2": return "4_2";
            case "T3": return "4_3";
            case "T4": return "4_4";
            case "T5": return "4_5";
            case "T6": return "4_6";
            case "T7": return "4_7";
            case "O1": return "5_1";
            case "O2": return "5_2";
            case "O3": return "5_3";
            case "C": return "5_4";
            case "O5": return "5_5";
            case "F": return "6_1";
            case "S2": return "6_2";
            case "S3": return "6_3";
            case "S4": return "6_4";
            case "O": return "6_5";
            case "R": return "6_6";
            case "W": return "6_7";
            default: return "1_1";
        }
    }

    /**
     * Maps a tile type to its corresponding image path.
     *
     * @param tileType The string representing the tile type (e.g., "1_1" for a grass tile).
     * @return The image path corresponding to the tile type.
     */
    private String getTileImagePath(String tileType) {
        switch (tileType) {
            case "1_1": return "/Grass/1_1.jpg";
            case "1_2": return "/Grass/1_2.png";
            case "1_3": return "/Grass/1_3.png";
            case "2_1": return "/House/2_1.jpg";
            case "2_2": return "/House/2_2.jpg";
            case "2_3": return "/House/2_3.jpg";
            case "2_4": return "/House/2_4.jpg";
            case "2_5": return "/House/2_5.jpg";
            case "2_6": return "/House/2_6.jpg";
            case "3_1": return "/Water/3_1.jpg";
            case "3_2": return "/Water/3_2.jpg";
            case "3_3": return "/Water/3_3.jpg";
            case "3_4": return "/Water/3_4.jpg";
            case "3_5": return "/Water/3_5.jpg";
            case "4_1": return "/Forest/4_1.png";
            case "4_2": return "/Forest/4_2.png";
            case "4_3": return "/Forest/4_3.png";
            case "4_4": return "/Forest/4_4.png";
            case "4_5": return "/Forest/4_5.png";
            case "4_6": return "/Forest/4_6.png";
            case "4_7": return "/Forest/4_7.png";
            case "5_1": return "/Cemetery/5_1.png";
            case "5_2": return "/Cemetery/5_2.png";
            case "5_3": return "/Cemetery/5_3.png";
            case "5_4": return "/Cemetery/5_4.png";
            case "5_5": return "/Cemetery/5_5.png";
            case "6_1": return "/Church/6_1.png";
            case "6_2": return "/Church/6_2.png";
            case "6_3": return "/Church/6_3.png";
            case "6_4": return "/Church/6_4.png";
            case "6_5": return "/Church/6_5.png";
            case "6_6": return "/Church/6_6.png";
            case "6_7": return "/Church/6_7.png";
            default: return "/Grass/1_1.jpg";
        }
    }

    /**
     * Retrieves the GridPane that represents the scene grid.
     * 
     * @return The GridPane representing the scene.
     */
    public GridPane getGridPane() {
        return sceneGrid;
    }
}
