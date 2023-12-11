package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

import java.lang.annotation.Target;

public class Poison extends AttackMove{
    public Poison(BaseUnit owner){
        super(owner);
        setMpConsume(50);
        setPercentDamage(0);
        setName("Poison");
    }

    public void performEffect(BaseUnit target) {
        setTarget(target);
        Buff poison = new DOT("Poison",getTarget(),2,0.15);
        Buff weakness = new DebuffDefense("Weakness",getTarget(),2,0.1);
        getTarget().getBuffers().add(poison);
        getTarget().getBuffers().add(weakness);
    }
}
