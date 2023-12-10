package worldObject;

import display.ScreenUtil;
import interfaces.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Entity implements IRenderable {

    public int WorldX,WorldY;
    public int speed;
    public String direction;
    public Rectangle solidArea;
    public Rectangle sprite;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    private int z = 0;

    public int getZ(){
        return z;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    public void setZ(int z){
        this.z = z;
    }
    public String name;
    //Method
   /* public void update();*/
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
