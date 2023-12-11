package battleEntity.monster;

import battleEntity.battleUnit.BaseUnit;
import battleEntity.combatMove.*;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;

public class Chimera extends BaseUnit {
    public Chimera(String name,int baseHp,int baseMp, int baseAttack,int baseDefense){
        super(name,baseHp,baseMp,baseAttack,baseDefense);
        Image image = new Image("/monster/VenomousChimera.png");
        images = new WritableImage[8];
        images[0] = new WritableImage(image.getPixelReader(),0,0,128,128);
        images[1] = new WritableImage(image.getPixelReader(),128,0,128,128);
        images[2] = new WritableImage(image.getPixelReader(),256,0,128,128);
        images[3] = new WritableImage(image.getPixelReader(),384,0,128,128);
        images[4] = new WritableImage(image.getPixelReader(),512,0,128,128);
        images[5] = new WritableImage(image.getPixelReader(),640,0,128,128);
        images[6] = new WritableImage(image.getPixelReader(),768,0,128,128);
        images[7] = new WritableImage(image.getPixelReader(),896,0,128,128);
        setMoveSet();
        Loop.add(0) ; Loop.add(1) ; Loop.add(0); Loop.add(0); Loop.add(2);
    }
    public void setMoveSet(){
        moveSet = new BaseMove[3];
        AttackMove basicAttack = new BasicAttack("Demon Claw",this);
        AttackMove demonBite = new DemonBite(this);
        AttackMove roarOfThunder = new RoarOfThunder(this);
        moveSet[0] = basicAttack;
        moveSet[1] = demonBite;
        moveSet[2] = roarOfThunder;
    }
}
