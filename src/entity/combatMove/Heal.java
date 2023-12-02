package entity.combatMove;

import entity.battleUnit.BaseUnit;

public class Heal extends SupportMove{
    protected int heal;
    public Heal(String name, int mpConsume, BaseUnit owner,int Heal){
        super(name, mpConsume, owner);
        setPercentHeal(Heal);
    }

    @Override
    public void performEffect() {
        if (!this.getOwner().isDestroyed()&& !this.getTarget().isDestroyed()){
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setHp(getTarget().getHp()+heal);
            //String text = toString();
        }
    }

    public void setPercentHeal(int heal) {
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }
}
