package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class BackStab extends AttackMove{
    public BackStab(BaseUnit owner) {
        super(owner);
        setName("BackStab");
        setPercentDamage(1.5);
        setMpConsume(15);
        setDescription("The user attacks target from behind. Deals ");
    }

    @Override
    public void performEffect() {
        if (!this.getOwner().isDestroyed() && !this.getTarget().isDestroyed()) {
            //String text = this.toString();
            int Damage = (int) getPercentDamage() * getOwner().getAttack() - (int) (getTarget().getDefense() * 0.4);
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setHp(getTarget().getHp() - Damage);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
