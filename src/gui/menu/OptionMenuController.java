package gui.menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import worldObject.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionMenuController implements Initializable {
    @FXML
    private javafx.scene.shape.Rectangle speedBar;
    @FXML
    private Rectangle musicBar;
    @FXML
    private StackPane speedBarRoot;
    @FXML
    private StackPane musicBarRoot;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSpeedBar();
        initializeMusicBar();
    }

    public void initializeSpeedBar() {
        speedBar.widthProperty().bind(speedBarRoot.widthProperty().multiply(0));
        speedBarRoot.setOnMouseDragged(event -> {

            double mouseX = event.getX(); // X position
            double barLength = 258;
            int speed = (int) Math.min(10, ((mouseX + 77.4) / barLength) * 10);
            if (speed <= 3) speed = 3;
            GameLogic.getPlayer().setSpeed(speed);
            speedBar.widthProperty().bind(speedBarRoot.widthProperty().multiply((Math.min(1, mouseX / barLength))));
        });
    }

    public void initializeMusicBar() {
        musicBar.widthProperty().bind(musicBarRoot.widthProperty().multiply(0.5));
        GameLogic.setMusicVolume(0.5);
        musicBarRoot.setOnMouseDragged(event -> {

            double mouseX = event.getX(); // Store the X position in a local variable
            double barLength = 258;
            double volume = Math.min(1, mouseX / barLength);
            GameLogic.setMusicVolume(volume);
            musicBar.widthProperty().bind(musicBarRoot.widthProperty().multiply(volume));

        });
    }






}
