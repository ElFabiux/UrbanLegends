package game;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Class Time that manages a timer that counts time in minutes and seconds. Use
 * JavaFX's Timeline class to update elapsed time every second. The timer
 * automatically resets after one minute.
 *
 * @author Melani
 * @author Joxan
 * @author Ismael
 * @author Jorge
 * @author Fabian
 */
public class Time {

    private boolean isDaytime;
    private boolean isRunning;

    private List<TimeObserver> observers;

    private Duration elapsedTime;
    private Timeline timeLine;

    /**
     * Time class constructor. Initialize the timer with an elapsed time of 0
     * and set a Timeline that updates the time every second.
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
     * Checks if it is currently daytime.
     *
     * @return true if it is daytime or false otherwise.
     */
    public boolean isDaytime() {
        return isDaytime;
    }

    /**
     * Returns the elapsed time formatted as a text string. The format is MM:SS
     * (minutes:seconds).
     *
     * @return The formatted elapsed time.
     */
    public String getFormattedTime() {
        long seconds = (long) elapsedTime.toSeconds();
        long minutes = seconds / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Adds a new observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers of changes in the state. This method
     * calls the update method on each observer, passing the current state
     * (isDaytime) as a parameter.
     */
    private void notifyObservers() {
        for (TimeObserver observer : observers) {
            observer.update(isDaytime);
        }
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to be removed.
     */
    public void removeObserver(TimeObserver observer) {
        observers.remove(observer);
    }

    /**
     * Resets the timer and elapsed time to zero.
     */
    public void resetTime() {
        timeLine.stop();
        elapsedTime = Duration.ZERO;
    }

    public void setTimeOfDay(boolean isDaytime) {
        this.isDaytime = isDaytime;
        notifyObservers();
    }

    /**
     * Start the timer if it is not running.
     */
    public void startTime() {
        if (!isRunning) {
            timeLine.play();
            isRunning = true;
        }
    }

    /**
     * Stops the timer if it is running.
     */
    public void stopTime() {
        if (isRunning) {
            timeLine.pause();
            isRunning = false;
        }
    }
}
