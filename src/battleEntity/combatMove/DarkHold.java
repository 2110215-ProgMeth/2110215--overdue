package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import items.DefensePotion;
import logic.GameLogic;

public class DarkHold extends AttackMove{
    public DarkHold(BaseUnit owner){
        super(owner);
        setName("DarkHold");
        setPercentDamage(2);
        setMpConsume(60);
        setAllTarget(true);
    }

    public void performEffect() {
        if (!getOwner().isDestroyed()) {
            for (BaseUnit enemy : GameLogic.getEnemiesUnits()) {
                if (!enemy.isDestroyed()) {
                    Buff weak = new DebuffAttack("Weak", enemy, 3, 0.2);
                    Buff fragile = new DebuffDefense("Fragile", enemy, 3, 0.2);
                    int Damage = (int) (getPercentDamage() * getOwner().getAttack() - enemy.getDefense());
                    enemy.setHp(getTarget().getHp() - Damage);
                }
            }
        }
    }
}
