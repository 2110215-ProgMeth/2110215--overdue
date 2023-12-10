package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class EnchantDeadlyPoison extends AttackMove{
    public EnchantDeadlyPoison(BaseUnit owner){
        super(owner);
        setName("EnchantDeadlyPoison");
        setMpConsume(25);
        setPercentDamage(1);
    }
    @Override
    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!this.getOwner().isDestroyed() && !this.getTarget().isDestroyed()) {
            //String text = this.toString();
            Buff temper = new Temper("Focus",owner,2,0.5);
            owner.getBuffers().add(temper);
            int Damage = (int) getPercentDamage() * getOwner().getAttack() - getTarget().getDefense();
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setHp(getTarget().getHp() - Damage);
            DOT poison = new DOT("Poison",getTarget(),2,0.2);
            getTarget().getBuffers().add(poison);
        }
    }
}
