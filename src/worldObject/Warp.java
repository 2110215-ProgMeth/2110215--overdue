package worldObject;

import javafx.scene.image.Image;

public class Warp extends BaseObject{

    public Warp(double WorldX,double WorldY){
        super(WorldX,WorldY);
        image = new Image("/tile/road00.png");
        setCollision(true);

    }
    @Override
    public boolean isDestroyed() {
        return false;
    }

}