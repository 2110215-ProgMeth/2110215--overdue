package worldObject;

import display.GameScreen;
import display.ScreenUtil;

public class CollisionChecker {
    GameScreen gameScreen;
    public CollisionChecker(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX =  entity.WorldX + (int) entity.solidArea.getX();
        int entityRightWorldX =  entity.WorldX + (int) (entity.solidArea.getX()+entity.solidArea.getWidth());
        int entityTopWorldY = entity.WorldY + (int) entity.solidArea.getY();
        int entityBottomWorldY = entity.WorldY + (int) (entity.solidArea.getY() + entity.solidArea.getHeight());

        int entityLeftCol = entityLeftWorldX  / ScreenUtil.tileSize;
        int entityRightCol = entityRightWorldX / ScreenUtil.tileSize;
        int entityTopRow = entityTopWorldY / ScreenUtil.tileSize;
        int entityBottomRow = entityBottomWorldY / ScreenUtil.tileSize;

        int tileNum1, tileNum2;
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / ScreenUtil.tileSize;
                tileNum1 = gameScreen.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameScreen.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gameScreen.tileM.tile[tileNum1].collision || gameScreen.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / ScreenUtil.tileSize;
                tileNum1 = gameScreen.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gameScreen.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gameScreen.tileM.tile[tileNum1].collision || gameScreen.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / ScreenUtil.tileSize;
                tileNum1 = gameScreen.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameScreen.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gameScreen.tileM.tile[tileNum1].collision || gameScreen.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / ScreenUtil.tileSize;
                tileNum1 = gameScreen.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gameScreen.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gameScreen.tileM.tile[tileNum1].collision || gameScreen.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
