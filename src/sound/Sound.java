package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Sound {
    private Media clip;
    Media sound[] = new Media[30];
    MediaPlayer soundPlayer;
    public Sound() {
        sound[0] = new Media((getPath("sound/opening.mp3")));
        sound[1] = new Media(getPath("sound/town.mp3"));
        sound[2] = new Media(getPath("sound/forest.mp3"));
        sound[3] = new Media(getPath("sound/battle.mp3"));
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

    public MediaPlayer getClip() {
        return this.soundPlayer;
    }

    public static String getPath(String url) {
        return ClassLoader.getSystemResource(url).toString();
    }
}
