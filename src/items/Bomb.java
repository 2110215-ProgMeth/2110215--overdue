package items;

import battleEntity.battleUnit.BaseUnit;
import interfaces.Usable;

public class Bomb extends BaseItem implements Usable {
    public Bomb() {
        super();
        setName("Bomb");
        setDescription("Deals AOE damage to all enemy."); // fixed damage
        setPrice(10);
    }

    public void use(BaseUnit target) {
    //
    }
}
