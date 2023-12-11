package worldObject.npc;

import battleEntity.battleUnit.BaseUnit;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import worldObject.BaseObject;

import java.awt.*;

public class Chimera extends BaseObject {
    Image i = new Image("/monster/VenomousChimera.png");
    WritableImage image = new WritableImage(i.getPixelReader(),0,0,128,128);
    public Chimera (double WorldX,double WorldY,double scaleX,double scaleY,double solidX,double solidY,double solidW,double solidH){
        super(WorldX,WorldY,scaleX,scaleY,solidX,solidY,solidW,solidH); //
        setName("Lady");
        setCollision(true);
        setImage(image);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
