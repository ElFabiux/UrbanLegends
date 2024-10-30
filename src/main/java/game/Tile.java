package game;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a tile in the game, which can change its appearance based on the 
 * time of day.
 * Implements the TimeObserver interface to respond to changes in the time 
 * state.
 * 
 * @author Melani
 * @author Joxan
 * @author Jorge
 * @author Ismael
 * @author Fabian
 */
public class Tile implements TimeObserver {
    private Image image;
    private ImageView imageView;
    
    private String tileType;

    /**
     * Constructs a Tile with the specified type and image path.
     *
     * @param tileType The type of the tile (e.g., grass, water).
     * @param imagePath The path to the image resource for this tile.
     */
    public Tile(String tileType, String imagePath) {
        this.tileType = tileType;
        this.image = new Image(getClass().getResource(imagePath).toExternalForm());
        this.imageView = new ImageView(this.image);
        this.imageView.setFitHeight(56);
        this.imageView.setFitWidth(56);
    }

    /**
     * Gets the type of this tile.
     *
     * @return The tile type.
     */
    public String getTileType() {
        return tileType;
    }
    
    /**
     * Gets the ImageView associated with this tile.
     *
     * @return The ImageView displaying the tile image.
     */
    public ImageView getImageView() {
        return imageView;
    }
    
    /**
     * Updates the tile's appearance based on whether it is daytime or 
     * nighttime.
     *
     * @param isDaytime True if it is currently daytime; false if it is 
     * nighttime.
     */
    @Override
    public void update(boolean isDaytime) {
        if (isDaytime) {
            imageView.setEffect(null);
        } else {
            ColorAdjust grayscale = new ColorAdjust();
            grayscale.setSaturation(-1);

            imageView.setEffect(grayscale);
        }
    }
}