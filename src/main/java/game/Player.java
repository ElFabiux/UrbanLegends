/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import playableCharacters.Character;

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
        if(this.positionY<=0) return;
        this.positionY --;
    }

    public void moveDown() {
        if(this.positionY > 10) return;
        this.positionY ++;
    }

    public void moveLeft() {
        if (this.positionX<=0)return;
        this.positionX --;
    }

    public void moveRight() {
        if(this.positionX>10)return;
        this.positionX ++;
    }

    public String getPosition() {
        return "(" + positionX + ", " + positionY + ")";
    }
   
   
    
}
