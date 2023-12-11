package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import logic.GameLogic;

public class DarkMagic extends SupportMove{
    public DarkMagic(BaseUnit owner){
        super(owner);
        setName("DarkMagic");
        setAllTarget(true);
    }

    public void performEffect(BaseUnit target) {
        if (!this.getOwner().isDestroyed()){
            if (getOwner().getHp() <= getOwner().getBaseHp() * 0.2){
                getOwner().setHp(1);
            }else{
                getOwner().setHp((getOwner().getHp() - (int) (getOwner().getBaseHp() * 0.2)));
            }
            for(BaseUnit enemy : GameLogic.getEnemiesUnits()){
                if (!enemy.isDestroyed()){
                    Buff darkPower = new Temper("DARK POWER",enemy,2,0.3);
                    enemy.getBuffers().add(darkPower);
                }
            }

        }
    }
}
