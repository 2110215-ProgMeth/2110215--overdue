package entity.combatMove;

import entity.battleUnit.BaseUnit;

public abstract class AttackMove extends BaseMove{
    protected double percentDamage;
    public AttackMove(String name, double percentDamage, int mpConsume, BaseUnit owner){
        super(name,mpConsume,owner);
        setPercentDamage(percentDamage);
        setUsable(true);
    }

    @Override
    public void performEffect() {
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

}
