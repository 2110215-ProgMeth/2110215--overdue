package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class BoostDefense extends Buff{
    private double boost;
    public BoostDefense(BaseUnit owner, int defaultCount, double boost){
        super(owner,defaultCount);
        this.boost = boost;
        performBuff();
    }
    public void performBuff(){
        int boostDefense = (int) (getOwner().getBaseDefense() * boost);
        getOwner().setDefense(getOwner().getDefense() + boostDefense);
    }
    public void destroyBuff(){
        int boostDefense = (int) (getOwner().getBaseDefense() * boost);
        getOwner().setDefense(getOwner().getDefense() - boostDefense);
    }
}
