package items;

import battleEntity.battleUnit.BaseUnit;

public class BaseItem {
    protected String name;
    protected String description;
    protected int price;
    protected BaseUnit target;
    protected boolean allTarget = false;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setTarget(BaseUnit target) {
        this.target = target;
    }
    public void setAllTarget(boolean allTarget) {
        this.allTarget = allTarget;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

}

