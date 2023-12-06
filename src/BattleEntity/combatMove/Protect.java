package BattleEntity.combatMove;

import BattleEntity.battleUnit.BaseUnit;

public class Protect extends SupportMove{
    public Protect(String name, int mpConsume, BaseUnit owner){
        super(name,mpConsume,owner);
    }

    @Override
    public void performEffect() {
        if (!this.getOwner().isDestroyed()&& !this.getTarget().isDestroyed()){
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setProtected(true);
            //String text = toString();
        }
    }
}
