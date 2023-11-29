package display;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;

public class GamePanel extends Canvas implements Runnable {
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48 x 48 tiles
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    Thread gameThread;

    //*** BELOW WILL BE DELETED LATER ***//

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // *** ABOVE WILL BE DELETED LATER ***//

    // constructor
    public GamePanel() {
        super();
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setVisible(true);
        this.requestFocus();
        this.setFocused(true);
    }

    // methods
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        while (gameThread != null) {
            update();
            paintComponent();
        }
    }

    public void update() {


    }

    public void paintComponent() {  // method for drawing on our canvas
        GraphicsContext gc = this.getGraphicsContext2D();
//        gc.setFill(Color.BLACK);
//        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
//            if (entity.isVisible() && !entity.isDestroyed()) {
//                entity.draw(gc);
//            }
//        }

        //*** BELOW WILL BE DELETED LATER***//

        gc.setFill(Color.BLACK);
        gc.fillRect(playerX, playerY, tileSize, tileSize);
    }




}
