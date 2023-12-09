package logic;

import control.InputUtility;
import display.GameScreen;
import display.ScreenUtil;
import font.FontManager;
import gui.menu.RootPane;
import interfaces.IRenderable;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;
import sound.Sound;
import tile.TileManager;
import worldObject.*;
import worldObject.buildings.*;
import worldObject.forest.*;

import java.io.IOException;

public class GameLogic {
    //private static ArrayList<IRenderable> gameObjectContainer;
    private static RootPane root;
    private static Scene scene;
    private static GameScreen gameScreen;
    private static Thread gameThread;
    private static int FPS = 45;
    private static Player player;
    private static TileManager tilemanager;
    private static FontManager fontManager;
    private static BaseObject baseObject[];
    private static Sound sound;
    private static int gameState;
    public static final int worldState = 0;
    public static final int battleState = 1;
    public static final int pauseState = 2;



    public GameLogic() throws IOException {
        //this.gameObjectContainer = new ArrayList<IRenderable>();
 /*       root = new RootPane();
        scene = new Scene(root);*/
 /*       gameScreen = new GameScreen();*/

        player = new Player();
        addNewObject(player);
        tilemanager = new TileManager();
        fontManager = new FontManager();

        baseObject = new BaseObject[20];

        sound = new Sound();
        playMusic(0);

        root = new RootPane();
        scene = new Scene(root);
        root.requestFocus();

  /*      gameScreen = new GameScreen();
        getRoot().getChildren().add(gameScreen);
        gameScreen.requestFocus();*/

/*        setupGame();
        startGameThread();*/
    }

    // methods
    public static void setupGame() {
        stopMusic();
        setTownObject();
        playMusic(1);
        setMusicVolume(0.2);
        setGameState(worldState);

        getRoot().getChildren().clear();
        gameScreen = new GameScreen();
        getRoot().getChildren().add(gameScreen);
        gameScreen.requestFocus();
    }

    public static void startGameThread() {
        gameThread = new Thread(gameScreen);
        gameThread.start();

        /*gameThread = new Thread(() -> {
            gameScreen.paintComponent();
            player.update();
        });
        gameThread.start();*/


    }

    protected static void addNewObject(IRenderable entity){
        //gameObjectContainer.add(entity);
        RenderableHolder.getInstance().add(RenderableHolder.townEntities,entity);
    }

    public static void logicUpdate(){
        //System.out.println("GameLogic update called");
        if (InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
            if (GameLogic.getGameState() == GameLogic.worldState) {
                GameLogic.setGameState(GameLogic.pauseState);
                GameLogic.pauseGame();
            } else if (GameLogic.getGameState() == GameLogic.pauseState) {
                GameLogic.setGameState(GameLogic.worldState);
            }
        }
        player.update();
    }



    // initialize world object
    public static void setTownObject() {
        baseObject[2] = new House(27, 18,6,6,0,3,6,3);
        baseObject[5] = new ChimneyHouse(34, 18, 6, 6, 0, 3, 6, 3);
        baseObject[6] = new LongHouse(41, 18, 10.25, 6, 0, 3, 6, 3);
        baseObject[7] = new TallHouse(18, 27, 6, 10, 0, 3, 6, 3);
        baseObject[8] = new Guild(18, 9, 10.25, 10, 0, 7, 10.25, 3);
        baseObject[9] = new NormalTree(18, 50, 7, 7, 2, 3.5, 2, 0.5);
        baseObject[10] = new Statue(25.0,23.0,2,4,0,3,2,1);

        for(int i=0 ; i < baseObject.length ;i++){
            if (baseObject[i] != null) {
                addNewObject(baseObject[i]);
            }
        }
    }

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

