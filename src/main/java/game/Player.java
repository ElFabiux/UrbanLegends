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
   

    public Player(String name, String ip, Character character) {
        this.name = name;
        this.ip = ip;
        this.character = character;
    }
   public Character character;
   
   
   
    
}
