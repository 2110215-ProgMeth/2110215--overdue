package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class SoulEater extends AttackMove{
    public SoulEater(BaseUnit owner){
        super(owner);
        setPercentDamage(0.5);
    }

    @Override
    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!getOwner().isDestroyed() && !getTarget().isDestroyed()){
            getTarget().setHp(getTarget().getHp()/2);
        }
    }
}
