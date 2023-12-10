package gui;

import display.ScreenUtil;
import javafx.scene.layout.VBox;
import logic.GameLogic;

public class MainMenu extends VBox {
    private MainMenuButton startButton;
    private MainMenuButton optionButton;
    private MainMenuButton helpButton;
    private MainMenuButton creditButton;
    private MainMenuButton exitButton;

    public MainMenu() {
        this.setSpacing(20);
     /*   this.setBorder(Border.stroke(Color.LIGHTGRAY));*/
        startButton = new MainMenuButton("START");
        optionButton = new MainMenuButton("OPTION");
        // helpButton = new MainMenuButton("HELP");
        creditButton = new MainMenuButton("CREDIT");
        exitButton = new MainMenuButton("EXIT");
        this.setPrefSize(ScreenUtil.screenWidth / (20 * ScreenUtil.scale), ScreenUtil.screenHeight / (3 * ScreenUtil.scale));
        this.setLayoutX(ScreenUtil.screenWidth / (3 * ScreenUtil.scale)); // 10 bigger number, closer to left
        this.setLayoutY(ScreenUtil.screenHeight / (0.75 * ScreenUtil.scale)); // 2.2 bigger number, higher it goes

        this.getChildren().addAll(startButton, optionButton, /*helpButton,*/ creditButton, exitButton);

        startButton.setOnMouseClicked(event -> {
            GameLogic.setupGame();
            GameLogic.startGameThread();
        });

        optionButton.setOnMouseClicked((event -> {
            switchToOptionMenu();
        }));

        /*helpButton.setOnMouseClicked(event -> {
            switchToHelpMenu();
        });*/

        creditButton.setOnMouseClicked((event -> {
            switchToCreditMenu();
        }));

        exitButton.setOnMouseClicked(event -> {
            System.exit(0);
        });

    }


    public void switchToOptionMenu() {
        GameLogic.getRoot().getChildren().remove(GameLogic.getRoot().getMainMenu());
        GameLogic.getRoot().getChildren().add(GameLogic.getRoot().getOptionMenu());
    }

    public void switchToCreditMenu() {
        GameLogic.getRoot().getChildren().remove(GameLogic.getRoot().getMainMenu());
        GameLogic.getRoot().getChildren().add(GameLogic.getRoot().getCreditMenu());
    }


}
