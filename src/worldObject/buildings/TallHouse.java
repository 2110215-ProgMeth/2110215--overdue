package worldObject.buildings;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class TallHouse extends BaseObject {
    Image i = new Image("/object/House(Cropped).png");
    // x = 349 y = 243 x2 = 554  y2 = 577
    WritableImage image = new WritableImage(i.getPixelReader(),349,243,205,335);
    public TallHouse(int WorldX,int WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH);
        setName("Tall House");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
