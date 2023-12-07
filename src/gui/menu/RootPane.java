package gui.menu;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sharedObject.RenderableHolder;


import java.io.IOException;

public class RootPane extends Pane {
    private Parent creditMenu;
    private MainMenu mainMenu;
    private Parent settingMenu;
    private SettingMenuController settingMenuController;
    private Parent pauseMenu;
    private PauseMenuController pauseMenuController;

    public RootPane() throws IOException {
        mainMenu = new MainMenu();
   /*   initializeCreditMenu();
        initializePauseMenu();
        initializeSettingMenu();*/

        ImageView mainMenuBackground = RenderableHolder.mainMenuBackground;
    }



}
