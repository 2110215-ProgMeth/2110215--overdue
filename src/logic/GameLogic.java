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
import worldObject.npc.Lady;
import worldObject.npc.Man1Left;
import worldObject.npc.Merchant;
import java.util.List;
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
    private static BaseObject baseObject[];
    private static Sound sound;
    private static Player player;
    private static int gameState;
    public static final int worldState = 0;
    public static final int pauseState = 2;
    public static final int battleState = 3;
    public static int currentMap = 0;
    public static final int townMap = 0;
    public static final int forestMap = 1;
    public static boolean threadActive = false;

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
            player.setWorldX(40);
            player.setWorldY(45);
            RenderableHolder.getForestEntities().add(player);
            RenderableHolder.setForestEntities();
        }
        setMusicVolume(0.2);
        getRoot().getChildren().clear();
        getRoot().getChildren().add(gameScreen);
        gameScreen.requestFocus();
    }

    public static void startGameThread() {
        if (!threadActive) {
            threadActive = true;
            gameThread.start();
        }

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
            if (GameLogic.getGameState() == GameLogic.worldState) {
                GameLogic.setGameState(GameLogic.pauseState);
                GameLogic.pauseGame();
            }
        }
        /*if (GameLogic.getCurrentMap() == GameLogic.townMap) {
            RenderableHolder.player1.update();
        }else if(GameLogic.getCurrentMap() == GameLogic.forestMap) {
            RenderableHolder.player2.update();
        }*/
        player.update();
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
    /*public static void setForestObject(){
        baseObject = new BaseObject[20];
        baseObject[0] = new Warp(38,38);
        baseObject[0].setName("WARP_TO_TOWN");
        baseObject[1] = new Warp(38,39);
        baseObject[1].setName("WARP_TO_TOWN");
        baseObject[2] = new ForestTreeB(32,38,4, 4, 1.25, 3, 1.75, 0.5);
        //baseObject[1] = new ForestTreeS(34,39,2, 2, 0.5, 1.5, 1, 0.25);
        baseObject[3] = new ForestTreeB(25,32.75,4, 4, 1.25, 3, 1.75, 0.5);
        baseObject[4] = new ForestTreeB(10.5,27,4, 4, 1.25, 3, 1.75, 0.5);
        baseObject[5] = new DeadTreeB(23.5,21,4, 4, 1, 3.5, 1.5, 0.5);
        baseObject[6] = new DeadTreeB(13,23,4, 4, 1, 3.5, 1.5, 0.5);
        baseObject[7] = new DeadTreeB(10,19,4, 4, 1, 3.5, 1.5, 0.5);
        baseObject[8] = new DeadTreeB(23.5,13,4, 4, 1, 3.5, 1.5, 0.5);
        baseObject[9] = new DeadTreeB(13.5,11.5,4, 4, 1, 3.5, 1.5, 0.5);


        for(int i=0 ; i < baseObject.length ;i++){
            if (baseObject[i] != null) {
                addNewObject(baseObject[i]);
            }
        }
    }*/

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

    public List<IRenderable> mapList(){
        if (currentMap == GameLogic.townMap){
            return RenderableHolder.getTownEntities();
        }else{
            return RenderableHolder.getForestEntities();
        }
    }

    public static int checkObject(Entity entity,boolean player){
        List<IRenderable> holder = RenderableHolder.getTownEntities();;
        if (currentMap == GameLogic.townMap){
             holder = RenderableHolder.getTownEntities();
        }else{
            holder = RenderableHolder.getForestEntities();
        }
        int index = 999;
        for(int i = 0 ; i < holder.size() ; i++){
            if(!(holder.get(i) instanceof Player) && (BaseObject) holder.get(i) != null) {
                //Entity's solid area position
                entity.solidArea.setX(entity.solidArea.getX() + entity.WorldX);
                entity.solidArea.setY(entity.solidArea.getY() + entity.WorldY);
                //Object's solid area position
                ((BaseObject) holder.get(i)).solidArea.setX(((BaseObject) holder.get(i)).solidArea.getX() + ((BaseObject) holder.get(i)).WorldX);
                ((BaseObject) holder.get(i)).solidArea.setY(((BaseObject) holder.get(i)).solidArea.getY() + ((BaseObject) holder.get(i)).WorldY);

                switch ((entity.direction)){
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())){
                            if (holder.get(i).isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())){
                            if (holder.get(i).isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())){
                            if (holder.get(i).isCollision()){
                                entity.collisionOn = true ;
                            }
                            if(player){
                                index = i ;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())){
                            if (holder.get(i).isCollision()){
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
                ((BaseObject) holder.get(i)).solidArea.setX(((BaseObject) holder.get(i)).solidAreaDefaultX);
                ((BaseObject) holder.get(i)).solidArea.setY(((BaseObject) holder.get(i)).solidAreaDefaultY);
            }
        }

        return index;
    }

    public static int isFront(Entity entity) {
        List<IRenderable> holder;
        if (currentMap == GameLogic.townMap) {
            holder = RenderableHolder.getTownEntities();
        }else {
            holder = RenderableHolder.getForestEntities();
        }
        int isfront = 999;
        for (int i = 0; i < holder.size(); i++) {
            if (!(holder.get(i) instanceof Player) && holder.get(i) != null) {
                //Entity's solid area position
                entity.sprite.setX(entity.sprite.getX() + entity.WorldX);
                entity.sprite.setY(entity.sprite.getY() + entity.WorldY);
                //Object's solid area position
                ((BaseObject) holder.get(i)).solidArea.setX(((BaseObject) holder.get(i)).solidArea.getX() + ((BaseObject) holder.get(i)).WorldX);
                ((BaseObject) holder.get(i)).solidArea.setY(((BaseObject) holder.get(i)).solidArea.getY() + ((BaseObject) holder.get(i)).WorldY);

                switch ((entity.direction)) {
                    case "up":
                        entity.sprite.setY(entity.sprite.getY() - entity.speed);
                        if (entity.sprite.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())) {
                            if (holder.get(i).isCollision()) {
                                isfront = i;
                            }
                        }
                        break;
                    case "down":
                        entity.sprite.setY(entity.sprite.getY() + entity.speed);
                        if (entity.sprite.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())) {
                            if (holder.get(i).isCollision()) {
                                isfront = i;
                            }
                        }
                        break;
                    case "left":
                        entity.sprite.setX(entity.sprite.getX() - entity.speed);
                        if (entity.sprite.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())) {
                            if (holder.get(i).isCollision()) {
                                isfront = i;
                                ;
                            }
                        }
                        break;
                    case "right":
                        entity.sprite.setX(entity.sprite.getX() + entity.speed);
                        if (entity.sprite.intersects(((BaseObject) holder.get(i)).solidArea.getBoundsInLocal())) {
                            if (holder.get(i).isCollision()) {
                                isfront = i;
                                ;
                            }
                        }
                        break;
                }

                entity.sprite.setX(0);
                entity.sprite.setY(0);
                ((BaseObject) holder.get(i)).solidArea.setX(((BaseObject) holder.get(i)).solidAreaDefaultX);
                ((BaseObject) holder.get(i)).solidArea.setY(((BaseObject) holder.get(i)).solidAreaDefaultY);
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

    public static BaseObject[] getBaseObject() {
        return baseObject;
    }

    public static int getGameState() {
        return gameState;
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
