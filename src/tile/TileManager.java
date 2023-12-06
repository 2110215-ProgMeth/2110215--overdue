package tile;

import display.GameScreen;
import display.ScreenUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public Tile[] tile;
    GameScreen gameScreen;
    public int mapTileNum[][];
    public TileManager(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        tile = new Tile[10];
        mapTileNum = new int[ScreenUtil.maxWorldCol][ScreenUtil.maxWorldRow];
        tile[0] = new Tile();
        tile[0].setImage(new Image("/tile/grass00.png"));
        tile[1] = new Tile();
        tile[1].setImage(new Image("/tile/grass01.png"));
        tile[2] = new Tile();
        tile[2].setImage(new Image("/tile/Tree.png"));
        tile[2].setCollision(true);
        tile[3] = new Tile();
        tile[3].setImage(new Image("/tile/Water01.png"));
        tile[3].setCollision(true);
        loadMap();
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/map/WorldV1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < ScreenUtil.maxWorldCol && row < ScreenUtil.maxWorldRow){
                String line  = br.readLine();

                while(col < ScreenUtil.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == ScreenUtil.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }
    public void createMap(GraphicsContext gc){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < ScreenUtil.maxWorldCol && worldRow < ScreenUtil.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * ScreenUtil.tileSize;
            int worldY = worldRow * ScreenUtil.tileSize;
            int screenX = worldX - gameScreen.player.WorldX + gameScreen.player.screenX;
            int screenY = worldY - gameScreen.player.WorldY + gameScreen.player.screenY;
            if (worldX + ScreenUtil.tileSize > gameScreen.player.WorldX - gameScreen.player.screenX &&
                worldX - ScreenUtil.tileSize < gameScreen.player.WorldX + gameScreen.player.screenX &&
                worldY + ScreenUtil.tileSize > gameScreen.player.WorldY - gameScreen.player.screenY &&
                worldY - ScreenUtil.tileSize < gameScreen.player.WorldY + gameScreen.player.screenY){
                gc.drawImage(tile[tileNum].image ,screenX ,screenY ,ScreenUtil.tileSize ,ScreenUtil.tileSize);
            }

            worldCol++;


            if(worldCol == ScreenUtil.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }


    }
}
