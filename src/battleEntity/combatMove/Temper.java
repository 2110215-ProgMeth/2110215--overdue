package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class Temper extends Buff {
    private double boost;
    public Temper(String name,BaseUnit owner,int defaultCount,double boost){
        super(name,owner,defaultCount);
        this.boost = boost;
        performBuff();
    }
    @Override
    public void performBuff() {
        int boostAttack = (int) (getOwner().getBaseAttack() * boost);
        getOwner().setAttack(getOwner().getAttack() + boostAttack);
    }
    @Override
    public void destroyBuff() {
        int boostAttack = (int) (getOwner().getBaseAttack() * boost);
        getOwner().setAttack(getOwner().getAttack() - boostAttack);
    }
}
