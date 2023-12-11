package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class Heal extends SupportMove {
    public Heal(BaseUnit owner) {
        super(owner);
        setName("Heal");
        setPercentHeal(0.3);
        setMpConsume(30);
        setDescription("Heal an ally,with the power of god blessing");
    }

    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!this.getOwner().isDestroyed() && !this.getTarget().isDestroyed()) {
            int heal = (int) (getTarget().getBaseHp() * getPercentHeal() + 10);
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setHp(getTarget().getHp() + heal);
            for (Buff buff : getTarget().getBuffers()) {
                if (buff instanceof DebuffAttack || buff instanceof DebuffDefense || buff instanceof DOT) {
                    buff.destroyBuff();
                    getTarget().getBuffers().remove(buff);
                }
                //String text = toString();
            }
        }
    }
}
