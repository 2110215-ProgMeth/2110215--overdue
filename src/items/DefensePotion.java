package items;

import interfaces.Consumable;

public class DefensePotion extends BaseItem implements Consumable {
    public DefensePotion() {
        setName("Defense potion");
        setDescription("Increase party member's Def by 20%");
        setPrice(4);
    }

    @Override
    public void use() {

    }
}
