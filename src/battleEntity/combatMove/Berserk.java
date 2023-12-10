package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

import java.lang.annotation.Target;

public class Berserk extends SupportMove{
    public Berserk(BaseUnit owner){
        super(owner);
        setName("Berserk");
        setAllTarget(true);
    }

    @Override
    public void performEffect(BaseUnit target) {
        Buff berserk = new Temper("Berserk",getOwner(),2,0.5);
        Buff tough = new BoostDefense("Tough",getOwner(),2,1);
        getOwner().getBuffers().add(berserk);
        getOwner().getBuffers().add(tough);
    }
}
