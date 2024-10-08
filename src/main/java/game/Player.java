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
   private Character character;

    public Player(String name, String ip, int positionX, int positionY, 
            Character character) {
        this.name = name;
        this.ip = ip;
        this.positionX = positionX;
        this.positionY = positionY;
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
   

    
   
    public void moveUp() {
        this.positionY --;
    }

    public void moveDown() {
        this.positionY ++;
    }

    public void moveLeft() {
        this.positionX --;
    }

    public void moveRight() {
        this.positionX ++;
    }

    public String getPosition() {
        return "(" + positionX + ", " + positionY + ")";
    }
   
   
    
}
