package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class Thundara extends AttackMove{
    public Thundara(BaseUnit owner){
        super(owner);
        setPercentDamage(1.5);
        setMpConsume(25);
        setAllTarget(true);
    }

    public void performEffect() {
        if (!getOwner().isDestroyed()) {
            for (BaseUnit enemy : GameLogic.getEnemiesUnits()) {
                if (!enemy.isDestroyed()) {
                    Buff shork = new Dot("Shock",enemy,1,0.1);
                    int Damage = (int) (getPercentDamage() * getOwner().getAttack() - enemy.getDefense());
                    enemy.setHp(getTarget().getHp() - Damage);
                }
            }
        }
    }
}