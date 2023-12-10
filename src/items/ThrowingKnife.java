package items;

import interfaces.Usable;

public class ThrowingKnife extends BaseItem implements Usable {
    public ThrowingKnife() {
        super();
        setName("Throwing knife");
        setDescription("Deals damage to single a target.\nIgnore 15% defense");
        setPrice(7);
    }
    @Override
    public void use() {

    }
}
