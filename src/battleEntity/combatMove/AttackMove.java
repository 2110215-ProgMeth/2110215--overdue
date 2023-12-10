package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public abstract class AttackMove extends BaseMove{
    protected double percentDamage;
    public AttackMove(BaseUnit owner){
        super(owner);
        setUsable(true);
    }

    @Override
    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!this.getOwner().isDestroyed()&& !this.getTarget().isDestroyed()) {
            //String text = this.toString();
            int Damage = (int) getPercentDamage() * getOwner().getAttack() - getTarget().getDefense();
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setHp(getTarget().getHp() - Damage);
        }
    }

    @Override
    public String toString() {
        return getOwner() + " used " + getName() + " on " + getTarget();
    }

    public void setPercentDamage(double damage) {
        this.percentDamage = damage;
    }

    public double getPercentDamage() {
        return percentDamage;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
