/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import com.mycompany.urbanlegends.App;
import game.Client;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
 * @author igmml
 */
public class MapController implements Initializable {

    @FXML
    private AnchorPane playerView;
    private static GridPane view;
    private static int SIZE = 10;
    private static int WIDTH = 30;
    private static int HEIGHT = 30;
    private static char[][] map;
    private static Client client;
    private static MapController instance; 

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
    }

    /**
     * Fill the view of the player with 10 rows and 10 cols
     *
     * @param row current row
     * @param col current col
     * @param grid the player view
     * @param cell the node that will be insert
     * @return the player view with nothing
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
        }
        if (row < SIZE - 1) {
            render(row + 1, col, playerView);
        } else {
            render(0, col + 1, playerView);
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
}
