package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class CrossRipperSlasher extends AttackMove{ //penetrate50% decHp20% Atk175% mp60
    public CrossRipperSlasher(BaseUnit owner){
        super(owner);
        setName("CrossRipperSlasher");
        setPercentDamage(1.75);
        setMpConsume(60);
        setAllTarget(true);
    }

    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!this.getOwner().isDestroyed()) {
            //String text = this.toString();
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            for (BaseUnit enemy : GameLogic.getEnemiesUnits())
                if (!enemy.isDestroyed()) {
                    int Damage = (int) (getPercentDamage() * getOwner().getAttack() + enemy.getHp() * 0.2) - (int) (enemy.getDefense() * 0.5) ;
                    enemy.setHp(getTarget().getHp() - Damage);
                }
        }
    }
}
