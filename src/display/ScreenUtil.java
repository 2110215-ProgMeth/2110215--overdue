package display;

public class ScreenUtil {
    // SCREEN SETTINGS
    static final int originalTileSize = 16; // 16 x 16 tile
    public static int scale = 3; // defalut 3
    public static final int tileSize = originalTileSize * scale; // 48 x 48 tiles
    public static final int maxScreenCol = 23; // defalut 16 // new 23
    public static final int maxScreenRow = 13; // default 12 // new 13
    public static final int screenWidth = tileSize * maxScreenCol; // 1104 pixels
    public static final int screenHeight = tileSize * maxScreenRow; // 624 pixels

    // WORLD SETTING
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;
    public static final int worldWidth = tileSize * maxWorldCol;
    public static final int worldHeight = tileSize * maxWorldRow;
    public static int FPS = 45;

    public static int getFPS() {
        return FPS;
    }


}
