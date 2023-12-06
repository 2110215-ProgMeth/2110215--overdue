package display;

import sound.Sound;
import tile.TileManager;
import worldObject.AssetObject;
import worldObject.BaseObject;
import worldObject.CollisionChecker;
import worldObject.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import control.InputUtility;
import logic.GameLogic;

public class GameScreen extends Canvas implements Runnable{
    Thread gameThread;
    int FPS = 55;

    GameLogic logic = new GameLogic();
    public Player player = new Player(this);
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public BaseObject object[] = new BaseObject[10];
    public AssetObject assetObject = new AssetObject(this);
    Sound sound = new Sound();
    Sound se = new Sound();

    // constructor
    public GameScreen() {
        super();
        this.setWidth(ScreenUtil.screenWidth);
        this.setHeight(ScreenUtil.screenHeight);
        this.setVisible(true);
        this.requestFocus();
        this.setFocused(true);

        addListerner();

    }
    public void addListerner() {
        this.setOnKeyPressed((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), true);
        });

        this.setOnKeyReleased((KeyEvent event) -> {
            InputUtility.setKeyPressed(event.getCode(), false);
        });

        this.setOnMousePressed((MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY)
                InputUtility.mouseLeftDown();
        });

        this.setOnMouseReleased((MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY)
                InputUtility.mouseLeftRelease();
        });

        this.setOnMouseEntered((MouseEvent event) -> {
            InputUtility.mouseOnScreen = true;
        });

        this.setOnMouseExited((MouseEvent event) -> {
            InputUtility.mouseOnScreen = false;
        });

        this.setOnMouseMoved((MouseEvent event) -> {
            if (InputUtility.mouseOnScreen) {
                InputUtility.mouseX = event.getX();
                InputUtility.mouseY = event.getY();
            }
        });

        this.setOnMouseDragged((MouseEvent event) -> {
            if (InputUtility.mouseOnScreen) {
                InputUtility.mouseX = event.getX();
                InputUtility.mouseY = event.getY();
            }
        });
    }

    // methods
    public void paintComponent() {  // method for drawing on our canvas
        //System.out.println("paintComponent update called");
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0,0,ScreenUtil.screenWidth,ScreenUtil.screenHeight);
        gc.setFill(Color.WHITE);

        // TILE
        tileM.createMap(gc);

        // OBJECT
        for (int i = 0; i < object.length; i++ ) {
            if (object[i] != null) {
                object[i].draw(gc, this);
            }
        }
        // PLAYER
        player.draw(gc);

        /*for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
        //            if (!entity.isDestroyed()) {
        //                entity.draw(gc);
        //            }
        //        }*/
        //        //*** BELOW WILL BE DELETED LATER***//
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame() {
        assetObject.setObject();
        playMusic(1);
        setMusicVolume(0.2);
    }

    public void update() {
        player.update();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                update();
                paintComponent();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void playMusic(int i){
            sound.setClip(i);
            sound.play();
            sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setClip(i);
        sound.play();
    }
    public void setMusicVolume(double i){
        sound.getClip().setVolume(i);
    }

}
