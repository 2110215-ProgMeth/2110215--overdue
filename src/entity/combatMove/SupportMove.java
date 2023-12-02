package entity.combatMove;

import entity.battleUnit.BaseUnit;

public class SupportMove extends BaseMove{
    public SupportMove(String name, int mpConsume, BaseUnit owner){
        super(name,mpConsume,owner);
    }

    @Override
    public void performEffect() {
    }

    @Override
    public String toString() {
        return getOwner() + " used " + getName() + " on " + getTarget();
    }

    @Override
    public void setDescription() {

    }
}
