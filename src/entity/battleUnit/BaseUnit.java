package entity.battleUnit;

import entity.combatMove.BaseMove;
import interfaces.IRenderable;

public abstract class BaseUnit implements IRenderable {
    // Unit stats & status
    protected String name;
    protected int hp;
    protected int baseHp;
    protected int mp;
    protected int baseMp;
    protected int attack;
    protected int baseAttack;
    protected int defense;
    protected int baseDefense;
    protected boolean isProtected;
    protected boolean isAlive;

    // Array for storing moveSet
    protected BaseMove[] moveSet;

    // constructor
    public BaseUnit(String name,int baseHp,int baseMp, int baseAttack,int baseDefense) {
        setName(name);
        setBaseHp(baseHp);
        setHp(baseHp);
        setBaseMp(baseMp);
        setBaseMp(baseMp);
        setBaseAttack(baseAttack);
        setAttack(baseAttack);
        setBaseDefense(baseDefense);
        setDefense(baseDefense);
        setProtected(false);
        setAlive(true);
    }

    // methods
    public boolean isDestroyed() {
        return !this.isAlive;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void addMove(BaseMove move, int index) {
        moveSet[index]= move;
    }

    public String toString() {
        return this.name;
    }


    // getter & setter
    public abstract void setMoveSet();

    public void setName(String name){
        this.name = name;
    }

    public void setHp(int hp){
        if (hp < 0) hp = 0;
        if (getBaseHp() < hp) hp = getBaseHp();
        this.hp = hp;
    }

    public void setBaseHp(int baseHp){
        if (baseHp < 0){
            baseHp = 1;
        }
        this.baseHp = baseHp;
    }

    public void setMp(int mp) {
        if (mp < 0) mp = 0;
        if (getBaseMp() < mp) mp = getBaseMp();
        this.mp = mp;
    }

    public void setBaseMp(int baseMp) {
        this.baseMp = baseMp;
    }

    public void setAttack(int attack) {
        if (attack < 0) attack = 0;
        this.attack = attack;
    }

    public void setBaseAttack(int baseAttack) {
        if (baseAttack < 0) baseAttack = 0;
        this.baseAttack = baseAttack;
    }

    public void setDefense(int defense){
        if (defense < 0) defense = 0;
        this.defense = defense;
    }

    public void setBaseDefense(int baseDefense) {
        if (baseDefense < 0) baseDefense = 0;
        this.baseDefense = baseDefense;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public int getBaseHp(){
        return baseHp;
    }

    public int getMp() {
        return mp;
    }

    public int getBaseMp() {
        return baseMp;
    }

    public int getAttack() {
        return attack;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public BaseMove getMove(int index) {
        return moveSet[index];
    }

    @Override
    public int getZ() {
        return 0;
    }
}