package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class DrainLife extends AttackMove{
    public DrainLife(BaseUnit owner){
        super(owner);
        setName("Drain Life");
        setMpConsume(40);
        setPercentDamage(0.2);
        setAllTarget(true);
    }

    public void performEffect(BaseUnit target) {
        if (!this.getOwner().isDestroyed()){
            int drains = 0;
            for(BaseUnit ally : GameLogic.getAlliessUnits()){
                double drain = ally.getBaseHp() * getPercentDamage();
                ally.setHp(ally.getHp() - (int) (ally.getHp() * getPercentDamage()));
                drains += (int) drain;
            }
            owner.setHp(owner.getHp() + (int) (drains * 0.25));
        }
    }
}
