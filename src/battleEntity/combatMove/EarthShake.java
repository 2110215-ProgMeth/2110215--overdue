package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class EarthShake extends AttackMove{
    public EarthShake(BaseUnit owner){
        super(owner);
        setName("EarthShake");
        setPercentDamage(1.5);
        setAllTarget(true);
    }

    public void performEffect(BaseUnit target) {
        if (!getOwner().isDestroyed()){
            for (BaseUnit ally : GameLogic.getAlliessUnits())
                if (!ally.isDestroyed()) {
                    int Damage = (int) (getPercentDamage() * getOwner().getAttack());
                    ally.setHp(getTarget().getHp() - Damage);
                }
        }
    }
}