    public static int checkObject(Entity entity,boolean player){
        int index = 999;
        for(int i = 0 ; i < baseObject.length ; i++){
            if(baseObject[i] != null){
                //Entity's solid area position
                entity.solidArea.setX(entity.solidArea.getX() + entity.WorldX);
                entity.solidArea.setY(entity.solidArea.getY() + entity.WorldY);
                //Object's solid area position
                baseObject[i].solidArea.setX(baseObject[i].solidArea.getX() + baseObject[i].WorldX);
                baseObject[i].solidArea.setY(baseObject[i].solidArea.getY() + baseObject[i].WorldY);

                switch ((entity.direction)){
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.intersects(baseObject[i].solidArea.getBoundsInLocal())){
                            if (baseObject[i].isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.intersects(baseObject[i].solidArea.getBoundsInLocal())){
                            if (baseObject[i].isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.intersects(baseObject[i].solidArea.getBoundsInLocal())){
                            if (baseObject[i].isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.intersects(baseObject[i].solidArea.getBoundsInLocal())){
                            if (baseObject[i].isCollision()){
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
                baseObject[i].solidArea.setX(baseObject[i].solidAreaDefaultX);
                baseObject[i].solidArea.setY(baseObject[i].solidAreaDefaultY);
            }
        }

        return index;
    }

    public static int isFront(Entity entity) {
        int isfront = 999;
        for (int i = 0; i < baseObject.length; i++) {
            if (baseObject[i] != null) {
                //Entity's solid area position
                entity.sprite.setX(entity.sprite.getX() + entity.WorldX);
                entity.sprite.setY(entity.sprite.getY() + entity.WorldY);
                //Object's solid area position
                baseObject[i].solidArea.setX(baseObject[i].solidArea.getX() + baseObject[i].WorldX);
                baseObject[i].solidArea.setY(baseObject[i].solidArea.getY() + baseObject[i].WorldY);

                switch ((entity.direction)) {
                    case "up":
                        entity.sprite.setY(entity.sprite.getY() - entity.speed);
                        if (entity.sprite.intersects(baseObject[i].solidArea.getBoundsInLocal())) {
                            if (baseObject[i].isCollision()) {
                                isfront = i;
                            }
                        }
                        break;
                    case "down":
                        entity.sprite.setY(entity.sprite.getY() + entity.speed);
                        if (entity.sprite.intersects(baseObject[i].solidArea.getBoundsInLocal())) {
                            if (baseObject[i].isCollision()) {
                                isfront = i;
                            }
                        }
                        break;
                    case "left":
                        entity.sprite.setX(entity.sprite.getX() - entity.speed);
                        if (entity.sprite.intersects(baseObject[i].solidArea.getBoundsInLocal())) {
                            if (baseObject[i].isCollision()) {
                                isfront = i;
                                ;
                            }
                        }
                        break;
                    case "right":
                        entity.sprite.setX(entity.sprite.getX() + entity.speed);
                        if (entity.sprite.intersects(baseObject[i].solidArea.getBoundsInLocal())) {
                            if (baseObject[i].isCollision()) {
                                isfront = i;
                                ;
                            }
                        }
                        break;
                }

                entity.sprite.setX(0);
                entity.sprite.setY(0);
                baseObject[i].solidArea.setX(baseObject[i].solidAreaDefaultX);
                baseObject[i].solidArea.setY(baseObject[i].solidAreaDefaultY);
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
            if (getRoot().getChildren().contains(getRoot().getPauseMenu())) {
                getRoot().getChildren().remove(getRoot().getPauseMenu());
            }
        });

        // gameThread.start();
        setGameState(worldState);
    }

    public static void pauseGame() {
    //    getRoot().getChildren().add(getRoot().getPauseMenu());
        Platform.runLater(() -> {
            if (!getRoot().getChildren().contains(getRoot().getPauseMenu())); {
                getRoot().getChildren().add(getRoot().getPauseMenu());
            }
        });
        setGameState(pauseState);
       /* Platform.runLater(() -> {
            try {
                synchronized (gameThread) {
                    // Check if the game is paused
                    while (gameState == pauseState) {
                        gameThread.wait(); // Wait until notified
                    }
                }

                // Game logic processing goes here

                // Adjust the sleep time to control the frame rate
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameThread.start();
        });*/

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

    public static Player getPlayer() {
        return player;
    }

    public static TileManager getTilemanager() {
        return tilemanager;
    }

    public static int getFPS() {
        return FPS;
    }

    public static BaseObject[] getBaseObject() {
        return baseObject;
    }

    public static int getGameState() {
        return gameState;
    }

    public static FontManager getFontManager() {
        return fontManager;
    }

/*    public static ArrayList<IRenderable> getGameObjectContainer() {
        return gameObjectContainer;
    }*/

    public static void setGameState(int state) {
        gameState = state;
    }


}
