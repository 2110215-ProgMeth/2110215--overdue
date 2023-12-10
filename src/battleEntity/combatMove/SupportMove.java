package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class SupportMove extends BaseMove{
    protected double percentHeal;
    public SupportMove(BaseUnit owner){
        super(owner);
    }

    @Override
    public void performEffect(BaseUnit target) {
    }

    @Override
    public String toString() {
        return getOwner() + " used " + getName() + " on " + getTarget();
    }

    public void setPercentHeal(double percentHeal) {
        this.percentHeal = percentHeal;
    }
    public double getPercentHeal() {
        return percentHeal;
    }

}
