package battleEntity.battleUnit;

import javafx.scene.canvas.GraphicsContext;

public class WhiteMage extends BaseUnit {

    // constructor
    public WhiteMage(String name, int baseHp, int baseMp, int baseAttack, int baseDefense) {
        super(name, baseHp, baseMp, baseAttack, baseDefense);
    }

    @Override
    public void setMoveSet() {

    }

    @Override
    public void draw(GraphicsContext gc) {

    }
    public void update(){}
}