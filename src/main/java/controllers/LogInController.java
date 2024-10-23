/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import com.mycompany.urbanlegends.App;
import playableCharacters.Character;
import playableCharacters.Hunter;
import playableCharacters.Researcher;
import playableCharacters.Witch;
import game.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author igmml
 */
public class LogInController implements Initializable {

    @FXML
    private VBox startMenuContainer;
    @FXML
    private Button newGame;
    @FXML
    private Button score;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane createPlayerMenuContainer;
    @FXML
    private ToggleGroup characterGroup;
    @FXML
    private Button start;

    private Character character;
    @FXML
    private Button back;
    @FXML
    private TextField playerName;
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void newGame(ActionEvent event) {
        this.startMenuContainer.setVisible(false);
        this.startMenuContainer.setDisable(true);
        this.createPlayerMenuContainer.setVisible(true);
        this.createPlayerMenuContainer.setDisable(false);
    }

    @FXML
    private void getScore(ActionEvent event) throws IOException {
        App.setRoot("Map");
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void setChracter(ActionEvent event) {
        String name = this.playerName.getText();
        RadioButton selectedRadioButton = (RadioButton) this.characterGroup.getSelectedToggle();
        String value = selectedRadioButton.getText();
        switch (value) {
            case "Witch":
                this.character = new Witch("Witch", 0, 0, 0);
                break;
            case "Hunter":
                this.character = new Hunter("Hunter", 0, 0, 0);
                break;
            case "Researcher":
                this.character = new Researcher("Researcher", 0, 0, 0);
                break;
            default:
                throw new AssertionError();
        }
    }

    @FXML
    private void start(ActionEvent event) {
        String[] player = new String[2];
        player[0] = this.playerName.getText();
        player[1] = this.character.getName();
        Client client = Client.main(player);
        MapController.setClient(client);
        
        App.setRoot("Map");
    }

    @FXML
    private void back(ActionEvent event) {
        this.startMenuContainer.setVisible(true);
        this.startMenuContainer.setDisable(false);
        this.createPlayerMenuContainer.setVisible(false);
        this.createPlayerMenuContainer.setDisable(true);
    }

}
