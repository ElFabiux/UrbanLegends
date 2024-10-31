/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java 
 * to edit this template
 */
package controllers;

import com.mycompany.urbanlegends.App;
import playableCharacters.Character;
import playableCharacters.Hunter;
import playableCharacters.Researcher;
import playableCharacters.Witch;
import game.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The controller for the login interface
 * 
 * Implements Initializable interface to handle the initialization process.
 *
 * @author Ismael Marchena
 * @author Jorge Rojas
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class LogInController implements Initializable {

    @FXML
    private AnchorPane aboutContainer;
    @FXML
    private AnchorPane createPlayerMenuContainer;
    @FXML
    private AnchorPane container;
    @FXML
    private Button about;
    @FXML
    private Button back;
    @FXML
    private Button back1;
    @FXML
    private Button exit;
    @FXML
    private Button newGame;
    @FXML
    private Button start;
    
    private Character character;
    @FXML
    private ImageView bg;
    
    @FXML
    private TextField playerName;
    @FXML
    private ToggleGroup characterGroup;
    @FXML
    private VBox startMenuContainer;
  
    /**
     * Return from the player menu to the game menu
     * 
     * @param event 
     */
    @FXML
    private void back(ActionEvent event) {
        this.bg.setVisible(true);
        this.startMenuContainer.setVisible(true);
        this.startMenuContainer.setDisable(false);
        this.createPlayerMenuContainer.setVisible(false);
        this.createPlayerMenuContainer.setDisable(true);
        this.aboutContainer.setVisible(false);
        this.aboutContainer.setDisable(true);
    }
    
    /**
     * Exit the program
     * 
     * @param event ActionEvent
     */
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    Hunter hunter;
    Researcher researcher;
    Witch witch;

    /**
     * Initialize the login controller
     *
     * @param url Indicates the search source
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hunter = new Hunter();
        researcher = new Researcher();
        witch = new Witch();
    }
    
    /**
     * Change from game menu to player menu
     * 
     * @param event ActionEvent
     */
    
    @FXML
    private void newGame(ActionEvent event) {
        this.startMenuContainer.setVisible(false);
        this.startMenuContainer.setDisable(true);
        this.createPlayerMenuContainer.setVisible(true);
        this.createPlayerMenuContainer.setDisable(false);
    }
    
    /**
     * Set the character to be use
     * 
     * @param event ActionEvent
     */
    @FXML
    private void setChracter(ActionEvent event) {
        String name = this.playerName.getText();
        RadioButton selectedRadioButton = 
                (RadioButton) this.characterGroup.getSelectedToggle();
        String value = selectedRadioButton.getText();
        switch (value) {
            case "Witch":
                this.character = witch.createCharacter();
                break;

            case "Hunter":
                this.character = hunter.createCharacter();
                break;
            case "Researcher":
                this.character = researcher.createCharacter();
                break;
            default:
                throw new AssertionError();
        }
    }
    
    /**
     * Start the client and chage the view to the map
     * 
     * @param event ActionEvent
     */
    @FXML
    private void start(ActionEvent event) {
        String[] player = new String[2];
        player[0] = this.playerName.getText();
        player[1] = this.character.getName();
        Client client = Client.main(player);
        MapController.setClient(client, player[1]);

        App.setRoot("Map");
    }

    @FXML
    private void showAbout(ActionEvent event) {
        this.startMenuContainer.setVisible(false);
        this.startMenuContainer.setDisable(true);
        this.bg.setVisible(false);
        this.aboutContainer.setVisible(true);
        this.aboutContainer.setDisable(false);
    }
}