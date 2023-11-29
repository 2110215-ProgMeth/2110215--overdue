package entity.battleUnit;

import javafx.scene.canvas.GraphicsContext;

public class Assasssin extends BaseUnit {

    public Assasssin(String name, int baseHp, int baseMp, int baseAttack, int baseDefense) {
        super(name, baseHp, baseMp, baseAttack, baseDefense);
    }


    @Override
    public void setMoveSet() {

    }

    @Override
    public void draw(GraphicsContext gc) {
        //
    }

}
