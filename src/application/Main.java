package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import display.GameScreen;
import logic.GameLogic;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        GameLogic gameLogic = new GameLogic();
        stage.setScene(GameLogic.getScene());
        stage.setTitle("Progmeth Project"); // Title will be edited later
        stage.setResizable(false);

        stage.show();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}

