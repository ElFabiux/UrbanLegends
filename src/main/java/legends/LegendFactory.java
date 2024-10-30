/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package legends;

/**
 * Interface for creating instances of Legend.
 * This interface defines a factory method for legend creation.
 * 
 * @author jorge
 * @author fabian
 * @author joxan
 * @author melani
 * @author ismael
 */
public interface LegendFactory {
    /**
     * Factory method to create a new instance of a Legend.
     * 
     * @return a new Legend object.
     */
    Legend createLegend();
}