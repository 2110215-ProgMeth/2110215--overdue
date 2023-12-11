package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class Unbreakable extends SupportMove{
    public Unbreakable(BaseUnit owner){
        super(owner);
        setName("Unberakable");
        setPercentHeal(0.2);
        setAllTarget(true);
    }

    public void performEffect() {
        Buff unbreakable = new BoostDefense("Unbreakable",getOwner(),2,2);
        getOwner().getBuffers().add(unbreakable);
        getOwner().setHp(getOwner().getHp() + (int) (getOwner().getHp() * getPercentHeal()));
    }
}
