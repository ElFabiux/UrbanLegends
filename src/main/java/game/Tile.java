package game;

import controllers.MapController;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile implements TimeObserver {
    private String tileType;
    private Image image;
    private ImageView imageView;

    public Tile(String tileType, String imagePath) {
        this.tileType = tileType;
        this.image = new Image(getClass().getResource(imagePath).toExternalForm());
        this.imageView = new ImageView(this.image);
        this.imageView.setFitHeight(56);
        this.imageView.setFitWidth(56);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public String getTileType() {
        return tileType;
    }

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
