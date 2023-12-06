package Tile;

import display.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public Tile[] tile;
    GameScreen gp;
    public int mapTileNum[][];
    public TileManager(GameScreen gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        tile[0] = new Tile();
        tile[0].setImage(new Image("/Tile/grass00.png"));
        tile[1] = new Tile();
        tile[1].setImage(new Image("/Tile/grass01.png"));
        tile[2] = new Tile();
        tile[2].setImage(new Image("/Tile/Tree.png"));
        tile[2].setCollision(true);
        tile[3] = new Tile();
        tile[3].setImage(new Image("/Tile/Water01.png"));
        tile[3].setCollision(true);
        loadMap();
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/Map/WorldV1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line  = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
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

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.WorldX + gp.player.screenX;
            int screenY = worldY - gp.player.WorldY + gp.player.screenY;
            if (worldX + gp.tileSize > gp.player.WorldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.WorldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.WorldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.WorldY + gp.player.screenY){
                gc.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize);
            }

            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }


    }
}
