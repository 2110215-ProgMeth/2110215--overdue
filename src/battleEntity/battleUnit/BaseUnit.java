package battleEntity.battleUnit;

import battleEntity.combatMove.BaseMove;
import battleEntity.combatMove.DOT;
import display.ScreenUtil;
import interfaces.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import battleEntity.combatMove.Buff;
import sharedObject.RenderableHolder;

import java.util.ArrayList;

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
    protected BaseMove[] moveSet = new BaseMove[4];
    // Array for storing Buff
    protected ArrayList<Buff> buffers = new ArrayList<>();
    // Array for storing Loop move
    protected ArrayList<Integer> Loop = new ArrayList<>() ;
    protected int currentLoop = 0 ;
    //for Animation
    protected WritableImage[] images;
    protected double x,y;
    protected int spriteCounter = 0 ;
    protected int spriteNum = 0 ;
    // constructor
    public BaseUnit(String name,int baseHp,int baseMp, int baseAttack,int baseDefense) {
        setName(name);
        setBaseHp(baseHp);
        setHp(baseHp);
        setBaseMp(baseMp);
        setMp(baseMp);
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
    public void setStat(){
        setHp(getBaseHp());
        setMp(getBaseMp());
        this.buffers = new ArrayList<>();
    }

    // getter & setter
    public abstract void setMoveSet();

    public void setName(String name){
        this.name = name;
    }

    public void setHp(int hp){
        if (hp <= 0)  this.isAlive = false;
        else  if (getBaseHp() < hp) hp = getBaseHp();
        else this.hp = hp;
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
    public ArrayList<Buff> getBuffers(){
        return buffers;
    }
    public BaseMove getMove(int index) {
        return moveSet[index];
    }
    public WritableImage[] getImages(){
        return images;
    }
    public void setPosition(double x,double y){
        this.x = x;
        this.y = y;
    }

    public int getCurrentLoop() {
        return currentLoop;
    }

    public void setCurrentLoop(int currentLoop) {
        this.currentLoop = currentLoop;
    }

    public BaseMove[] getMoveSet() {
        return moveSet;
    }

    public void update(){ //everyone always update alive
        /*for (Buff buff : this.getBuffers()){
            buff.setCount(buff.getCount() + 1);
            if (buff instanceof DOT){
                buff.performBuff();
            }
        }*/
        if (this.getHp() <= 0){
            this.setAlive(false);
        }
    }
    public void updateAnimation(){
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum < images.length){
                spriteNum++;
            }else{
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }
    public int updateLoop(){
        if (currentLoop == Loop.size()) currentLoop = 0;
        return Loop.get(currentLoop++);
    }
    @Override
    public int getZ() {
        return 0;
    }
    public void draw(GraphicsContext gc){
        gc.drawImage(images[spriteNum], x, y, ScreenUtil.tileSize, ScreenUtil.tileSize);
    } // Draw
}
