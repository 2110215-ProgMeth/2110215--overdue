package items;

import battleEntity.battleUnit.BaseUnit;
import interfaces.Consumable;
import worldObject.Player;

public class DefensePotion extends BaseItem implements Consumable {
    public DefensePotion() {
        setName("Defense potion");
        setDescription("Increase party member's Def by 20%");
        setPrice(4);
    }

    @Override
    public void use(BaseUnit target) {
        if (Player.getPlayerInventory().get(this.getName()) > 0 && !target.isDestroyed()) {
            target.setDefense((int) (1.20 * target.getHp()));
        }
    }
}
