package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class DebuffAttack extends Buff{ //count = turn + 1
    private double reduce;
    public DebuffAttack(BaseUnit owner, int defaultCount, double reduce){
        super(owner,defaultCount);
        this.reduce = reduce;
    }
    @Override
    public void performBuff(){
        setCount(getCount() + 1);
        int boostAttack = (int) (getOwner().getBaseAttack() * reduce);
        getOwner().setAttack(getOwner().getAttack() - boostAttack);
    }
    @Override
    public void destroyBuff(){
        int boostAttack = (int) (getOwner().getBaseAttack() * reduce);
        getOwner().setAttack(getOwner().getAttack() + boostAttack);
    }
}
