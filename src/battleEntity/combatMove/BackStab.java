package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class BackStab extends AttackMove{
    public BackStab(BaseUnit owner) {
        super(owner);
        setName("BackStab");
        setPercentDamage(1.5);
        setMpConsume(15);
        setDescription("The user attacks target from behind.\nIgnore target defense by 50%");
    }

    @Override
    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!this.getOwner().isDestroyed() && !this.getTarget().isDestroyed()) {
            int Damage = (int) getPercentDamage() * getOwner().getAttack() - (int) (getTarget().getDefense() * 0.5);
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setHp(getTarget().getHp() - Damage);
        }
    }

}
