package battleEntity.battleUnit;

import battleEntity.combatMove.AttackMove;
import battleEntity.combatMove.BasicAttack;
import battleEntity.combatMove.DarkHold;
import javafx.scene.canvas.GraphicsContext;

public class BlackMage extends BaseUnit {

    public BlackMage(String name, int baseHp, int baseMp, int baseAttack, int baseDefense) {
        super(name, baseHp, baseMp, baseAttack, baseDefense);
    }

    @Override
    public void setMoveSet() {
        AttackMove basicAttack = new BasicAttack("Dark void",this);
        AttackMove darkHold = new DarkHold(this);
        moveSet[0] = basicAttack;
        moveSet[3] = darkHold;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
    public void update(){}
}
