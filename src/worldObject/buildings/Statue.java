package worldObject.buildings;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class Statue extends BaseObject {
    Image i = new Image("/object/statue.png");
    // x = 11 y = 239 x2 = 290  y2 = 433
    WritableImage image = new WritableImage(i.getPixelReader(),1,96,47,96);
    public Statue(double WorldX,double WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH);
        setName("Knight Statue");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
