package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class DebuffDefense extends Buff {
    private double reduce;
    public DebuffDefense(BaseUnit owner, int defaultCount, double reduce){
        super(owner,defaultCount);
        this.reduce = reduce;
        performBuff();
    }
    public void performBuff(){
        int boostDefense = (int) (getOwner().getBaseDefense() * reduce);
        getOwner().setDefense(getOwner().getDefense() + boostDefense);
    }
    public void destroyBuff(){
        int boostDefense = (int) (getOwner().getBaseDefense() * reduce);
        getOwner().setDefense(getOwner().getDefense() - boostDefense);
    }
}
