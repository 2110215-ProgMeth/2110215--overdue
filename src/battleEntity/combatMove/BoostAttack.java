package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class BoostAttack extends Buff{
    private double boost;
    public BoostAttack(BaseUnit owner,int defaultCount,double boost){
        super(owner,defaultCount);
        this.boost = boost;
        performBuff();
    }
    @Override
    public void performBuff(){
        setCount(getCount() + 1);
        int boostAttack = (int) (getOwner().getBaseAttack() * boost);
        getOwner().setAttack(getOwner().getAttack() + boostAttack);
    }
    @Override
    public void destroyBuff(){
        int boostAttack = (int) (getOwner().getBaseAttack() * boost);
        getOwner().setAttack(getOwner().getAttack() - boostAttack);
    }
}
