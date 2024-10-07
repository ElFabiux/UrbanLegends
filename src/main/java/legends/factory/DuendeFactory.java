/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends.factory;

import legends.Duende;
import legends.Legend;

/**
 *
 * @author jorge
 */
public class DuendeFactory  implements LegendFactory {

    @Override
    public Legend createLegend() {
        return new Duende("Duendin", 6,8);
    }
    
}
