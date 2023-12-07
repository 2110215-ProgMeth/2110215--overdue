package worldObject.tree;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class NormalTree extends BaseObject {
    Image i = new Image("/object/tree2.png");
    // x = 401, y = 265, x2 = 790, y2 = 729
    WritableImage image = new WritableImage(i.getPixelReader(),401,265,389,464);
    public NormalTree (int WorldX,int WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH);
        setName("Normal tree");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
