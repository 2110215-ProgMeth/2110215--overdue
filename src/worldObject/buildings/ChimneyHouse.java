package worldObject.buildings;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class ChimneyHouse extends BaseObject {
    Image i = new Image("/object/House(Cropped).png");
    // x = 203 y = 7 x2 = 370  y2 = 211
    WritableImage image = new WritableImage(i.getPixelReader(),203,7,167,205);
    public ChimneyHouse(double WorldX,double WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH);
        setName("Chimney House");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
