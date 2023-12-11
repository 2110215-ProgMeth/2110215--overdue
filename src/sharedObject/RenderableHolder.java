package sharedObject;

import interfaces.IRenderable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import logic.GameLogic;
import worldObject.BaseObject;
import worldObject.Player;
import worldObject.Warp;
import worldObject.buildings.*;
import worldObject.forest.DeadTreeB;
import worldObject.forest.ForestTreeB;
import worldObject.forest.NormalTree;
import worldObject.npc.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();
    public static AudioClip hoverSound;
    public static AudioClip confirmSound;
    public static AudioClip declineSound;
    public static ImageView mainMenuBackground;
    public static ImageView darkWojak;
    public static ImageView battleBackground;
    public static Image unitImage;
    public static ArrayList<IRenderable> townEntities;
    public static ArrayList<IRenderable> battleEntities;
    public static ArrayList<IRenderable> forestEntities;
 /*   public static Player player1;
    public static Player player2;*/
    private static Comparator<IRenderable> comparator;
    static {
        loadSound();
        loadImage();
    }

    // constructor
    public RenderableHolder() {
        townEntities = new ArrayList<IRenderable>();
        battleEntities = new ArrayList<IRenderable>();
        forestEntities = new ArrayList<IRenderable>();
        comparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getZ() > o2.getZ()) return 1;
            return -1;
        };
    }

    // methods
    public static void add(List<IRenderable> entities ,IRenderable entity) {
        entities.add(entity);
        // Sort our list by Z
        Collections.sort(entities, comparator);
    }

    public static RenderableHolder getInstance() {
        return instance;
    }

    public static String getPath(String url) {
        return ClassLoader.getSystemResource(url).toString();
    }
    

    public static void update(List<IRenderable> entities) {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
    }

    public static void loadSound() {
        hoverSound = new AudioClip(getPath("effect/001_Hover_01.wav"));
        hoverSound.setVolume(0.4);
        confirmSound = new AudioClip(getPath("effect/013_Confirm_03.wav"));
        confirmSound.setVolume(0.4);
        declineSound = new AudioClip(getPath("effect/029_Decline_09.wav"));
        declineSound.setVolume(0.4);
    }

    public static void loadImage() {
        mainMenuBackground = new ImageView(new Image(getPath("menuImage/gameCover.png")));
        darkWojak = new ImageView(new Image(getPath("menuImage/creditImage1.jpg")));
        battleBackground = new ImageView(new Image(getPath("menuImage/forestBackground.png")));
        unitImage = new Image("battleUnit/unitSprite.png");
    }

    public static ArrayList<IRenderable> getTownEntities() {
        return townEntities;
    }

    public static ArrayList<IRenderable> getForestEntities() {
        return forestEntities;
    }
    public static ArrayList<IRenderable> getBattleEntities(){return battleEntities;}

    public static void setTownEntities() {
    //    player1 = new Player(26,28);
    //    townEntities.add(player1);
        townEntities.add(new Guild(20,0,12,8,0,4,12,5));
        townEntities.add(new NormalTree(20,18.5, 4, 4, 1, 3.5, 2, 0.5));
        townEntities.add(new NormalTree(28,18.5, 4, 4, 1, 3.5, 2, 0.5));
        townEntities.add(new Statue(25.0,23.0,2,4,0,3,2,1));
        townEntities.add(new ChimneyHouse(13,18,6, 6, 0, 3.5, 6, 2.5));
        townEntities.add(new House(32,18,5,6,0,3.5,5,2.5));
        townEntities.add(new LongHouse(12,8,10.25, 6, 0, 4, 10.25, 2));
        townEntities.add(new TallHouse(32.5,4,6, 10, 0, 8, 6, 2));
        townEntities.add(new NormalTree(15,28.5, 4, 4, 1, 3.25, 2, 0.25));
        townEntities.add(new NormalTree(33,28.5, 4, 4, 1, 3.25, 2, 0.25));
        townEntities.add(new NormalTree(15,4, 4, 4, 1, 3.25, 2, 0.25));
        townEntities.add(new NormalTree(8,9, 4, 4, 1, 3.25, 2, 0.25));
        townEntities.add(new Warp(25,40));
        townEntities.get(13).setName("WARP_TO_FOREST");
        townEntities.add(new Warp(26,40));
        townEntities.get(14).setName("WARP_TO_FOREST");
        townEntities.add(new Warp(23,39));
        townEntities.get(15).setName("WARP_TO_FOREST");
        townEntities.add(new Warp(24,39));
        townEntities.get(16).setName("WARP_TO_FOREST");
        townEntities.add(new Warp(28,39));
        townEntities.get(17).setName("WARP_TO_FOREST");
        townEntities.add(new Warp(27,39));
        townEntities.get(18).setName("WARP_TO_FOREST");
        townEntities.add(new Merchant(17, 24, 1, 1, 0, 0.5, 1, 0.5));
        townEntities.add(new Man1Left(27.85, 25.5, 1, 1, 0.1, 0.5 ,0.8, 0.5));
        townEntities.add(new Lady(20, 30, 1, 1, 0, 0.5, 1, 0.5));
        townEntities.add(new Man1(38, 25, 1, 1, 0, 0.5, 1, 0.5));
        //townEntities.add(new Warp(26,35));

    }

    public static void setForestEntities() {
        //      player2 = new Player(40, 45);
        //      forestEntities.add(player2);
        forestEntities.add(new Warp(38, 38));
        forestEntities.get(1).setName("WARP_TO_TOWN");
        forestEntities.add(new Warp(38, 39));
        forestEntities.get(2).setName("WARP_TO_TOWN");
        forestEntities.add(new ForestTreeB(32, 38, 4, 4, 1.25, 3, 1.75, 0.5));
        //forestEntities[1] = new ForestTreeS(34,39,2, 2, 0.5, 1.5, 1, 0.25);
        forestEntities.add(new ForestTreeB(25, 32.75, 4, 4, 1.25, 3, 1.75, 0.5));
        forestEntities.add(new ForestTreeB(10.5, 27, 4, 4, 1.25, 3, 1.75, 0.5));
        forestEntities.add(new DeadTreeB(23.5, 21, 4, 4, 1, 3.5, 1.5, 0.5));
        forestEntities.add(new DeadTreeB(13, 23, 4, 4, 1, 3.5, 1.5, 0.5));
        forestEntities.add(new DeadTreeB(10, 19, 4, 4, 1, 3.5, 1.5, 0.5));
        forestEntities.add(new DeadTreeB(23.5, 13, 4, 4, 1, 3.5, 1.5, 0.5));
        forestEntities.add(new DeadTreeB(13.5, 11.5, 4, 4, 1, 3.5, 1.5, 0.5));
        forestEntities.add(new Chimera(18,18,4,4,0,2,4,2));

    }
    public static ArrayList<IRenderable> getCurrentEntities(){
        if (GameLogic.getCurrentMap() == GameLogic.townMap){
            return townEntities;
        }
        return forestEntities;
    }
}
