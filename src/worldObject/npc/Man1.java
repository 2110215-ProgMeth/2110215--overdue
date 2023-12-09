package worldObject.npc;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class Man1 extends BaseObject {
    Image i = new Image("/npc/npcSheet.png");
    // x = 0, y = 48, x2 = 48, y2 = 95
    WritableImage image = new WritableImage(i.getPixelReader(),0,48,48,48);
    public Man1 (double WorldX,double WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH); //
        setName("Man1");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
