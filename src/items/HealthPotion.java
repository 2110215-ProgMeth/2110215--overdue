package items;

import interfaces.Consumable;

public class HealthPotion extends BaseItem implements Consumable {
    public HealthPotion() {
        setName("Health potion");
        setDescription("Restore party member's HP by 35%");
        setPrice(5);
    }

    @Override
    public void use() {

    }
}
