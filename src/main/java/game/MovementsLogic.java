/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import controllers.MapController;

/**
 *
 * @author jorge
 */
public class MovementsLogic {

    private final char[] blockedGrids = {'R', 'T', 'H', 'O', 'C', 'S', 'W', 'P', 'L'};

    public MovementsLogic() {
    }

    public boolean checkMovements(String[][] map, Player player, String direction) {
        int oldX = player.getPositionX();
        int oldY = player.getPositionY();
        int posX = oldX;
        int posY = oldY;
        System.out.println("dire: " + direction);
        if ("up".equals(direction)) {
            posY--;
        }
        if ("down".equals(direction)) {
            posY++;
        }
        if ("right".equals(direction)) {
            posX++;
        }
        if ("left".equals(direction)) {
            posX--;
        }

        System.out.println("width:" + map.length);
        System.out.println("height:" + map[0].length);
        System.out.println("stoy en:" + map[oldY][oldX]);
        //eSTE Dentro de los limites del mapa
        if (posX >= map.length || posX < 0) {
            return false;
        }
        if (posY >= map[0].length || posY < 0) {
            return false;
        }

        System.out.println("newPos: " + posX + " : " + posY);
        System.out.println("newPos: " + map[oldY][posX]);

        if (isBlocked(map[posY][oldX], 0)
                || isBlocked(map[oldY][posX], 0)) {
            return false;
        }

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return true;
    }

    //veificacion con cosas del mapa que no puede pisar
    private boolean isBlocked(String grid, int index) {
        if (index >= blockedGrids.length) {
            return false;
        }

        if (grid.charAt(0) == blockedGrids[index]) {
            return true;
        }

        return isBlocked(grid, index + 1);
    }
}
