/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends.factory;

import legends.Legend;
import legends.Segua;

/**
 *
 * @author jorge
 */
public class SeguaFactory  implements LegendFactory {

    @Override
    public Legend createLegend() {
        return new Segua("Mula", 5,7);
    }
    
}

