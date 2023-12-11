package battleEntity.battleUnit;

import battleEntity.combatMove.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class Assasssin extends BaseUnit {

    public Assasssin(String name, int baseHp, int baseMp, int baseAttack, int baseDefense) {
        super(name, baseHp, baseMp, baseAttack, baseDefense);
        images = new WritableImage[1];
        images[0] = new WritableImage(RenderableHolder.unitImage.getPixelReader(), 0, 832, 96, 96);
        setMoveSet();
    }


    @Override
    public void setMoveSet() {
        AttackMove basicAttack = new BasicAttack("",this);
        AttackMove backStab = new BackStab(this);
        AttackMove enchantDeadlyPoison = new EnchantDeadlyPoison(this);
        AttackMove CrossRipperSlasher = new CrossRipperSlasher(this);
        moveSet[0] = basicAttack ;
        moveSet[1] = backStab ;
        moveSet[2] = enchantDeadlyPoison ;
        moveSet[3] = CrossRipperSlasher ;
    }

    @Override
    public void draw(GraphicsContext gc) {
        //
    }
    public void update(){}
}
