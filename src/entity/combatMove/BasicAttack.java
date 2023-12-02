package entity.combatMove;

import entity.battleUnit.BaseUnit;

public class BasicAttack extends AttackMove{
    public BasicAttack(String name, int percentDamage, int mpConsume, BaseUnit owner){
        super(name, percentDamage, mpConsume, owner);
        this.isUsable = true;
    }

    @Override
    public void performEffect() {
        super.performEffect();
        // will add toString() to graphic later
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void setDescription() {
        this.description = "A normal attack. Deals small damage to enemy.";
    }

}
