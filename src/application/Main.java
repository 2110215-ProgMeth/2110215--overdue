package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import display.GamePanel;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Progmeth Project");
        stage.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        gamePanel.startGameThread();
        root.getChildren().add(gamePanel);

        stage.show();

    }

    public static void main(String[] args){
        Application.launch(args);
    }
}

