package logic;
import java.util.ArrayList;
import java.util.List;

import display.GameScreen;
import display.ScreenUtil;
import interfaces.IRenderable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;
import sound.Sound;
import tile.TileManager;
import tile.TileManager;
import worldObject.*;

public class GameLogic {
    private List<IRenderable> gameObjectContainer;
    private static StackPane root;
    private static Scene scene;
    private static GameScreen gameScreen;
    private static Thread gameThread;
    private static int FPS = 55;
    private static Player player;
    private static TileManager tilemanager;
    private static BaseObject baseObject[];
    public static Sound sound;

    public GameLogic() {
        // this.gameObjectContainer = new ArrayList<IRenderable>();
        root = new StackPane();
        scene = new Scene(root);

        gameScreen = new GameScreen();

        player = new Player();

        tilemanager = new TileManager();

        baseObject = new BaseObject[10];

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
    }

    public void startGameThread() {
        gameThread = new Thread(gameScreen);
        gameThread.start();
    }

    protected void addNewObject(IRenderable entity){
        gameObjectContainer.add(entity);
        RenderableHolder.getInstance().add(entity);
    }

    public static void logicUpdate(){
        //System.out.println("GameLogic update called");
        player.update();
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

    // initialize world object
    public void setObject() {
        baseObject[0] = new Key(25 * ScreenUtil.tileSize,25 * ScreenUtil.tileSize);
        baseObject[1] = new Key(25 * ScreenUtil.tileSize, 35 * ScreenUtil.tileSize);
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
}
