/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.actions;

import game.Game;
import game.Player;

/**
 *
 * @author joxan
 */
public interface Command {
    String execute(Player player, Game game);
}

