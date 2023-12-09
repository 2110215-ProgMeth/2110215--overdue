package sharedObject;

import interfaces.IRenderable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

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
    public static List<IRenderable> townEntities;
    public static List<IRenderable> battleEntities;
    public static List<IRenderable> forestEntities;

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

  /*  public List<IRenderable> getEntities() {
        return entities;
    }*/

    public static void update(List<IRenderable> entities) {
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed())
                entities.remove(i);
        }
    }

    public static void loadSound() {
        hoverSound = new AudioClip(getPath("effect/001_Hover_01.wav"));
        confirmSound = new AudioClip(getPath("effect/013_Confirm_03.wav"));
        declineSound = new AudioClip(getPath("effect/029_Decline_09.wav"));
    }

    public static void loadImage() {
        mainMenuBackground = new ImageView(new Image(getPath("menuImage/gameCover.png")));
        darkWojak = new ImageView(new Image(getPath("menuImage/creditImage1.jpg")));


    }

    public static List<IRenderable> getTownEntities() {
        return townEntities;
    }



}
