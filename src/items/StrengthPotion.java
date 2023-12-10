package items;

import interfaces.Consumable;

public class StrengthPotion extends BaseItem implements Consumable {
    public StrengthPotion() {
        setName("Strength potion");
        setDescription("Increase party member's Atk by 20%");
        setPrice(4);
    }

    @Override
    public void use() {

    }
}
