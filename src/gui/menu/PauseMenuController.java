package gui.menu;

import display.ScreenUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import sound.Sound;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PauseMenuController implements Initializable {
    @FXML
    private StackPane continueButton;
    @FXML
    private Text cont;
    @FXML
    private StackPane optionButton;
    @FXML
    private StackPane exitButton;
    @FXML
    private Text exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeButtons();
    }

    public void initializeButtons() {
        ArrayList<Rectangle> buttons= new ArrayList<>();

        continueButton.setOnMouseEntered(event -> {
            cont.setFill(Color.WHITE);
            RenderableHolder.hoverSound.play();
        });

        continueButton.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            GameLogic.continueGame();
        });

        continueButton.setOnMouseExited(event -> {
            cont.setFill(Color.LIGHTGRAY);
        });


        exitButton.setOnMouseEntered(event -> {
            exit.setFill(Color.WHITE);
            RenderableHolder.hoverSound.play();
        });

        exitButton.setOnMouseExited(event -> {
            exit.setFill(Color.LIGHTGRAY);
        });

        exitButton.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            ImageView mainMenuBackground = RenderableHolder.mainMenuBackground;
            mainMenuBackground.setFitHeight(ScreenUtil.screenHeight);
            mainMenuBackground.setFitWidth(ScreenUtil.screenWidth);
            GameLogic.getScene().setOnKeyPressed(null);
          //  RenderableHolder.getBattleEntities().clear();
          //  RenderableHolder.getForestEntities().clear();
            GameLogic.getGameThread().interrupt();
            RenderableHolder.getTownEntities().clear();
            GameLogic.getRoot().getChildren().clear();
            GameLogic.getRoot().getChildren().addAll(mainMenuBackground, GameLogic.getRoot().getMainMenu());
            GameLogic.getRoot().requestFocus();
            GameLogic.stopMusic();
            GameLogic.setGameState(GameLogic.worldState);
            GameLogic.playMusic(0);
        });



    }


}
