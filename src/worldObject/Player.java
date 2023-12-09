package worldObject;

import control.InputUtility;
import display.GameScreen;
import display.ScreenUtil;
import interfaces.IRenderable;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;
import org.ietf.jgss.GSSManager;
import sharedObject.RenderableHolder;
import java.util.List;

public class Player extends Entity {
    public final int screenX;
    public final int screenY;
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public boolean front = false;
    //Animation Character
    public Image up0 = new Image("/playerImage/up0.png");
    public Image up1 = new Image("/playerImage/up1.png");
    public Image up2 = new Image("/playerImage/up2.png");
    public Image down0 = new Image("/playerImage/down0.png");
    public Image down1 = new Image("/playerImage/down1.png");
    public Image down2 = new Image("/playerImage/down2.png");
    public Image left0 = new Image("/playerImage/left0.png");
    public Image left1 = new Image("/playerImage/left1.png");
    public Image left2 = new Image("/playerImage/left2.png");
    public Image right0 = new Image("/playerImage/right0.png");
    public Image right1 = new Image("/playerImage/right1.png");
    public Image right2 = new Image("/playerImage/right2.png");

    public Player(int WorldX,int WorldY) {
        screenX = ScreenUtil.screenWidth/2 - (ScreenUtil.tileSize/2);
        screenY = ScreenUtil.screenHeight/2 - (ScreenUtil.tileSize/2);
        solidArea = new Rectangle();
        solidArea.setX((double) ScreenUtil.tileSize / 6);
        solidArea.setY((double) (ScreenUtil.tileSize * 2) / 6);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
        solidArea.setHeight((double)  (ScreenUtil.tileSize * 2) / 3);
        solidArea.setWidth((double)  (ScreenUtil.tileSize * 2) / 3);
        sprite = new Rectangle(0,0,ScreenUtil.tileSize,ScreenUtil.tileSize-10);
        this.WorldX = ScreenUtil.tileSize * WorldX; // 25
        this.WorldY = ScreenUtil.tileSize * WorldY; // 27
        direction = "down";
        speed = ScreenUtil.scale;
    }
    //method
    private void up(){
        this.WorldY -= speed;
    }
    private void down(){
        this.WorldY += speed;
    }
    private void left(){
        this.WorldX -= speed;
    }
    private void right(){
        this.WorldX += speed;
    }

    public void update(){
        //System.out.println("Player update called");
      //   System.out.println();
     //   System.out.println(ScreenUtil.getFPS());
        if (InputUtility.getKeyPressed(KeyCode.W) || InputUtility.getKeyPressed(KeyCode.A)
         ||InputUtility.getKeyPressed(KeyCode.S) || InputUtility.getKeyPressed(KeyCode.D)) {
            if (GameLogic.getGameState() != GameLogic.pauseState) {
                //SET DIRECTION
                if (InputUtility.getKeyPressed(KeyCode.W)) {
                    direction = "up";
                }
                if (InputUtility.getKeyPressed(KeyCode.S)) {
                    direction = "down";
                }
                if (InputUtility.getKeyPressed(KeyCode.A)) {
                    direction = "left";
                }
                if (InputUtility.getKeyPressed(KeyCode.D)) {
                    direction = "right";
                }


                //CHECK TILE COLLISION
                collisionOn = false;
                GameLogic.checkTile(this);
                int i = GameLogic.isFront(this);
                if (i != 999) {
                    if (!front) {
                        System.out.println("Player is infront of the object");
                        RenderableHolder.townEntities.remove(this);
                        RenderableHolder.townEntities.add(RenderableHolder.townEntities.size(), this);
                        front = true;
                    }
                } else {
                    if (front) {
                        System.out.println("Player is behind the object");
                        RenderableHolder.townEntities.remove(this);
                        RenderableHolder.townEntities.add(0, this);
                        front = false;
                    }
                }

                //CHECK OBJECT COLLISION
                int objIndex = GameLogic.checkObject(this, true);
                interactObject(objIndex);

                // IF COLLISION IS FALSE, PLAYER CAN MOVE
                if (!collisionOn && GameLogic.getGameState() != GameLogic.pauseState) {
                    switch (direction) {
                        case "up":
                            up();
                            break;
                        case "down":
                            down();
                            break;
                        case "left":
                            left();
                            break;
                        case "right":
                            right();
                            break;
                    }
                }

                //SWITCH CHARACTER MOVEMENT
                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteNum == 0) {
                        spriteNum = 1;
                    } else if (spriteNum == 1) {
                        spriteNum = -1;
                    } else if (spriteNum == -1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 0;
                    }
                    spriteCounter = 0;
                }
            }
        }else{
            spriteNum = 0;
        }
    }
    public void interactObject(int i){
        if (i != 999){
            Player player;
            List<IRenderable> holder;
            if (GameLogic.getCurrentMap() == GameLogic.townMap){
               holder = RenderableHolder.getTownEntities();
            }else{
                holder = RenderableHolder.getForestEntities();
            }
            String objName = holder.get(i).getName();
            Platform.runLater(() -> {
                switch ((objName)){
                    case "WARP_TO_FOREST":
                        System.out.println("GO TO FOREST");
                        GameLogic.setCurrentMap(GameLogic.forestMap);
                        GameLogic.getPlayer().setWorldX(35);
                        GameLogic.getPlayer().setWorldY(38);
                        RenderableHolder.getTownEntities().clear();
                        RenderableHolder.getForestEntities().clear();
                        GameLogic.setupGame();
                        break;
                    case "WARP_TO_TOWN":
                        System.out.println("GO TO TOWN");
                        GameLogic.getPlayer().setWorldX(25);
                        GameLogic.getPlayer().setWorldY(40);
                        RenderableHolder.getTownEntities().clear();
                        RenderableHolder.getForestEntities().clear();
                        GameLogic.setCurrentMap(GameLogic.townMap);
                        GameLogic.setupGame();
                        break;
                }
            });
        }
    }
    public void draw(GraphicsContext gc) {
        Image image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }if(spriteNum == 2){
                    image = up2;
                }if(spriteNum == 0 || spriteNum == -1){
                    image = up0;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }if(spriteNum == 2){
                    image = down2;
                }if(spriteNum == 0 || spriteNum == -1){
                    image = down0;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }if(spriteNum == 2){
                    image = left2;
                }if(spriteNum == 0 || spriteNum == -1){
                    image = left0;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }if(spriteNum == 2){
                    image = right2;
                }if(spriteNum == 0 || spriteNum == -1){
                    image = right0;
                }
                break;
        }
        gc.drawImage(image,screenX,screenY,ScreenUtil.tileSize,ScreenUtil.tileSize);
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
