package com.mycompany.urbanlegends;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    private MediaPlayer mediaPlayer;
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
     * Plays a specified sound file in a continuous loop.
     * <p>
     * This method attempts to load a sound file from the resources directory
     * and plays it in an infinite loop. If the sound file cannot be found, an
     * error message is printed to the console.</p>
     *
     * @param soundFile the name of the sound file to play. It should be located
     * in the project's resources directory.
     */
    private void playSoundLoop(String soundFile) {
        URL resource = getClass().getResource("/" + soundFile);
        if (resource != null) {
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } else {
            System.out.println("No se pudo encontrar el archivo de sonido: " + soundFile);
        }
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
        stage.setResizable(false);
        stage.setTitle("URBAN LEGENDS, FELIZ HALLOWEN");
        stage.getIcons().add(new Image(App.class.getResourceAsStream(
                "/Legends/8_2.png")));

        stage.show();
        playSoundLoop("audio/horror.mp3");
    }
}
