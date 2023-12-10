package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class DebuffDefense extends Buff {
    private double reduce;
    public DebuffDefense(String name,BaseUnit owner, int defaultCount, double reduce){
        super(name,owner,defaultCount);
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
