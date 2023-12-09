package worldObject.forest;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class NormalTree extends BaseObject {
    Image i = new Image("/object/tree2.png");
    // x = 401, y = 265, x2 = 790, y2 = 729
    WritableImage image = new WritableImage(i.getPixelReader(),401,265,389,464);
    public NormalTree (double WorldX,double WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH); // 4, 4, 1, 3.5, 2, 0.5
        setName("Normal tree");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
