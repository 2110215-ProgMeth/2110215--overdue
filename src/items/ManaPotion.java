package items;

import battleEntity.battleUnit.BaseUnit;
import interfaces.Consumable;
import worldObject.Player;

public class ManaPotion extends BaseItem implements Consumable {

    public ManaPotion() {
        setName("Mana potion");
        setDescription("Restore party member's MP by 35%");
        setPrice(8);
    }

    @Override
    public void use(BaseUnit target) {
        if (Player.getPlayerInventory().get(this.getName()) > 0 && !target.isDestroyed()) {
            target.setMp((int) (1.35 * target.getHp()));
        }
    }
}
