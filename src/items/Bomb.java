package items;

import interfaces.Usable;

public class Bomb extends BaseItem implements Usable {
    public Bomb() {
        super();
        setName("Bomb");
        setDescription("Deals AOE damage to all enemy.");
        setPrice(10);
    }
    @Override
    public void use() {

    }
}
