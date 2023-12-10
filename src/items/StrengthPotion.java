package items;

import battleEntity.battleUnit.BaseUnit;
import interfaces.Consumable;
import worldObject.Player;

public class StrengthPotion extends BaseItem implements Consumable {
    public StrengthPotion() {
        setName("Strength potion");
        setDescription("Increase party member's Atk by 20%");
        setPrice(4);
    }

    @Override
    public void use(BaseUnit target) {
        if (Player.getPlayerInventory().get(this.getName()) > 0 && !target.isDestroyed()) {
            target.setAttack((int) (1.20 * target.getHp()));
        }
    }
}
