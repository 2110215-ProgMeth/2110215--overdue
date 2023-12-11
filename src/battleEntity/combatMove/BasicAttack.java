package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class BasicAttack extends AttackMove{
    public BasicAttack(String name,BaseUnit owner){
        super(owner);
        setName("BasicAttack");
        setMpConsume(0);
        setDescription("A normal attack. Deals small damage to enemy.");
    }

    @Override
    public void performEffect(BaseUnit target) {
        getOwner().setMp((int) (getOwner().getBaseMp() * 0.15));
        setTarget(target);
        super.performEffect(target);
        // will add toString() to graphic later
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
