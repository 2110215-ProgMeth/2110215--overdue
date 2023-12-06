package worldObject;

import javafx.scene.image.Image;

public class Key extends BaseObject{
    Image image = new Image("/Object/key.png");
    public Key(int worldX, int worldY){
        super(worldX,worldY);
        setName("Key");
        setCollision(true);
        setImage(image);
    }
}
