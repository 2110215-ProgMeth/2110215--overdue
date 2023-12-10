package items;

import interfaces.Consumable;

public class ManaPotion extends BaseItem implements Consumable {

    public ManaPotion() {
        setName("Mana potion");
        setDescription("Restore party member's MP by 35%");
        setPrice(8);
    }

    @Override
    public void use() {

    }
}
