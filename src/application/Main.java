package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import display.GameScreen;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Progmeth Project");
        stage.setResizable(false);

        GameScreen gameScreen = new GameScreen();
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();
        stage.show();
        gameScreen.setupGame();
        gameScreen.startGameThread();

    }

    public static void main(String[] args){
        Application.launch(args);
    }
}

