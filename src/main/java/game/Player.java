/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import characters.Character;

/**
 *
 * @author joxan
 */
public class Player {
    
   private String name; 
   private String ip;
   private int positionX;
   private int positionY;
   public Character character;

    public Player(String name, String ip, int positionX, int positionY, Character character) {
        this.name = name;
        this.ip = ip;
        this.positionX = positionX;
        this.positionY = positionY;
        this.character = character;
    }
   

    
   
    public void moveUp() {
        this.positionY += 1;
    }

    public void moveDown() {
        this.positionY -= 1;
    }

    public void moveLeft() {
        this.positionX -= 1;
    }

    public void moveRight() {
        this.positionX += 1;
    }

    public String getPosition() {
        return "(" + positionX + ", " + positionY + ")";
    }
   
   
    
}
