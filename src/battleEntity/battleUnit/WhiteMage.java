package battleEntity.battleUnit;

import battleEntity.combatMove.*;
import javafx.scene.canvas.GraphicsContext;

public class WhiteMage extends BaseUnit {

    // constructor
    public WhiteMage(String name, int baseHp, int baseMp, int baseAttack, int baseDefense) {
        super(name, baseHp, baseMp, baseAttack, baseDefense);
    }

    @Override
    public void setMoveSet() {
        AttackMove basicAttack = new BasicAttack("",this);
        SupportMove heal = new Heal(this);
        AttackMove holyLight = new HolyLight(this);
        SupportMove blessingOfGod = new BlessingOfGod(this);
        moveSet[0] = basicAttack;
        moveSet[1] = heal;
        moveSet[2] = holyLight;
        moveSet[3] = blessingOfGod;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
    public void update(){}
}
