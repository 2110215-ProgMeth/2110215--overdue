package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class Judgement extends AttackMove{
    public Judgement(BaseUnit owner){
        super(owner);
        setName("Judgement");
        setAllTarget(true);
        setPercentDamage(1);
        setMpConsume(50);
    }

    @Override
    public void performEffect(BaseUnit target) {
        Buff temper = new Temper("Temper",getOwner(),2,0.5);
        Buff tough = new BoostDefense("Tough",getOwner(),2,1);
        getOwner().getBuffers().add(temper);
        getOwner().getBuffers().add(tough);
        for(BaseUnit enemy : GameLogic.getEnemiesUnits()){
            if (!enemy.isDestroyed()) {
                int Damage = (int) (getPercentDamage() * getOwner().getAttack() - enemy.getDefense());
                enemy.setHp(getTarget().getHp() - Damage);
            }
        }
    }
}
