package tile;

import javafx.scene.image.Image;

public class Tile{
    public Image image;
    public boolean collision = false;

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public void setImage(Image image){
        this.image = image;
    }
}
