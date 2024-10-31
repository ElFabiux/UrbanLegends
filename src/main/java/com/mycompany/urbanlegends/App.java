package com.mycompany.urbanlegends;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The princial App class that contais all the necesary things to start a
 * program
 *
 * @author Ismael Marchena
 * @author Jorge Rojas
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Loads an FXML file and returns it as a Parent object.
     *
     * This method uses FXMLLoader to load a specified FXML file from the
     * application's resources. The .fxml extension is automatically appended to
     * the filename provided.
     *
     * @param fxml the fxml to be load
     * @return the fxml already load, object representing the root of the FXML
     * hierarchy.
     * @throws IOException if there is an error during loading, such as the file
     * not being found.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml
                + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Launch the program
     *
     * @param args args that came from the console
     */
    public static void main(String[] args) {
        launch();

    }

    /**
     * Change the of the window to another FXML
     *
     * @param fxml name of the fxml to be render
     */
    public static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
            Stage stage = (Stage) scene.getWindow();
            stage.sizeToScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Start the program
     *
     * @param stage the stage of the view
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LogIn"));
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(400); 
        stage.setMaxWidth(600);
        stage.setMaxHeight(400);
        stage.setResizable(false); 
         stage.setTitle("URBAN LEGENDS, FELIZ HALLOWEN");
        stage.getIcons().add(new Image(App.class.getResourceAsStream(
                "/Legends/8_2.png")));

        stage.show();
    }
}
