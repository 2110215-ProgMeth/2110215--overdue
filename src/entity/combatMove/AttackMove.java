package entity.combatMove;

import entity.battleUnit.BaseUnit;

public class AttackMove extends BaseMove{
    public AttackMove(String name, int perCentDamage, int mpConsume, BaseUnit owner){
        super(name,perCentDamage,mpConsume,owner);
    }

    @Override
    public void performEffect() {
    }

    @Override
    public String toString() {
        return getOwner()+" used "+getName()+" on "+getTarget();
    }

}
