package worldObject.npc;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

public class Merchant extends BaseObject {
    Image i = new Image("/npc/npcSheet.png");
    // x = 3, y = 0, x2 = 44, y2 = 47
    WritableImage image = new WritableImage(i.getPixelReader(),0,0,48,48);
    public Merchant (double WorldX,double WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH); //
        setName("Merchant");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
