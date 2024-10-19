/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author jorge
 */
public class MovementsLogic {

    private final char house = 'H';
    private final char river = 'R';
    private final char tree = 'T';
    private final char grass = 'G';

    public MovementsLogic() {
    }

    public void checkMovements(char[][] map, Player player, int oldX, int oldY,
            char character) {

        char playerMovement = map[player.getPositionX()][player.getPositionY()];

        //Esto donde si puede pisar, que no seria necesaria cuando se verifique lo lugares en que no se puede
        if (playerMovement == grass) {
            map[oldY][oldX] = grass;
            map[player.getPositionY()][player.getPositionX()] = character;
        }

        //Esto es que choque con cualquier cosa que no se deba
        if (playerMovement == river || playerMovement == house
                || playerMovement == tree) {
            map[oldY][oldX] = character;
        }

    }

}
