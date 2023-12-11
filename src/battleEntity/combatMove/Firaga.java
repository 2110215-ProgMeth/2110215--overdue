package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class Firaga extends AttackMove{
    public Firaga(BaseUnit owner){
        super(owner);
        setPercentDamage(2);
        setMpConsume(30);
        setAllTarget(true);
    }

    public void performEffect(BaseUnit target) {
        if (!getOwner().isDestroyed()) {
            for (BaseUnit enemy : GameLogic.getEnemiesUnits()) {
                if (!enemy.isDestroyed()) {
                    Buff burn = new DOT("Burn",enemy,2,0.25);
                    enemy.getBuffers().add(burn);
                    int Damage = (int) (getPercentDamage() * getOwner().getAttack() - enemy.getDefense());
                    enemy.setHp(getTarget().getHp() - Damage);
                }
            }
        }
    }
}
