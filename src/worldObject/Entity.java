package worldObject;

import display.ScreenUtil;
import interfaces.IRenderable;
import javafx.scene.shape.Rectangle;

public abstract class Entity implements IRenderable {

    public static int WorldX,WorldY;
    public static int speed;
    public static String direction;
    public static Rectangle solidArea;
    public static Rectangle sprite;
    public static int solidAreaDefaultX, solidAreaDefaultY;
    public static boolean collisionOn = false;
    private int z = 0;

    public int getZ(){
        return z;
    }
    public void setZ(int z){
        this.z = z;
    }
    public String name;
    //Method
    public abstract void update();
    public boolean isCollision(){
        return collisionOn;
    }
    //setter getter
    public void setName(String name){
        this.name = name;
    }
    public void setWorldX(int worldX){
        this.WorldX = ScreenUtil.tileSize * worldX;
    }
    public void setWorldY(int worldY){
        this.WorldY = ScreenUtil.tileSize * worldY;
    }

    public String getName() {
        return name;
    }
}
