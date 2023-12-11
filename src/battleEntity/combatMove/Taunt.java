package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import battleEntity.battleUnit.Warrior;

public class Taunt extends AttackMove{
    public Taunt(Warrior owner){
        super(owner);
        setName("Taunt");
        setAllTarget(true);
    }

    @Override
    public void performEffect(BaseUnit target) {
        Buff boostDefense = new BoostDefense("Tough",owner,2,2);
        owner.getBuffers().add(boostDefense);
        if (owner instanceof Warrior){
            ((Warrior) owner).setTaunt(true); //GameLogic need to turn off taunt in another cycle!!!
        }
    }
}
