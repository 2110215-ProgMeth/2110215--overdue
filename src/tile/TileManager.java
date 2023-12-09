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
        tile = new Tile[50];
        mapTileNum = new int[ScreenUtil.maxWorldCol][ScreenUtil.maxWorldRow];
        setTile(0,"earth");
        setTile(1,"grass00");
        setTile(2,"grass01");
        setTile(3,"road00");
        setTile(4,"road01");
        setTile(5,"road02");
        setTile(6,"road03");
        setTile(7,"road04");
        setTile(8,"road05");
        setTile(9,"road06");
        setTile(10,"road07");
        setTile(11,"road08");
        setTile(12,"road09");
        setTile(13,"road10");
        setTile(14,"road11");
        setTile(15,"road12");
        setTile(16,"rock00");
        setTile(17,"rock01");
        setTile(18,"rock02");
        setTile(19,"rock03");
        setTile(20,"rock04");
        setTile(21,"rock05");
        setTile(22,"rock06");
        setTile(23,"rock07");
        setTile(24,"rock08");
        setTile(25,"rock09");
        setTile(26,"rock10");
        setTile(27,"rock11");
        setTile(28,"rock12");
        setTile(29,"rock13");
        setTile(30,"stair");
        setTile(31,"wall");
        tile[31].setCollision(true);
        setTile(32,"water00");
        tile[32].setCollision(true);
        setTile(33,"water01");
        tile[33].setCollision(true);
        setTile(34,"water02");
        setTile(35,"water03");
        setTile(36,"water04");
        setTile(37,"water05");
        setTile(38,"water06");
        setTile(39,"water07");
        setTile(40,"water08");
        setTile(41,"water09");
        setTile(42,"water10");
        setTile(43,"water11");
        setTile(44,"water12");
        setTile(45,"water13");
        setTile(46,"rock05");
        tile[46].setCollision(true);
        loadMap("/map/jungleMap.txt");
    }
    public void setTile(int index,String name){
        tile[index] = new Tile();
        Image image = new Image("/tile/" + name + ".png");
        tile[index].setImage(image);
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
                if (tile[tileNum] != null && tile[tileNum].image != null) {
                    gc.drawImage(tile[tileNum].image, screenX, screenY, ScreenUtil.tileSize, ScreenUtil.tileSize);
                }
            }

            worldCol++;


            if(worldCol == ScreenUtil.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }


    }
}
