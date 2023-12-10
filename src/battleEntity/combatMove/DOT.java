package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class DOT extends Buff{
    private double percentDamage;
    public DOT(String name ,BaseUnit owner, int defaultCount, double percentDamage){
        super(name,owner,defaultCount);
        this.percentDamage = percentDamage;
    }
    @Override
    public void performBuff(){
        setCount(getCount() + 1);
        int damage = (int) (getOwner().getBaseHp() * percentDamage);
        getOwner().setHp(getOwner().getHp() - damage);
    }
    @Override
    public void destroyBuff(){
    }
}
