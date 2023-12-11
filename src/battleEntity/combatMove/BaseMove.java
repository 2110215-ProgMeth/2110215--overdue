package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public abstract class BaseMove {
    protected String name;
    protected int mpConsume = 0;
    protected BaseUnit owner;
    protected BaseUnit target;
    protected String description;
    private boolean allTarget = false;
    //constructor
    public BaseMove(BaseUnit owner){
        setOwner(owner);
    }
    //method
    public abstract void performEffect(BaseUnit target);

    @Override
    public abstract String toString();

    //getter setter
    public String getName() {
        return name;
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

    public String getDescription() {
        return this.description;
    }

    public boolean isAllTarget(){
        return allTarget;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setDescription(String description){
        this.description = description;
    }
    public boolean isUsable() {
        if (owner.getMp() < mpConsume) return false;
        else return true;
    }

    public void setAllTarget(boolean allTarget) {
        this.allTarget = allTarget;
    }
}
