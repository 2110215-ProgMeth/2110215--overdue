package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public abstract class Buff {
    private int defaultCount;
    private int count = 0;
    private String name;
    private BaseUnit owner;
    public Buff(BaseUnit owner,int defaultCount){
        setDefaultCount(defaultCount);
        setOwner(owner);
    }
    public abstract void performBuff();
    public abstract void destroyBuff();

    public void setOwner(BaseUnit owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultCount(int defaultCount) {
        this.defaultCount = defaultCount;
    }

    public void setCount(int count) {
        this.count = count;
    } //set in LogicGame !!!

    public int getCount() {
        return count;
    }

    public BaseUnit getOwner() {
        return owner;
    }
}
