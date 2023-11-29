package entity.combatMove;

import entity.battleUnit.BaseUnit;

public abstract class BaseMove {
    protected String name;
    protected int percentDamage;
    protected int mpConsume;
    protected BaseUnit owner;
    protected BaseUnit target;

    //constructor
    public BaseMove(String name,int percentDamage,int mpConsume,BaseUnit owner){
        setName(name);
        setDamage(percentDamage);
        setMpConsume(mpConsume);
        setOwner(owner);
    }
    //method
    public abstract void performEffect();

    @Override
    public abstract String toString();

    //getter setter
    public String getName() {
        return name;
    }
    public int getDamage() {
        return percentDamage;
    }

    public int getMpConsume() {
        return mpConsume;
    }

    public BaseUnit getOwner() {
        return owner;
    }

    public BaseUnit getTarget() {
        return target;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.percentDamage = damage;
    }

    public void setMpConsume(int mpConsume){
        this.mpConsume = mpConsume;
    }

    public void setOwner(BaseUnit owner) {
        this.owner = owner;
    }
    public void setTarget(BaseUnit target){
        this.target = target;
    }
}
