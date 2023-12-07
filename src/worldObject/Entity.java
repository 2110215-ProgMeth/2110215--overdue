package worldObject;

import interfaces.IRenderable;
import javafx.scene.shape.Rectangle;

public abstract class Entity implements IRenderable {

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
    public void setZ(int z){
        this.z = z;
    }
    //Method
    public abstract void update();
}
