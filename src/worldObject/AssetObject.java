package worldObject;

import display.GameScreen;

public class AssetObject {
    GameScreen gameScreen;

    public AssetObject(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void setObject() {
        gameScreen.object[0] = new Key(25 * gameScreen.tileSize,25 * gameScreen.tileSize);
        gameScreen.object[1] = new Key(25 * gameScreen.tileSize, 35 * gameScreen.tileSize);

    }

}
