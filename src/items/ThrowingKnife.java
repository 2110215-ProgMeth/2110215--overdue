package items;

import battleEntity.battleUnit.BaseUnit;
import interfaces.Usable;

public class ThrowingKnife extends BaseItem implements Usable {
    public ThrowingKnife() {
        super();
        setName("Throwing knife");
        setDescription("Deals damage to single a target.\nIgnore 15% defense"); // fixed damage
        setPrice(7);
    }
    @Override
    public void use(BaseUnit target) {

    }
}
