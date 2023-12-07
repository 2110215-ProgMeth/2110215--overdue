package worldObject;

import javafx.scene.image.Image;

public class Key extends BaseObject{
    Image image = new Image("/object/key.png");
    public Key(int WorldX, int WorldY){
        super(WorldX,WorldY);
        setName("Key");
        setCollision(true);
        setImage(image);
        collision = true;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
