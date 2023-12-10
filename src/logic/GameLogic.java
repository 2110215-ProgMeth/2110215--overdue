package logic;

import battleEntity.battleUnit.BaseUnit;
import control.InputUtility;
import display.GameScreen;
import display.ScreenUtil;
import font.FontManager;
import gui.RootPane;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;
import sound.Sound;
import tile.TileManager;
import worldObject.*;
import worldObject.buildings.*;
import worldObject.forest.*;
import worldObject.npc.Lady;
import worldObject.npc.Man1Left;
import worldObject.npc.Merchant;
import java.util.ArrayList;
import java.io.IOException;

public class GameLogic {
    //private static ArrayList<IRenderable> gameObjectContainer;
    private static RootPane root;
    private static Scene scene;
    private static GameScreen gameScreen;
    private static Thread gameThread;
    private static int FPS = ScreenUtil.getFPS();
    private static TileManager tilemanager;
    private static FontManager fontManager;
    private static BaseObject baseObjectTown[];
    private static BaseObject baseObjectForest[];
    private static Sound sound;
    private static Player player;
    private static int gameState;
    public static final int worldState = 0;
    public static final int pauseState = 2;
    public static final int battleState = 3;
    public static final int buyState = 4;
    public static final int inventoryState = 5;
    public static int currentMap = 0;
    public static final int townMap = 0;
    public static final int forestMap = 1;
    public static boolean threadActive = false;
    public static ArrayList<BaseUnit> alliesUnits;
    public static ArrayList<BaseUnit> enemiesUnits;
    public GameLogic() throws IOException {
        tilemanager = new TileManager();
        fontManager = new FontManager();
        sound = new Sound();

        playMusic(0);
        setGameState(GameLogic.townMap);
        player = new Player(26, 28);
        root = new RootPane();
        scene = new Scene(root);
        root.requestFocus();

        //setupgame
     //   setTownObject();
     //   setForestObject();

        gameScreen = new GameScreen();
        gameThread = new Thread(gameScreen);

        while (!threadActive) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                //
            }
            break;
        }

    }

    // methods
    public static void setupGame() {
        stopMusic();
        if (getCurrentMap() == GameLogic.townMap){
            playMusic(1);
            tilemanager.loadMap("/map/townMap.txt");
            player.setWorldX(26);
            player.setWorldY(28);
            RenderableHolder.getTownEntities().add(player);
            RenderableHolder.setTownEntities();

        } else if (getCurrentMap() == GameLogic.forestMap){
            playMusic(2);
            tilemanager.loadMap("/map/forestMap.txt");
            player.setWorldX(30);
            player.setWorldY(36);
            RenderableHolder.getForestEntities().add(player);
            RenderableHolder.setForestEntities();
        }
        setMusicVolume(0.2);
        getRoot().getChildren().clear();
        getRoot().getChildren().add(gameScreen);
        gameScreen.requestFocus();

        setBaseObjectTown();
        setBaseObjectForest();
    }

    public static void startGameThread() {
        if (!threadActive) {
            threadActive = true;
            gameThread.start();
        }

    }

    public static void setBaseObjectTown() {
        baseObjectTown = new BaseObject[30];
        baseObjectTown[0] = new Guild(20,0,12,8,0,4,12,5);
        baseObjectTown[1] = new NormalTree(20,18.5, 4, 4, 1, 3.5, 2, 0.5);
        baseObjectTown[2] = new NormalTree(28,18.5, 4, 4, 1, 3.5, 2, 0.5);
        baseObjectTown[3] = new Statue(25.0,23.0,2,4,0,3,2,1);
        baseObjectTown[4] = new ChimneyHouse(13,18,6, 6, 0, 3.5, 6, 2.5);
        baseObjectTown[5] = new House(32,18,5,6,0,3.5,5,2.5);
        baseObjectTown[6] = new LongHouse(12,8,10.25, 6, 0, 4, 10.25, 2);
        baseObjectTown[7] = new TallHouse(32.5,4,6, 10, 0, 8, 6, 2);
        baseObjectTown[8] = new NormalTree(15,28.5, 4, 4, 1, 3.25, 2, 0.25);
        baseObjectTown[9] = new NormalTree(33,28.5, 4, 4, 1, 3.25, 2, 0.25);
        baseObjectTown[10] = new NormalTree(15,4, 4, 4, 1, 3.25, 2, 0.25);
        baseObjectTown[11] = new NormalTree(8,9, 4, 4, 1, 3.25, 2, 0.25);
        baseObjectTown[12] = new Warp(25,40);
        baseObjectTown[12].setName("WARP_TO_FOREST");
        baseObjectTown[13] = new Warp(26,40);
        baseObjectTown[13].setName("WARP_TO_FOREST");
        baseObjectTown[14] = new Warp(23,39);
        baseObjectTown[14].setName("WARP_TO_FOREST");
        baseObjectTown[15] = new Warp(24,39);
        baseObjectTown[15].setName("WARP_TO_FOREST");
        baseObjectTown[16] = new Warp(28,39);
        baseObjectTown[16].setName("WARP_TO_FOREST");
        baseObjectTown[17] = new Warp(27,39);
        baseObjectTown[17].setName("WARP_TO_FOREST");
        baseObjectTown[18] = new Merchant(17, 24, 1, 1, 0, 0.5, 1, 0.5);
        baseObjectTown[19] = new Man1Left(27.75, 25.5, 1, 1, 0, 0.5 ,1, 0.5);
        baseObjectTown[20] = new Lady(20, 30, 1, 1, 0, 0.5, 1, 0.5);
    }

    /*protected static void addNewObject(Entity entity){
        if (currentMap == townMap){
            RenderableHolder.add(RenderableHolder.townEntities,entity);
        }
        else if(currentMap == forestMap){
            RenderableHolder.getInstance().add(RenderableHolder.forestEntities,entity);
        }
    }*/

    public static void logicUpdate(){
        //System.out.println("GameLogic update called");InputUtility.getKeyPressed(KeyCode.ESCAPE)
        if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
            if (GameLogic.getGameState() == GameLogic.worldState && !GameLogic.getRoot().getChildren().contains(GameLogic.getRoot().getShopMenu())) {
                GameLogic.setGameState(GameLogic.pauseState);
                GameLogic.pauseGame();
            } /*else if (GameLogic.getGameState() == GameLogic.buyState && !GameLogic.getRoot().getChildren().contains(GameLogic.getRoot().getPauseMenu())) {
                GameLogic.setGameState(GameLogic.worldState);
                GameLogic.closeShop();
            }*/
        } else if (InputUtility.getKeyPressed(KeyCode.I)){
            if (GameLogic.getGameState() == GameLogic.worldState){
                GameLogic.setGameState(GameLogic.inventoryState);
                GameLogic.openInventory();
            }
        }
        /*if (GameLogic.getCurrentMap() == GameLogic.townMap) {
            RenderableHolder.player1.update();
        }else if(GameLogic.getCurrentMap() == GameLogic.forestMap) {
            RenderableHolder.player2.update();
        }*/
        player.updateCoordinate();
    }

    public static Player getPlayer() {
        return player;
    }

    // initialize world object
    /*public static void setTownObject() {
        player = new Player(26,28);
        addNewObject(player);
        baseObject = new BaseObject[30];
        baseObject[0] = new Guild(20,0,12,8,0,4,12,5);
        baseObject[1] = new NormalTree(20,18.5, 4, 4, 1, 3.5, 2, 0.5);
        baseObject[2] = new NormalTree(28,18.5, 4, 4, 1, 3.5, 2, 0.5);
        baseObject[3] = new Statue(25.0,23.0,2,4,0,3,2,1);
        baseObject[4] = new ChimneyHouse(13,18,6, 6, 0, 3.5, 6, 2.5);
        baseObject[5] = new House(32,18,5,6,0,3.5,5,2.5);
        baseObject[6] = new LongHouse(12,8,10.25, 6, 0, 4, 10.25, 2);
        baseObject[7] = new TallHouse(32.5,4,6, 10, 0, 8, 6, 2);
        baseObject[8] = new NormalTree(15,28.5, 4, 4, 1, 3.25, 2, 0.25);
        baseObject[9] = new NormalTree(33,28.5, 4, 4, 1, 3.25, 2, 0.25);
        baseObject[10] = new NormalTree(15,4, 4, 4, 1, 3.25, 2, 0.25);
        baseObject[11] = new NormalTree(8,9, 4, 4, 1, 3.25, 2, 0.25);
        baseObject[12] = new Warp(25,40);
        baseObject[12].setName("WARP_TO_FOREST");
        baseObject[13] = new Warp(26,40);
        baseObject[13].setName("WARP_TO_FOREST");
        baseObject[16] = new Warp(23,39);
        baseObject[16].setName("WARP_TO_FOREST");
        baseObject[17] = new Warp(24,39);
        baseObject[17].setName("WARP_TO_FOREST");
        baseObject[18] = new Warp(28,39);
        baseObject[18].setName("WARP_TO_FOREST");
        baseObject[19] = new Warp(27,39);
        baseObject[19].setName("WARP_TO_FOREST");
        baseObject[14] = new Merchant(20, 24, 1, 1, 0, 0.5, 1, 0.5);
        baseObject[15] = new Man1Left(20, 26, 1, 1, 0, 0.5 ,1, 0.5);
        baseObject[16] = new Lady(20, 30, 1, 1, 0, 0.5, 1, 0.5);

        for(int i=0 ; i < baseObject.length ;i++){
            if (baseObject[i] != null) {
                addNewObject(baseObject[i]);
            }
        }
    }*/

    public static void setBaseObjectForest() {
        baseObjectForest = new BaseObject[20];
        baseObjectForest[0] = new Warp(38, 38);
        baseObjectForest[0].setName("WARP_TO_TOWN");
        baseObjectForest[1] = new Warp(38, 39);
        baseObjectForest[1].setName("WARP_TO_TOWN");
        baseObjectForest[2] = new ForestTreeB(32, 38, 4, 4, 1.25, 3, 1.75, 0.5);
        baseObjectForest[3] = new ForestTreeB(25, 32.75, 4, 4, 1.25, 3, 1.75, 0.5);
        baseObjectForest[4] = new ForestTreeB(10.5, 27, 4, 4, 1.25, 3, 1.75, 0.5);
        baseObjectForest[5] = new DeadTreeB(23.5, 21, 4, 4, 1, 3.5, 1.5, 0.5);
        baseObjectForest[6] = new DeadTreeB(13, 23, 4, 4, 1, 3.5, 1.5, 0.5);
        baseObjectForest[7] = new DeadTreeB(10, 19, 4, 4, 1, 3.5, 1.5, 0.5);
        baseObjectForest[8] = new DeadTreeB(23.5, 13, 4, 4, 1, 3.5, 1.5, 0.5);
        baseObjectForest[9] = new DeadTreeB(13.5, 11.5, 4, 4, 1, 3.5, 1.5, 0.5);
    }

       /* for(int i=0 ; i < baseObject.length ;i++){
            if (baseObject[i] != null) {
                addNewObject(baseObject[i]);
            }
        }*/
    //}

    // collision checker
    public static void checkTile(Entity entity){
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
                tileNum1 = tilemanager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = tilemanager.mapTileNum[entityRightCol][entityTopRow];
                if(tilemanager.tile[tileNum1].collision || tilemanager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / ScreenUtil.tileSize;
                tileNum1 = tilemanager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = tilemanager.mapTileNum[entityRightCol][entityBottomRow];
                if(tilemanager.tile[tileNum1].collision || tilemanager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / ScreenUtil.tileSize;
                tileNum1 = tilemanager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = tilemanager.mapTileNum[entityLeftCol][entityBottomRow];
                if(tilemanager.tile[tileNum1].collision || tilemanager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / ScreenUtil.tileSize;
                tileNum1 = tilemanager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = tilemanager.mapTileNum[entityRightCol][entityBottomRow];
                if(tilemanager.tile[tileNum1].collision || tilemanager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public static BaseObject[] getBaseObject(){
        if (currentMap == GameLogic.townMap){
            return baseObjectTown;
        }else{
            return baseObjectForest;
        }
    }
    public static int checkObject(Entity entity,boolean player){

        int index = 999;
        for(int i = 0 ; i < getBaseObject().length ; i++){
            if( getBaseObject()[i] != null) {
                //Entity's solid area position
                entity.solidArea.setX(entity.solidArea.getX() + entity.WorldX);
                entity.solidArea.setY(entity.solidArea.getY() + entity.WorldY);
                //Object's solid area position
                getBaseObject()[i].solidArea.setX(getBaseObject()[i].solidArea.getX() + getBaseObject()[i].WorldX);
                getBaseObject()[i].solidArea.setY( getBaseObject()[i].solidArea.getY() + getBaseObject()[i].WorldY);

                switch ((entity.direction)){
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())){
                            //System.out.println("Intersect!!!");
                            if (getBaseObject()[i].isCollision()){
                                entity.collisionOn = true ;
                                //System.out.println("Collide !!!");
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())){
                            //System.out.println("Intersect!!!");
                            if ( getBaseObject()[i].isCollision()){
                                entity.collisionOn = true ;
                                //System.out.println("Collide !!!");
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())){
                            if (getBaseObject()[i].isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())){
                            if ( getBaseObject()[i].isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                }
                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);
                getBaseObject()[i].solidArea.setX(getBaseObject()[i].solidAreaDefaultX);
                getBaseObject()[i].solidArea.setY(getBaseObject()[i].solidAreaDefaultY);
            }
        }

        return index;
    }

    public static int isFront(Entity entity) {
        int isfront = 999;
        for (int i = 0; i < getBaseObject().length ; i++) {
            if (getBaseObject()[i] != null) {
                //Entity's solid area position
                entity.sprite.setX(entity.sprite.getX() + entity.WorldX);
                entity.sprite.setY(entity.sprite.getY() + entity.WorldY);
                //Object's solid area position
                getBaseObject()[i].solidArea.setX( getBaseObject()[i].solidArea.getX() + getBaseObject()[i].WorldX);
                getBaseObject()[i].solidArea.setY( getBaseObject()[i].solidArea.getY() + getBaseObject()[i].WorldY);

                switch ((entity.direction)) {
                    case "up":
                        entity.sprite.setY(entity.sprite.getY() - entity.speed);
                        if (entity.sprite.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())) {
                            if ( getBaseObject()[i].isCollision()) {
                                isfront = i;
                            }
                        }
                        break;
                    case "down":
                        entity.sprite.setY(entity.sprite.getY() + entity.speed);
                        if (entity.sprite.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())) {
                            if ( getBaseObject()[i].isCollision()) {
                                isfront = i;
                            }
                        }
                        break;
                    case "left":
                        entity.sprite.setX(entity.sprite.getX() - entity.speed);
                        if (entity.sprite.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())) {
                            if ( getBaseObject()[i].isCollision()) {
                                isfront = i;
                                ;
                            }
                        }
                        break;
                    case "right":
                        entity.sprite.setX(entity.sprite.getX() + entity.speed);
                        if (entity.sprite.intersects( getBaseObject()[i].solidArea.getBoundsInLocal())) {
                            if ( getBaseObject()[i].isCollision()) {
                                isfront = i;
                                ;
                            }
                        }
                        break;
                }

                entity.sprite.setX(0);
                entity.sprite.setY(0);
                getBaseObject()[i].solidArea.setX(getBaseObject()[i].solidAreaDefaultX);
                getBaseObject()[i].solidArea.setY(getBaseObject()[i].solidAreaDefaultY);
            }
        }
        return isfront;
    }

    //Sound
    public static void playMusic(int i){
        sound.setClip(i);
        sound.play();
        sound.loop();
    }
    public static void stopMusic(){
        sound.stop();
    }
    public static void playSE(int i){
        sound.setClip(i);
        sound.play();
    }
    public static void setMusicVolume(double i){
        sound.getClip().setVolume(i);
    }

    public static void continueGame() {
        Platform.runLater(() -> {
            synchronized (gameThread) {
                if (getRoot().getChildren().contains(getRoot().getPauseMenu())) {
                    getRoot().getChildren().remove(getRoot().getPauseMenu());
                }
            }
        });

        // gameThread.start();
        setGameState(worldState);
    }

    public static void pauseGame() {
    //    getRoot().getChildren().add(getRoot().getPauseMenu());
        Platform.runLater(() -> {
            synchronized (gameThread) {
                if (!getRoot().getChildren().contains(getRoot().getPauseMenu())) {
                    getRoot().getChildren().add(getRoot().getPauseMenu());
                }
            }
        });
        //setGameState(pauseState);
    }

    public static void openShop() {
        Platform.runLater(() -> {
            synchronized (gameThread) {
                if (!getRoot().getChildren().contains(getRoot().getShopMenu())) {
                    getRoot().getChildren().add(getRoot().getShopMenu());
                }
            }
        });
    }

    public static void openInventory() {
        Platform.runLater(() -> {
            synchronized (gameThread) {
                if (!getRoot().getChildren().contains(getRoot().getInventoryMenu())) {
                    getRoot().getChildren().add(getRoot().getInventoryMenu());
                }
            }
        });
    }

    public static void closeShop() {
        Platform.runLater(() -> {
            synchronized (gameThread) {
                if (getRoot().getChildren().contains(getRoot().getShopMenu())) {
                    getRoot().getChildren().remove(getRoot().getShopMenu());
                }
            }
        });

        setGameState(worldState);
    }

    public static void closeInventory() {
        Platform.runLater(() -> {
            synchronized (gameThread) {
                if (getRoot().getChildren().contains(getRoot().getInventoryMenu())) {
                    getRoot().getChildren().remove(getRoot().getInventoryMenu());
                }
            }
        });

        setGameState(worldState);
    }

    public static Scene getScene() {
        return scene;
    }

    public static RootPane getRoot() {
        return root;
    }

    public static Thread getGameThread() {
        return gameThread;
    }


    public static TileManager getTilemanager() {
        return tilemanager;
    }

    public static int getFPS() {
        return FPS;
    }


    public static int getGameState() {
        return gameState;
    }
    public static ArrayList<BaseUnit> getAlliessUnits(){
        return alliesUnits;
    }
    public static ArrayList<BaseUnit> getEnemiesUnits(){
        return enemiesUnits;
    }
    public static FontManager getFontManager() {
        return fontManager;
    }

    public static int getCurrentMap() {
        return currentMap;
    }

    public static void setGameState(int state) {
        gameState = state;
    }

    public static void setCurrentMap(int map) {
        currentMap = map;
    }

}
