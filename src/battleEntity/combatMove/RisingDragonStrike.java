package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public class RisingDragonStrike extends AttackMove{
    public RisingDragonStrike(BaseUnit owner){
        super(owner);
        setName("RisingDragonStrike");
        setMpConsume(60);
        setPercentDamage(3);
        setDescription("Attack the enemy with a powerful upward thrust of your sword," +
                        "slashing with the force of a dragon's claws. The attack ignores the enemy's defense.");
    }

    @Override
    public void performEffect(BaseUnit target) {
        setTarget(target);
        if (!this.getOwner().isDestroyed()&& !this.getTarget().isDestroyed()) {
            //String text = this.toString();
            int Damage = (int) getPercentDamage() * getOwner().getAttack();
            getOwner().setMp(getOwner().getMp() - getMpConsume());
            getTarget().setHp(getTarget().getHp() - Damage);
        }
    }
}
