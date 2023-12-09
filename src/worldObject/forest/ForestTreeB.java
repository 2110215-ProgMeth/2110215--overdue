package worldObject.forest;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class ForestTreeB extends BaseObject {
    Image i = new Image("/object/tree5.png");
    // x = 75, y = 420, x2 = 322, y2 = 731
    WritableImage image = new WritableImage(i.getPixelReader(),75,420,248,312);
    public ForestTreeB(double WorldX, double WorldY, double scaleX, double scaleY, double solidX, double solidY, double solidW, double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH); // 4, 4, 1.25, 3, 1.75, 0.5
        setName("Normal tree");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
