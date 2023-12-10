package worldObject;

import control.InputUtility;
import display.GameScreen;
import display.ScreenUtil;
import interfaces.IRenderable;
import interfaces.Moveable;
import items.*;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;
import org.ietf.jgss.GSSManager;
import sharedObject.RenderableHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Entity implements Moveable {
    public final int screenX;
    public final int screenY;
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public boolean front = false;
    public boolean trade = false;
    public Rectangle tradeBox;
    public static int currentMoney;
    public static HashMap<String, Integer> playerInventory;

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
        playerInventory = new HashMap<String, Integer>();
        playerInventory.put("Health potion", 1);
        playerInventory.put("Mana potion", 1);
        playerInventory.put("Strength potion", 0);
        playerInventory.put("Defense potion", 0);
        playerInventory.put("Throwing knife", 0);
        playerInventory.put("Bomb", 0);
        setMoney(10);
    }
    //method
    private void up(){
        this.WorldY -= speed;
    }
    private void down(){
        this.WorldY += speed;
        System.out.println("DOWN!!");
    }
    private void left(){
        this.WorldX -= speed;
    }
    private void right(){
        this.WorldX += speed;
    }

    @Override
    public void updateCoordinate() {
        //System.out.println("Player update called");
      //   System.out.println();
      //  System.out.println(Player.getPlayerInventory().get("Health potion"));
        if (InputUtility.getKeyPressed(KeyCode.W) || InputUtility.getKeyPressed(KeyCode.A)
         ||InputUtility.getKeyPressed(KeyCode.S) || InputUtility.getKeyPressed(KeyCode.D)) {
            if (GameLogic.getGameState() == GameLogic.worldState) {
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
                trade = false;
                collisionOn = false; ///***
                GameLogic.checkTile(this);
                int i = GameLogic.isFront(this);

                if (i != 999) {
                    if (!front) {
                        System.out.println("Player is infront of the object");
                        RenderableHolder.getCurrentEntities().remove(this);
                        RenderableHolder.getCurrentEntities().add(RenderableHolder.townEntities.size(), this);
                        front = true;
                    }
                } else {
                    if (front) {
                        System.out.println("Player is behind the object");
                        RenderableHolder.getCurrentEntities().remove(this);
                        RenderableHolder.getCurrentEntities().add(0, this);
                        front = false;
                    }
                }

                //CHECK OBJECT COLLISION
                int objIndex = GameLogic.checkObject(this, true);
                interactObject(objIndex);

                }
                // IF COLLISION IS FALSE, PLAYER CAN MOVE
                if (!collisionOn && GameLogic.getGameState() == GameLogic.worldState) {
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

        }else{
            spriteNum = 0;
            int objIndex = GameLogic.checkObject(this, true);
            interactObject(objIndex);
        }
    }
    public void interactObject(int i){
        if (i != 999) {

            String objName = GameLogic.getBaseObject()[i].getName();
            Platform.runLater(() -> {
                switch ((objName)) {
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
                    case "Merchant":
                        System.out.println("LET'S BUY !!!");
                        if(InputUtility.getKeyPressed(KeyCode.J)){
                            System.out.println("TRADE !!!");
                            GameLogic.setGameState(GameLogic.buyState);
                            GameLogic.openShop();
                        }
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

    public static void setMoney(int money) {
        currentMoney = money;
    }
    public static int getMoney() {
        return currentMoney;
    }
    public static HashMap<String, Integer> getPlayerInventory() {
        return playerInventory;
    }

}
