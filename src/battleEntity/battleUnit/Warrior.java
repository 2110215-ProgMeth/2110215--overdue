package battleEntity.battleUnit;

import battleEntity.combatMove.*;
import javafx.scene.canvas.GraphicsContext;

public class Warrior extends BaseUnit {
    private boolean taunt;

    public Warrior(String name, int baseHp, int baseMp, int baseAttack, int baseDefense) {
        super(name, baseHp, baseMp, baseAttack, baseDefense);
    }

    @Override
    public void setMoveSet() {
        AttackMove basicAttack = new BasicAttack("Slash",this);
        AttackMove risingDragonStrike = new RisingDragonStrike(this);
        AttackMove judgement = new Judgement(this);
        AttackMove taunt = new Taunt(this);
        moveSet[0] = basicAttack;
        moveSet[1] = judgement;
        moveSet[2] = taunt;
        moveSet[3] = risingDragonStrike;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
    public void update(){}

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isTaunt() {
        return taunt;
    }
}
