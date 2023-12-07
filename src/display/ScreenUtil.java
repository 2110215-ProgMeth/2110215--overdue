package display;

public class ScreenUtil {
    // SCREEN SETTINGS
    static final int originalTileSize = 16; // 16 x 16 tile
    public static final int scale = 3;
    public static final int tileSize = originalTileSize * scale; // 48 x 48 tiles
    public static final int maxScreenCol = 20; // defalut 16
    public static final int maxScreenRow = 15; // default 12
    public static final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public static final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTING
    public static final int maxWorldCol = 100;
    public static final int maxWorldRow = 100;
    public static final int worldWidth = tileSize * maxWorldCol;
    public static final int worldHeight = tileSize * maxWorldRow;
    public static int FPS;


}
