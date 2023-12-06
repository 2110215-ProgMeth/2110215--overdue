package BattleEntity.combatMove;

import BattleEntity.battleUnit.BaseUnit;

public class BackStab extends AttackMove{
    public BackStab(String name, int perCentDamage, int mpConsume, BaseUnit owner) {
        super(name, perCentDamage, mpConsume, owner);
    }

    @Override
    public void performEffect() {
        super.performEffect();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void setDescription() {
        this.description = "The user attacks target from behind. Deals ";
    }

}
