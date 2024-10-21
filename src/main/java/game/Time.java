package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Clase Time que gestiona un temporizador que cuenta tiempo en minutos y 
 * segundos.
 * Utiliza la clase Timeline de JavaFX para actualizar el tiempo transcurrido 
 * cada segundo.
 * El temporizador se reinicia automáticamente después de un minuto.
 * 
 * @author Melani
 * @author Joxan 
 * @author Ismael
 * @author Jorge
 * @author Fabian
 */
public class Time {

    private Duration elapsedTime;
    private Timeline timeLine;
    private boolean isRunning;
    
    /**
     * Constructor de la clase Time.
     * Inicializa el temporizador con un tiempo transcurrido de 0 y configura
     * un Timeline que actualiza el tiempo cada segundo.
     */
    public Time() {
        elapsedTime = Duration.ZERO;
        timeLine = new Timeline(new KeyFrame(Duration.millis(1000), 
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                elapsedTime = elapsedTime.add(Duration.seconds(1));
                if (elapsedTime.toMinutes() >= 1) {
                    elapsedTime = Duration.ZERO;
                }
            }
        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        isRunning = false;
    }
    
    /**
     * Inicia el temporizador si no está corriendo.
     */
    public void startTime() {
        if (!isRunning) {
            timeLine.play();
            isRunning = true;
        }
    }
    
    /**
     * Detiene el temporizador si está corriendo.
     */
    public void stopTime() {
        if (isRunning) {
            timeLine.pause();
            isRunning = false;
        }
    }
    
    /**
     * Reinicia el temporizador y el tiempo transcurrido a cero.
     */
    public void resetTime() {
        timeLine.stop();
        elapsedTime = Duration.ZERO;
    }
    
    /**
     * Devuelve el tiempo transcurrido formateado como una cadena de texto.
     * El formato es MM:SS (minutos:segundos).
     * 
     * @return El tiempo transcurrido formateado.
     */
    public String getFormattedTime() {
        long seconds = (long) elapsedTime.toSeconds();
        long minutes = seconds / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}

/**
 * ESTO SOLO LO COLOCO PARA QUE SEPAN COMO USARLO.
 * public class PRUEBAController implements Initializable {

        @FXML
        private Label lbl_cantTiempo;
        private Time time;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            time = new Time();

            Timeline updateTime = new Timeline(new KeyFrame(Duration
                    .seconds(1), e -> {
                if (time != null) {
                    lbl_cantTiempo.setText(time.getFormattedTime());
                }
            }));
            time.startTime();
            updateTime.setCycleCount(Animation.INDEFINITE);
            updateTime.play();
        } 
    }
 */