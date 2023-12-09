package worldObject.buildings;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class Guild extends BaseObject {
    Image i = new Image("/object/House(Cropped).png");
    // x = 582 y = 3 x2 = 913  y2 = 334
    WritableImage image = new WritableImage(i.getPixelReader(),582,3,331,332);
    public Guild(double WorldX,double WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH);
        setName("Guild");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
