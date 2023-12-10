package gui;

import display.ScreenUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
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
    private Parent shopMenu;
    private ShopMenuController shopMenuController;
    private Parent inventoryMenu;
    private InventoryMenuController inventoryMenuController;
    private ImageView mainMenuBackground = RenderableHolder.mainMenuBackground;

    public RootPane() throws IOException {
        this.setFocusTraversable(true);
        mainMenu = new MainMenu();
        initializeCreditMenu();
        initializePauseMenu();
        initializeOptionMenu();
        initializeShopMenu();
        initializeInventoryMenu();

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

    public void initializeShopMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(GameLogic.class.getResource(("/fxml/ShopMenu.fxml")));
        shopMenu = loader.load();
        shopMenuController = loader.getController();
    }

    public void initializeInventoryMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(GameLogic.class.getResource(("/fxml/InventoryMenu.fxml")));
        inventoryMenu = loader.load();
        inventoryMenuController = loader.getController();
    }

    public void switchToMainMenu() {
        if (this.getChildren().contains(this.creditMenu) || this.getChildren().contains(optionMenu)) {
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

    public Parent getShopMenu() {
        return this.shopMenu;
    }

    public Parent getInventoryMenu() {
        return this.inventoryMenu;
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
    public ShopMenuController getShopMenuController() {
        return this.shopMenuController;
    }

    public InventoryMenuController getInventoryMenuController() {
        return this.inventoryMenuController;
    }

    public void setOptionMenu(Parent optionMenu) {
        this.optionMenu = optionMenu;
    }

    public void setPauseMenu(Parent pauseMenu) {
        this.pauseMenu = pauseMenu;
    }

}
