package battleEntity.monster;

import battleEntity.battleUnit.BaseUnit;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class IronGolem extends BaseUnit {
    public IronGolem(String name,int baseHp,int baseMp, int baseAttack,int baseDefense){
        super(name,baseHp,baseMp,baseAttack,baseDefense);
        Image image = new Image("/monster/SteelGolem.png");
        images = new WritableImage[8];
        images[0] = new WritableImage(image.getPixelReader(),0,0,128,128);
        images[1] = new WritableImage(image.getPixelReader(),128,0,128,128);
        images[2] = new WritableImage(image.getPixelReader(),256,0,128,128);
        images[3] = new WritableImage(image.getPixelReader(),384,0,128,128);
        images[4] = new WritableImage(image.getPixelReader(),512,0,128,128);
        images[5] = new WritableImage(image.getPixelReader(),640,0,128,128);
        images[6] = new WritableImage(image.getPixelReader(),768,0,128,128);
        images[7] = new WritableImage(image.getPixelReader(),896,0,128,128);
    }

    public void setMoveSet(){

    }
    public void update(){
        updateAnimation();
    }
}
