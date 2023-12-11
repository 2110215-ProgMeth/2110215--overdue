package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class DemonBite extends AttackMove{
    public DemonBite(BaseUnit owner){
        super(owner);
        setName("DemonBite");
        setPercentDamage(1.5);
    }

    @Override
    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!getOwner().isDestroyed() && !getTarget().isDestroyed()){
            Buff deadlyVenom = new DOT("Deadly Venom",getTarget(),3,0.3);
            getTarget().getBuffers().add(deadlyVenom);
            int Damage = (int) getPercentDamage() * getOwner().getAttack() - getTarget().getDefense();
            getTarget().setHp(getTarget().getHp() - Damage);
        }
    }
}
