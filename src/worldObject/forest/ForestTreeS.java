package worldObject.forest;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class ForestTreeS extends BaseObject {
    Image i = new Image("/object/tree5.png");
    // x = 647, y = 500, x2 = 801, y2 = 671
    WritableImage image = new WritableImage(i.getPixelReader(),647,500,155,172);
    public ForestTreeS(double WorldX, double WorldY, double scaleX, double scaleY, double solidX, double solidY, double solidW, double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH); // 2, 2, 0.5, 1.5, 1, 0.25
        setName("Normal tree");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
