/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private static int SIZE = 20;
    private static int WIDTH = 30;
    private static int HEIGHT = 30;
    @FXML
    private Button btn;

    /**
     * Fill the view of the player with 20 rows and 20 cols
     *
     * @param row current row
     * @param col current col
     * @param grid the player view
     * @param cell the node that will be insert
     * @return the player view with nothing
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

        if (row < SIZE-1) {
            return createGrid(row + 1, col, grid, cell);
        } else {
            return createGrid(0, col + 1, grid, cell);
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MapController.view = new GridPane();
        createGrid(0, 0, view, new Label());
        this.playerView.getChildren().add(view);
    }
    
    /**
     * Obtains the node from the gridPane
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
     * Render the new layout
     * @param row current row
     * @param col current col
     * @param playerView the new playerView
     */
    public void render(int row, int col, String[][] playerView) {
        if (col == SIZE) {
            return;
        }
        Label cell = (Label) getNodeFromGridPane(view, row, col);
        if (!playerView[row][col].equals(cell.getText())) {
            cell.setText(playerView[row][col]);
            //change the image
        }
        if(row < SIZE-1){
            render(row+1, col, playerView);
        }else{
            render(0, col+1, playerView);
        }
    }

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
}
