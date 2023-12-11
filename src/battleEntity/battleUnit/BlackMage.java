package battleEntity.battleUnit;

import battleEntity.combatMove.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class BlackMage extends BaseUnit {

    public BlackMage(String name, int baseHp, int baseMp, int baseAttack, int baseDefense) {
        super(name, baseHp, baseMp, baseAttack, baseDefense);
        images = new WritableImage[1];
        images[0] = new WritableImage(RenderableHolder.unitImage.getPixelReader(), 0, 1288, 96, 96);
        setMoveSet();
    }

    @Override
    public void setMoveSet() {
        AttackMove basicAttack = new BasicAttack("Dark void",this);
        AttackMove thundara = new Thundara(this);
        AttackMove firaga = new Firaga(this);
        AttackMove darkHold = new DarkHold(this);
        moveSet[0] = basicAttack;
        moveSet[1] = thundara;
        moveSet[2] = firaga;
        moveSet[3] = darkHold;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
    public void update(){}
}
