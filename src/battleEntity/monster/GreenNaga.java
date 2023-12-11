package battleEntity.monster;

import battleEntity.battleUnit.BaseUnit;
import battleEntity.combatMove.AttackMove;
import battleEntity.combatMove.BaseMove;
import battleEntity.combatMove.BasicAttack;
import battleEntity.combatMove.Poison;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class GreenNaga extends BaseUnit {
    public GreenNaga(String name,int baseHp,int baseMp, int baseAttack,int baseDefense){
        super(name,baseHp,baseMp,baseAttack,baseDefense);
        Image image = new Image("/monster/GreenNaga.png");
        images = new WritableImage[8];
        images[0] = new WritableImage(image.getPixelReader(),0,0,64,64);
        images[1] = new WritableImage(image.getPixelReader(),64,0,64,64);
        images[2] = new WritableImage(image.getPixelReader(),128,0,64,64);
        images[3] = new WritableImage(image.getPixelReader(),192,0,64,64);
        setMoveSet();
        Loop.add(0) ; Loop.add(1) ; Loop.add(0);
    }
    public void setMoveSet(){
        moveSet = new BaseMove[2];
        AttackMove basicAttack = new BasicAttack("Bite",this);
        AttackMove poison = new Poison(this);
        moveSet[0] = basicAttack;
        moveSet[1] = poison;
    }
    public void update(){
        updateAnimation();
    }

}
