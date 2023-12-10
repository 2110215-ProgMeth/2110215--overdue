package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;
import display.ScreenUtil;
import logic.GameLogic;

public class BlessingOfGod extends SupportMove {
    public BlessingOfGod(BaseUnit owner){
        super(owner);
        setPercentHeal(0.35);
        setAllTarget(true);
    }
    public void performEffect() {
        if (!this.getOwner().isDestroyed()){
            for(BaseUnit ally : GameLogic.getAlliessUnits()){
                if (!ally.isDestroyed()) {
                    int heal = (int) (ally.getBaseHp() * getPercentHeal() + 10);
                    ally.setHp(heal);
                    ally.getBuffers().add(new Barrier(ally,1));
                    ally.getBuffers().add(new BoostDefense(ally,2,0.5));
                    ally.getBuffers().add(new BoostAttack(ally,2,0.2));
                }
            }
        }
    }
}
