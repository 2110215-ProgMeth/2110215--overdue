package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class Barrier extends Buff{
    public Barrier(String name,BaseUnit owner,int defaultCount){
        super(name,owner,defaultCount);
        performBuff();
    }
    public void performBuff(){
        getOwner().setProtected(true);
    }
    public void destroyBuff(){
        getOwner().setProtected(false);
    }
}
