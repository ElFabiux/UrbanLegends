/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends.factory;

import legends.Legend;
import legends.Llorona;

/**
 *
 * @author jorge
 */
public class LloronaFactory  implements LegendFactory {

    @Override
    public Legend createLegend() {
        return new Llorona("maricona", "Nose");
    }
    
}
