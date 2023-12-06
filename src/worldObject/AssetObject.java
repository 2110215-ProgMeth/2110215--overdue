package worldObject;

import display.GameScreen;
import display.ScreenUtil;

public class AssetObject {
    GameScreen gameScreen;

    public AssetObject(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void setObject() {
        gameScreen.object[0] = new Key(25 * ScreenUtil.tileSize,25 * ScreenUtil.tileSize);
        gameScreen.object[1] = new Key(25 * ScreenUtil.tileSize, 35 * ScreenUtil.tileSize);

    }

}
