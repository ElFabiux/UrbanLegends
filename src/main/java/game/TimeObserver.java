/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package game;

/**
 * Interface for observers that need to be notified of changes in time state.
 * Implementing classes will define actions to take when the time changes 
 * (example, when it becomes daytime or nighttime).
 * 
 * @author Fabiux
 * @author Ismael
 * @author Jorge
 * @author Joxan
 * @author Melani
 */
public interface TimeObserver {
    /**
     * Updates the observer with the current time state.
     *
     * @param isDaytime true if it is currently daytime; false if it is 
     * nighttime.
     */
    void update(boolean isDaytime);
}