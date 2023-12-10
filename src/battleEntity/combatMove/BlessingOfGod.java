package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import display.ScreenUtil;
import logic.GameLogic;

public class BlessingOfGod extends SupportMove {
    public BlessingOfGod(BaseUnit owner){
        super(owner);
        setMpConsume(60);
        setPercentHeal(0.35);
        setAllTarget(true);
    }
    public void performEffect() {
        if (!this.getOwner().isDestroyed()){
            for(BaseUnit ally : GameLogic.getAlliessUnits()){
                if (!ally.isDestroyed()) {
                    int heal = (int) (ally.getBaseHp() * getPercentHeal() + 10);
                    ally.setHp(heal);
                    ally.getBuffers().add(new Barrier("Divine Shield",ally,1));
                    ally.getBuffers().add(new Temper("Blessed",ally,2,0.2));
                }
            }
        }
    }
}
