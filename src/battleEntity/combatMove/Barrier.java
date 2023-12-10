package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class Barrier extends Buff{
    public Barrier(BaseUnit owner,int defaultCount){
        super(owner,1);
        performBuff();
    }
    public void performBuff(){
        setCount(getCount() + 1);
        getOwner().setProtected(true);
    }
    public void destroyBuff(){
        getOwner().setProtected(false);
    }
}
