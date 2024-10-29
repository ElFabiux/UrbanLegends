/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import javafx.scene.image.ImageView;

/**
 * A class to manage all the near players to the current player
 *
 * @author Ismael Marchena
 * @author Jorge Rojas
 * @author Fabian Arguedas
 * @author Joxan Portilla
 * @author Melani Barrantes
 */
public class ClientPlayer {

    int col;
    int row;
    ImageView icon;

    /**
     * Creates a new client player object 
     * 
     * @param col current column
     * @param row current row
     * @param icon the Icon
     */
    public ClientPlayer(int col, int row, ImageView icon) {
        this.col = col;
        this.row = row;
        this.icon = icon;
    }

    /**
     * Get the current column
     *
     * @return the current column
     */
    public int getCol() {
        return col;
    }

    /**
     * Set the column to a new value
     *
     * @param col new value for col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Get the current row
     *
     * @return the current row
     */
    public int getRow() {
        return row;
    }

    /**
     * Set the row to a new value
     *
     * @param row new value for row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Get the icon of the player
     *
     * @return the icon of the player
     */
    public ImageView getIcon() {
        return icon;
    }

    /**
     * Change the player Icon
     *
     * @param icon new icon
     */
    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

}
