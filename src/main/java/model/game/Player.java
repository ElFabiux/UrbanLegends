/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.game;

import model.Character.Character;

/**
 *
 * @author joxan
 */
public class Player {
    
   private String name; 
   private int positionX;
   private int positionY;
   private String ip;

    public Player(String name, int positionX, int positionY, String ip, Character character) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.ip = ip;
        this.character = character;
    }
   public Character character;
   
   
   
    
}
