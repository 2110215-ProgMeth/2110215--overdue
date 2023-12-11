package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class HolyLight extends AttackMove{
    public HolyLight(BaseUnit owner){
        super(owner);
        setName("HolyLight");
        setPercentDamage(1.5);
        setMpConsume(25);
        setAllTarget(true);
    }

    public void performEffect(BaseUnit target) {
        if (!this.getOwner().isDestroyed()) {
            //String text = this.toString();
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            for (BaseUnit enemy : GameLogic.getEnemiesUnits())
                if (!enemy.isDestroyed()) {
                    int Damage = (int) (getPercentDamage() * getOwner().getAttack() - enemy.getDefense());
                    enemy.setHp(getTarget().getHp() - Damage);
                }
        }
    }
}
