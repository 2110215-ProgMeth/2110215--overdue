package battleEntity.monster;

import battleEntity.battleUnit.BaseUnit;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class StoneGolem extends BaseUnit {
    public StoneGolem(String name,int baseHp,int baseMp, int baseAttack,int baseDefense){
        super(name,baseHp,baseMp,baseAttack,baseDefense);
        Image image = new Image("/monster/StoneGolem.png");
        images = new WritableImage[4];
        images[0] = new WritableImage(image.getPixelReader(),0,0,64,64);
        images[1] = new WritableImage(image.getPixelReader(),64,0,64,64);
        images[2] = new WritableImage(image.getPixelReader(),128,0,64,64);
        images[3] = new WritableImage(image.getPixelReader(),192,0,64,64);
    }
    public void setMoveSet(){

    }
    public void update(){
        updateAnimation();
    }
}
