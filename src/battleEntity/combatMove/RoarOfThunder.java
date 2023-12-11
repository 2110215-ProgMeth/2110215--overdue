package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class RoarOfThunder extends AttackMove{
    public RoarOfThunder(BaseUnit owner){
        super(owner);
        setPercentDamage(2);
        setAllTarget(true);
    }

    public void performEffect() {
        if (!this.getOwner().isDestroyed()){
            for (BaseUnit enemy : GameLogic.getEnemiesUnits()) {
                if (!enemy.isDestroyed()) {
                    int Damage = (int) (getPercentDamage() * getOwner().getAttack());
                    enemy.setHp(getTarget().getHp() - Damage);
                }
            }
        }
    }
}
