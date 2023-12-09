package gui.menu;

import control.InputUtility;
import display.ScreenUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;
import sharedObject.RenderableHolder;


import java.io.IOException;

public class RootPane extends Pane {
    private MainMenu mainMenu;
    private Parent creditMenu;
    private CreditMenuController creditMenuController;
    private Parent optionMenu;
    private OptionMenuController optionMenuController;
    private Parent pauseMenu;
    private PauseMenuController pauseMenuController;
    private Parent helpMenu;
    private HelpMenuController helpMenuController;
    private ImageView mainMenuBackground = RenderableHolder.mainMenuBackground;

    public RootPane() throws IOException {
        this.setFocusTraversable(true);
        mainMenu = new MainMenu();
        initializeCreditMenu();
        initializePauseMenu();
        initializeOptionMenu();

        mainMenuBackground.setFitHeight(ScreenUtil.screenHeight);
        mainMenuBackground.setFitWidth(ScreenUtil.screenWidth);
        this.getChildren().addAll(mainMenuBackground, mainMenu);



        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("PRESS ESC");
                switchToMainMenu();
            }
        });
    }


    public void initializeCreditMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/fxml/CreditMenu.fxml")));
        creditMenu = loader.load();
        creditMenuController = loader.getController();
    }

    public void initializePauseMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/fxml/PauseMenu.fxml")));
        pauseMenu = loader.load();
        pauseMenuController = loader.getController();
    }

    public void initializeOptionMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/fxml/OptionMenu.fxml")));
        optionMenu = loader.load();
        optionMenuController =loader.getController();

    }

    public void initializeHelpMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/fxml/HelpMenu.fxml")));
        helpMenu = loader.load();
        helpMenuController =loader.getController();
    }

    public void switchToMainMenu() {
        if (this.getChildren().contains(this.creditMenu) || this.getChildren().contains(optionMenu)
                || this.getChildren().contains(this.helpMenu)) {
            RenderableHolder.declineSound.play();

            this.getChildren().clear();
            this.getChildren().addAll(RenderableHolder.mainMenuBackground, this.getMainMenu());

        }

    }

    public Parent getCreditMenu() {
        return this.creditMenu;
    }

    public MainMenu getMainMenu() {
        return this.mainMenu;
    }

    public Parent getOptionMenu() {
        return this.optionMenu;
    }

    public Parent getPauseMenu() {
        return this.pauseMenu;
    }

    public Parent getHelpMenu() {
        return this.helpMenu;
    }

    public CreditMenuController getCreditMenuController() {
        return this.creditMenuController;
    }

    public OptionMenuController getOptionMenuController() {
        return this.optionMenuController;
    }

    public PauseMenuController getPauseMenuController() {
        return this.pauseMenuController;
    }

    public HelpMenuController getHelpMenuController() {
        return this.helpMenuController;
    }

    public void setOptionMenu(Parent optionMenu) {
        this.optionMenu = optionMenu;
    }

    public void setPauseMenu(Parent pauseMenu) {
        this.pauseMenu = pauseMenu;
    }

    public void setHelpMenu(Parent helpMenu) {
        this.helpMenu = helpMenu;
    }

}
