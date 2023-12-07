package worldObject.buildings;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class House extends BaseObject {
    Image i = new Image("/object/House(Cropped).png");
    // x = 16 y = 10 x2=178 y2=193
    WritableImage image = new WritableImage(i.getPixelReader(),16,10,162,183);
    public House(int WorldX,int WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
            super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH);
            setName("house");
            setCollision(true);
            setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
