package items;

import battleEntity.battleUnit.BaseUnit;
import interfaces.Consumable;
import worldObject.Player;

public class HealthPotion extends BaseItem implements Consumable {
    public HealthPotion() {
        setName("Health potion");
        setDescription("Restore party member's HP by 35%");
        setPrice(5);
    }

    @Override
    public void use(BaseUnit target) {
        if (Player.getPlayerInventory().get(this.getName()) > 0 && !target.isDestroyed()) {
            target.setHp((int) (1.35 * target.getHp()));
        }
    }
}
