package worldObject.forest;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class DeadTreeB extends BaseObject {
    Image i = new Image("/object/tree5.png");
    // x = 1024, y = 111, x2 = 1247, y2 = 366
    WritableImage image = new WritableImage(i.getPixelReader(),1024,111,223,255);
    public DeadTreeB(double WorldX, double WorldY, double scaleX, double scaleY, double solidX, double solidY, double solidW, double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH); // 4, 4, 1, 3.5, 1.5, 0.5
        setName("Normal tree");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
