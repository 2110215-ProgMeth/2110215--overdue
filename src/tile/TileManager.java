package tile;

import display.GameScreen;
import display.ScreenUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.GameLogic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager(){
        tile = new Tile[10];
        mapTileNum = new int[ScreenUtil.maxWorldCol][ScreenUtil.maxWorldRow];
        Image i = new Image("/tile/trippleTile.jpg");
        tile[0] = new Tile();
        tile[0].setImage(new WritableImage(i.getPixelReader(), 400, 40, 319, 319)); // ground
        tile[1] = new Tile(); // x1 = 40; y1 = 40, x2 = 360, y2 = 360
        tile[1].setImage(new WritableImage(i.getPixelReader(), 40, 40, 319, 319)); // grass
        tile[2] = new Tile();
        tile[2].setImage(new WritableImage(i.getPixelReader(), 760, 40, 319, 319)); // stone
        tile[2].setCollision(true);
        tile[3] = new Tile();
        tile[3].setImage(new Image("/tile/Water01.png"));
        tile[3].setCollision(true);
        loadMap("/map/townMap.txt");
    }
    public void loadMap(String filename){
        try{
            InputStream is = getClass().getResourceAsStream(filename);
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
            int screenX = worldX - GameLogic.getPlayer().WorldX + GameLogic.getPlayer().screenX;
            int screenY = worldY - GameLogic.getPlayer().WorldY + GameLogic.getPlayer().screenY;
            if (worldX + ScreenUtil.tileSize > GameLogic.getPlayer().WorldX - GameLogic.getPlayer().screenX &&
                worldX - ScreenUtil.tileSize < GameLogic.getPlayer().WorldX + GameLogic.getPlayer().screenX &&
                worldY + ScreenUtil.tileSize > GameLogic.getPlayer().WorldY - GameLogic.getPlayer().screenY &&
                worldY - ScreenUtil.tileSize < GameLogic.getPlayer().WorldY + GameLogic.getPlayer().screenY){
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
