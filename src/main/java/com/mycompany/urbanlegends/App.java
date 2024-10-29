package com.mycompany.urbanlegends;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
     * Load a fxml
     *
     * @param fxml the fxml to be load
     * @return the fxml already load
     * @throws IOException
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
        stage.show();
    }
}
