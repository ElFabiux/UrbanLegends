package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
    private boolean isDaytime;
    private List<TimeObserver> observers;
    
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
                    isDaytime = !isDaytime;
                    notifyObservers();
                }
            }
        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        isRunning = false;
        
        this.isDaytime = true;
        observers = new ArrayList<>();
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
    
    //Para Observer................................
    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(TimeObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (TimeObserver observer : observers) {
            observer.update(isDaytime);
        }
    }

    public void setTimeOfDay(boolean isDaytime) {
        this.isDaytime = isDaytime;
        notifyObservers();
    }

    public boolean isDaytime() {
        return isDaytime;
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