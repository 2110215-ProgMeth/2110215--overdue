package battleEntity.combatMove;

import battleEntity.battleUnit.BaseUnit;

public abstract class BaseMove {
    protected String name;
    protected int mpConsume;
    protected BaseUnit owner;
    protected BaseUnit target;
    protected String description;
    protected boolean isUsable;
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

    public String getDecription() {
        return this.description;
    }

    public boolean isUsable() {
        return this.isUsable;
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
    public void setUsable(boolean usable) {
        if (owner.getMp() < mpConsume) this.isUsable = false;
        else this.isUsable = usable;
    }

    public void setAllTarget(boolean allTarget) {
        this.allTarget = allTarget;
    }
}
