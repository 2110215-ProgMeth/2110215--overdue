package worldObject.tree;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class LightTree extends BaseObject {
    Image i = new Image("/object/tree3.png");
    // x = 411, y = 289, x2 = 800, y2 = 752
    WritableImage image = new WritableImage(i.getPixelReader(),411,289,389,463);
    public LightTree (int WorldX,int WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH);
        setName("Light tree");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
