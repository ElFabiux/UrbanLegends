/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legends.factory;

import legends.Legend;
import legends.Cadejo;

/**
 *
 * @author jorge
 */
public class CadejoFactory implements LegendFactory {

    @Override
    public Legend createLegend() {
        return new Cadejo("Cadejin", 3,6);
    }
    
}
