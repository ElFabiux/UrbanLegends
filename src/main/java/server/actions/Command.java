/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.actions;

import game.Game;
import characters.Character;

/**
 *
 * @author joxan
 */
public interface Command {
    String execute(Character character, Game game);
}

