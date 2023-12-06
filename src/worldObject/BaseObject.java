package worldObject;

import display.GameScreen;
import display.ScreenUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BaseObject {
    public Image image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;

    // constructor
    public BaseObject(int worldX, int worldY) {
        setWorldX(worldX);
        setWorldY(worldY);
    }

    public void draw(GraphicsContext gc, GameScreen gameScreen){
        int screenX = worldX - gameScreen.player.WorldX + gameScreen.player.screenX;
        int screenY = worldY - gameScreen.player.WorldY + gameScreen.player.screenY;
        if (worldX + ScreenUtil.tileSize > gameScreen.player.WorldX - gameScreen.player.screenX &&
                worldX - ScreenUtil.tileSize < gameScreen.player.WorldX + gameScreen.player.screenX &&
                worldY + ScreenUtil.tileSize > gameScreen.player.WorldY - gameScreen.player.screenY &&
                worldY - ScreenUtil.tileSize < gameScreen.player.WorldY + gameScreen.player.screenY){
            gc.drawImage(image,screenX,screenY,ScreenUtil.tileSize,ScreenUtil.tileSize);
        }

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

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
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

}
