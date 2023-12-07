package logic;

import display.GameScreen;
import display.ScreenUtil;
import interfaces.IRenderable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;
import sound.Sound;
import tile.TileManager;
import worldObject.*;
import worldObject.buildings.*;
import worldObject.tree.LightTree;
import worldObject.tree.NormalTree;

public class GameLogic {
    //private static ArrayList<IRenderable> gameObjectContainer;
    private static StackPane root;
    private static Scene scene;
    private static GameScreen gameScreen;
    private static Thread gameThread;
    private static int FPS = 50;
    private static Player player;
    private static TileManager tilemanager;
    private static BaseObject baseObject[];
    private static Sound sound;
    private static int gameState;
    public static final int worldState = 0;
    public static final int battleState = 1;
    public static final int pauseState = 2;


    public GameLogic() {
        //this.gameObjectContainer = new ArrayList<IRenderable>();
        root = new StackPane();
        scene = new Scene(root);

        gameScreen = new GameScreen();

        player = new Player();
        addNewObject(player);
        tilemanager = new TileManager();

        baseObject = new BaseObject[20];

        sound = new Sound();

        getRoot().getChildren().add(gameScreen);
        gameScreen.requestFocus();

        setupGame();
        startGameThread();
    }

    // methods
    public void setupGame() {
        setObject();
        playMusic(1);
        setMusicVolume(0.2);
        setGameState(worldState);
    }

    public void startGameThread() {
        gameThread = new Thread(gameScreen);
        gameThread.start();
    }

    protected void addNewObject(IRenderable entity){
        //gameObjectContainer.add(entity);
        RenderableHolder.getInstance().add(RenderableHolder.townEntities,entity);
    }

    public static void logicUpdate(){
        //System.out.println("GameLogic update called");
        if (gameState == worldState) {
            player.update();
        }
        if (gameState == pauseState) {

        }
    }



    // initialize world object
    public void setObject() {
        baseObject[2] = new House(27, 18,6,6,0,3,6,3);
        baseObject[3] = new NormalTree(18, 20, 4, 4, 1, 3.5, 2, 0.5);
        baseObject[4] = new LightTree(13, 20, 4, 4, 1.5, 3, 1, 0.5);
        baseObject[5] = new ChimneyHouse(34, 18, 6, 6, 0, 3, 6, 3);
        baseObject[6] = new LongHouse(41, 18, 10.25, 6, 0, 3, 6, 3);
        baseObject[7] = new TallHouse(18, 27, 6, 10, 0, 3, 6, 3);
        baseObject[8] = new Guild(18, 9, 10.25, 10, 0, 7, 10.25, 3);
        baseObject[9] = new NormalTree(18, 50, 7, 7, 2, 3.5, 2, 0.5);

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
    public void playMusic(int i){
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
    public static Scene getScene() {
        return scene;
    }

    public static StackPane getRoot() {
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

/*    public static ArrayList<IRenderable> getGameObjectContainer() {
        return gameObjectContainer;
    }*/

    public static void setGameState(int state) {
        gameState = state;
    }


}