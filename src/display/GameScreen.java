package display;

import interfaces.IRenderable;
import sharedObject.RenderableHolder;
import sound.Sound;
import tile.TileManager;
/*import worldObject.AssetObject;*/
import worldObject.BaseObject;
/*import worldObject.CollisionChecker;*/
import worldObject.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import control.InputUtility;
import logic.GameLogic;

public class GameScreen extends Canvas implements Runnable {

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

        // TILE
        GameLogic.getTilemanager().createMap(gc);

        // OBJECT
        /*for (int i = 0; i < GameLogic.getBaseObject().length; i++ ) {
            if (GameLogic.getBaseObject()[i] != null) {
                GameLogic.getBaseObject()[i].draw(gc);
            }
        }

        // PLAYER
        GameLogic.getPlayer().draw(gc);*/

        for (IRenderable entity : RenderableHolder.townEntities){
            if (!entity.isDestroyed()) {
                entity.draw(gc);
            }
        }

    }


    @Override
    public void run() {
        double drawInterval = 1000000000/GameLogic.getFPS();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(GameLogic.getGameThread() != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                GameLogic.logicUpdate();
                switch(GameLogic.getGameState()){
                    case GameLogic.worldState:
                        break;
                    case GameLogic.pauseState:
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                }
                RenderableHolder.update(RenderableHolder.townEntities);
                paintComponent();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }

           // System.out.println(GameLogic.getRoot().getChildren().contains(GameLogic.getRoot().getPauseMenu()));

        }

    }

}
