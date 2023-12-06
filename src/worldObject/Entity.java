package worldObject;

import javafx.scene.shape.Rectangle;

public abstract class Entity {

    public int WorldX,WorldY;
    public int speed;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX, getSolidAreaDefaultY;
    public boolean collisionOn = false;
    //Method
}
