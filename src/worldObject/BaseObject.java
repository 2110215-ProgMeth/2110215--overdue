package worldObject;

import display.GameScreen;
import display.ScreenUtil;
import interfaces.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;

public abstract class BaseObject extends Entity implements IRenderable {
    public Image image;
    public String name;
    public boolean collision = false;
    public int WorldX,WorldY;
    public Rectangle solidArea;
    public double solidAreaDefaultX = 0;
    public double solidAreaDefaultY = 0;
    public double scaleX, scaleY;
    public boolean destroyed;
    public int z = 1;
    // constructor
    public BaseObject(int WorldX, int WorldY) {
        setWorldX(WorldX * ScreenUtil.tileSize);
        setWorldY(WorldY * ScreenUtil.tileSize);
        setScale(1,1);
        solidArea = new Rectangle(0,0,ScreenUtil.tileSize,ScreenUtil.tileSize);
    }
    public BaseObject(int WorldX, int WorldY, double scaleX, double scaleY,double solidX,double solidY,double solidW,double solidH){
        setWorldX(WorldX * ScreenUtil.tileSize);
        setWorldY(WorldY * ScreenUtil.tileSize);
        setScale(scaleX,scaleY);
        solidAreaDefaultX =  solidX * ScreenUtil.tileSize;
        solidAreaDefaultY =  solidY * ScreenUtil.tileSize;
        solidArea = new Rectangle(solidX * ScreenUtil.tileSize,solidY * ScreenUtil.tileSize,solidW * ScreenUtil.tileSize,solidH * ScreenUtil.tileSize);
    }

    public void draw(GraphicsContext gc){
        int screenX = WorldX - GameLogic.getPlayer().WorldX + GameLogic.getPlayer().screenX;
        int screenY = WorldY - GameLogic.getPlayer().WorldY + GameLogic.getPlayer().screenY;
        if (WorldX + scaleX * ScreenUtil.tileSize > GameLogic.getPlayer().WorldX - GameLogic.getPlayer().screenX &&
                WorldX - scaleX * ScreenUtil.tileSize < GameLogic.getPlayer().WorldX + GameLogic.getPlayer().screenX &&
                WorldY + scaleY * ScreenUtil.tileSize > GameLogic.getPlayer().WorldY - GameLogic.getPlayer().screenY &&
                WorldY - scaleY * ScreenUtil.tileSize < GameLogic.getPlayer().WorldY + GameLogic.getPlayer().screenY){
            gc.drawImage(image,screenX,screenY,scaleX * ScreenUtil.tileSize,scaleY * ScreenUtil.tileSize);
        }

    }

    public void setScale(double scaleX,double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    public void update(){

    }

    @Override
    public int getZ() {
        return z;
    }
    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setWorldX(int worldX) {
        this.WorldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.WorldY = worldY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollision(Boolean collision) {
        this.collision = collision;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

}