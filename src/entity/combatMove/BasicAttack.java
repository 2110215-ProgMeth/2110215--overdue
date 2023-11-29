package entity.combatMove;

import entity.battleUnit.BaseUnit;

public class BasicAttack extends BaseMove{
    public BasicAttack(String name, BaseUnit owner){
        super(name,100,0,owner);
    }
    @Override
    public void performEffect() {
        toString();
    }
}
