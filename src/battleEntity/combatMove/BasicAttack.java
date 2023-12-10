package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class BasicAttack extends AttackMove{
    public BasicAttack(BaseUnit owner){
        super(owner);
        this.isUsable = true;
        setDescription("A normal attack. Deals small damage to enemy.");
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

}
