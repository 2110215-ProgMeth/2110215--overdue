package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logic.GameLogic;
import sharedObject.RenderableHolder;


public class Sound {
    private Media clip;
    Media sound[] = new Media[30];
    public static MediaPlayer soundPlayer;
    public static double volume;
    public Sound() {
        sound[0] = new Media(RenderableHolder.getPath("sound/opening.mp3"));
        sound[1] = new Media(RenderableHolder.getPath("sound/town.mp3"));
        sound[2] = new Media(RenderableHolder.getPath("sound/forest.mp3"));
        sound[3] = new Media(RenderableHolder.getPath("sound/battle.mp3"));
    }

    public void setClip(int i) {
        this.clip = sound[i];
        soundPlayer = new MediaPlayer(clip);
        this.soundPlayer.setVolume(0.5);
    }
    public void play() {
        this.soundPlayer.play();
    }
    public void loop() {
        this.soundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void stop() {
        this.soundPlayer.stop();
    }

    public static MediaPlayer getClip() {
        return soundPlayer;
    }

}
